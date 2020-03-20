import React, { Component } from 'react';

class Notifications extends Component{
    render() {
        return (
            <div className="section">
                <h4>Notifications</h4>
                <div className="divider"/>
                <p className="valign-wrapper">Use this to notify users of when a patient goes into a critical state, goes back to being stable, or dies.</p>
                <div className="divider"/>
                <a className="waves-effect waves-light light-green darken-2 btn">Start Simulator</a>
            </div>
        )
    }
}

export default Notifications;