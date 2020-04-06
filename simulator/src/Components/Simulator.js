import React, { Component } from 'react';
import { connect } from "react-redux";
import {UpdatePatient} from "../Store/Actions/PatientActions";
import firebase from 'firebase';
import 'firebase/firestore';

class Simulator extends Component{

    constructor(props) {
        super(props);
        this.state = {
            text: "Start Simulator",
            running: false
        }
    };

    simButton = () => {
        this.setState((prevState) => ({
            running: !prevState.running
        }));

        if(this.state.running){ this.setState({text: "Start Simulator"}); }
        else{ this.setState({text: "Stop Simulator"}); }
    };

    runSim = () => {
        if(this.state.running) {
            var patients = [];
            firebase.firestore().collection('patients').get().then(snapshot => {
                    // Populate array patients[] with firestore data
                    snapshot.forEach(patient => {
                        let data = patient.data();
                        let id = patient.id;
                        patients.push({id, ...data});
                    })
                }
            ).catch(error => {
                console.error(error)
            }).then(() => {
                for (let patient of patients) {
                    // Set max according to triage tag
                    let max;
                    switch (patient.tt) {
                        case "Yellow":
                            max = 100000;
                            break;
                        case "Red":
                            max = 10000;
                            break;
                        case "Black":
                            max = 1000;
                            break;
                        case "Green":
                        default:
                            max = 100;
                    }

                    // Randomize values
                    let randHR = 1 + Math.random() * max;
                    let randBT = 1 + Math.random() * max;

                    let heartRate = patient.hr;
                    let bodyTemp = patient.temp;

                    // Change heart rate
                    if (randHR <= (max / 3)) {
                        heartRate++;
                    } else if (patient.tt !== "Green" && randHR === max) {
                        heartRate = 0;
                    } else if (randHR >= (2 * (max / 3))) {
                        heartRate--;
                    }

                    // Change body temp
                    if (randBT <= (max / 3)) {
                        bodyTemp += .1;
                    } else if (randBT >= (2 * (max / 3))) {
                        bodyTemp -= .1;
                    }

                    // Round to ##.#
                    bodyTemp = Math.round(bodyTemp * 10) / 10;

                    // Update firestore data with new values
                    this.props.UpdatePatient(patient, heartRate, bodyTemp);
                }
            })
        }
    };

    componentDidMount(){
        this.interval = setInterval(() => {this.runSim()}, 1000);
    }
    componentWillUnmount(){
        clearInterval(this.interval);
    }

    render() {
        return (
            <div className="section">
                <h4>Simulator Details</h4>
                <div className="divider"/>
                <p>
                    Randomization depends on triage tag<br/>
                    <div className="divider"/>
                    <h5>Heart Rate:</h5>
                    All:<br/>1/3 chance of increase by 1<br/>
                    1/3 chance of decrease by 1<br/>
                    1/3 chance of no change<br/>
                    Yellow:<br/>1/100000 chance of dropping to 0<br/>
                    Red:<br/>1/10000 chance of dropping to 0<br/>
                    Black:<br/>1/1000 chance of dropping to 0<br/>
                    Green:<br/>No chance of dropping to 0
                    <div className="divider"/>
                    <h5>Body Temperature</h5>
                    All:<br/>1/3 chance of increase by 0.1<br/>
                    1/3 chance of decrease by 0.1<br/>
                    1/3 chance of no change<br/>
                </p>
                <div className="divider"/>
                <a id="start" href="#" onClick={ this.simButton } className="waves-effect waves-light light-green darken-2 btn">{this.state.text}</a>
            </div>
        )
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        UpdatePatient: (patient, heartRate, bodyTemp) => dispatch(UpdatePatient(patient, heartRate, bodyTemp))
    }
};

export default connect(null, mapDispatchToProps)(Simulator);
