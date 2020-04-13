import React, { Component } from "react";
import {compose} from "redux";
import {connect} from "react-redux";
import {firestoreConnect} from "react-redux-firebase";
import * as firebase from "firebase";

class Chat extends Component {
    constructor(props){
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        const {auth} = this.props;
        this.state = {
            user: auth.uid,
            chats: [],
            content: '',
            readError: null,
            writeError: null
        };
    }
    componentDidMount() {
        this.setState({readError: null});
        try {
            firebase.ref("chat").on("value", snapshot => {
                let chats = [];
                snapshot.forEach((snap) => {
                    chats.push(snap.val());
                });
                this.setState({chats});
            });
        } catch (error) {
            this.setState({readError: error.message})
        }
    }
    handleChange = (event) => {
        this.setState({
            content: event.target.value
        });
    };
    async handleSubmit(event){
        event.preventDefault();
        this.setState({ writeError: null });
        try {
            await firebase.ref("chat").push({
                content: this.state.content,
                timestamp: Date.now(),
                uid: this.state.user.uid
            });
            this.setState({ content: '' });
        } catch (error) {
            this.setState({ writeError: error.message });
        }
    }
    render() {
        return (
            <div>
                <div className="chats">
                    {this.state.chats.map(chat => {
                        return <p key={chat.timestamp}>{chat.content}</p>
                    })}
                </div>
                <form onSubmit={this.handleSubmit}>
                    <input onChange={this.handleChange} value={this.state.content}></input>
                    {this.state.error ? <p>{this.state.writeError}</p> : null}
                    <button type="submit">Send</button>
                </form>
                <div>
                    Login in as: <strong>{this.state.user.uid}</strong>
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
    auth: state.firebase.auth
    }
};

export default compose(
    connect(mapStateToProps),
    firestoreConnect([
        {collection: 'chat'}
    ])
)(Chat)