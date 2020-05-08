import React, { Component } from 'react';
import Simulator from './Simulator';
import Feed from './Feed';
import { connect } from 'react-redux';
import { firestoreConnect} from "react-redux-firebase";
import { compose } from "redux";
import { Redirect } from 'react-router-dom';

class Main extends Component {
    render(){
        const { patients, auth, user } = this.props;
        if(!auth.uid) return <Redirect to='/signin'/>;
        return(
            <div className="App">
                <div>
                    <div className="row">
                        <div className="col s12 m6">
                            <Feed patients={patients} />
                        </div>

                        <div className="col s12 m6">
                            <Simulator />
                        </div>

                    </div>
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    const id = state.firebase.auth.uid;
    const users = state.firestore.data.Users;
    const user = users ? users[id] : null;
    return {
        patients: state.firestore.ordered.patients3,
        auth: state.firebase.auth,
        user: user
    }
};

export default compose(
    connect(mapStateToProps),
    firestoreConnect([ { collection: 'patients3' } ] )
)(Main);
