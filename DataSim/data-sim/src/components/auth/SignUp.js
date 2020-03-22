import React, { Component } from 'react'
import {Redirect} from 'react-router-dom'
import {connect} from 'react-redux'
import {signUp} from "../../store/actions/authActions";


class SignUp extends Component {
    state = {
        email: '',
        password: '',
        fullName: '',
        position: '',
        department: '',
        hospital: '' ,
        // confirmpassword: ''
    }
    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }
    handleSubmit = (e) => {
        e.preventDefault();
        console.log(this.state);
        this.props.signUp(this.state)
    }
    render() {
        const { auth ,authError} = this.props;
        if(auth.uid) return <Redirect to='/'/>
        return (
            <div className="container">
                <form className="white" onSubmit={this.handleSubmit}>
                    <h5 className="grey-text text-darken-3">Sign Up</h5>
                    <div className="input-field">
                        <label htmlFor="email">Email</label>
                        <input type="email" id='email' onChange={this.handleChange} />
                    </div>
                    <div className="input-field">
                        <label htmlFor="password">Password</label>
                        <input type="password" id='password' onChange={this.handleChange} />
                    </div>
                    {/*<div className="input-field">*/}
                    {/*    <label htmlFor="confirmpassword">Confirm Password</label>*/}
                    {/*    <input type="password" id='confirmpassword' onChange={this.handleChange} />*/}
                    {/*</div>*/}
                    <div className="input-field">
                        <label htmlFor="firstName">Full Name</label>
                        <input type="text" id='fullName' onChange={this.handleChange} />
                    </div>
                    <div className="input-field">
                        <label htmlFor="position">Position</label>
                        <input type="text" id='position' onChange={this.handleChange} />
                    </div>
                    <div className="input-field">
                        <label htmlFor="department">Department</label>
                        <input type="text" id='department' onChange={this.handleChange} />
                    </div>
                    <div className="input-field">
                        <label htmlFor="hospital">Hospital</label>
                        <input type="text" id='hospital' onChange={this.handleChange} />
                    </div>
                    <div className="input-field">
                        <button className="btn red lighten-1 z-depth-0">Sign Up</button>
                        <div className="red-text center">
                            {authError ? <p>{authError}</p> : null}
                        </div>
                    </div>
                </form>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return{
        auth: state.firebase.auth,
        authError: state.auth.authError
    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        signUp: (newUser) => dispatch(signUp(newUser))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(SignUp);
