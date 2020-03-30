import React, { Component } from 'react';

class Simulator extends Component{
    /*
    constructor(props) {
        super(props);
        this.state = {
            text: "Start Simulator"
        };
    }

    simButton = () => {
        this.props.startSim();
        if(this.props.running){ this.setState({text: "Start Simulator"}); }
        else{ this.setState({text: "Stop Simulator"}); }
    };

     */

    render() {
        return (
            <div className="section">
                <h4>Simulator</h4>
                <div className="divider"/>
                <p className="valign-wrapper">Simulator details coming soon.</p>
                <div className="divider"/>
                <a id="start" href="#" /*onClick={ this.simButton }*/ className="waves-effect waves-light light-green darken-2 btn">Start Simulator</a>
            </div>
        )
    }
}

export default Simulator;