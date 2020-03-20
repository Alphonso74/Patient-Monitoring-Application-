import React, { Component } from 'react';
import { Button } from 'react-mdl';

class Feed extends Component {
    render() {
        return (
            <div className="section">
                <div className="card">
                    <div className="card-content">
                        <span className="card-title">Patient Name</span>
                        <div className="divider"/>
                        <p>Heart Rate:</p>
                        <a className="waves-effect waves-light red darken-2 btn">Flat-line</a>
                        <a className="waves-effect waves-light red darken-2 btn">Elevate</a>
                    </div>
                </div>

                <div className="card">
                    <div className="card-content">
                        <span className="card-title">Patient Name</span>
                        <div className="divider"/>
                        <p>Heart Rate:</p>
                        <a className="waves-effect waves-light red darken-2 btn">Flat-line</a>
                        <a className="waves-effect waves-light red darken-2 btn">Elevate</a>
                    </div>
                </div>

                <div className="card">
                    <div className="card-content">
                        <span className="card-title">Patient Name</span>
                        <div className="divider"/>
                        <p>Heart Rate:</p>
                        <a className="waves-effect waves-light red darken-2 btn">Flat-line</a>
                        <a className="waves-effect waves-light red darken-2 btn">Elevate</a>
                    </div>
                </div>
            </div>
        )
    }
}

export default Feed;