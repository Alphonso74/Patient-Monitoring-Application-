import React, { Component } from 'react';
import { connect } from 'react-redux';
import {UpdatePatient} from "../Store/Actions/PatientActions";
import 'firebase/firestore';

class PatientList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            text: "Start Simulator",
            running: false
        }
    }

    componentDidMount(){
        this.interval = setInterval(() => {this.simulate()}, 1000);
    }
    componentWillUnmount(){
        clearInterval(this.interval);
    }

    simulate = () => {
        if(this.state.running){
            let max;
            switch (this.props.patient.triageTag) {
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

            let heartRate = this.props.patient.rHeartRate;
            let bodyTemp = this.props.patient.bodyTempature;

            // Change heart rate
            if (randHR <= (max / 3)) {
                heartRate++;
            } else if (this.props.patient.triageTag !== "Green" && randHR === max) {
                heartRate = 0;
            } else if (heartRate > 0 && randHR >= (2 * (max / 3))) {
                heartRate--;
            }

            // Change body temp
            if (randBT <= (max / 3)) {
                bodyTemp += 1;
            } else if (randBT >= (2 * (max / 3))) {
                bodyTemp -= 1;
            }

            // Round to ##.#
            //bodyTemp = Math.round(bodyTemp * 1) / 1;

            // Update firestore data with new values
            this.props.UpdatePatient(this.props.patient, heartRate, bodyTemp);
        }
    }

    simButton = () => {
        this.setState((prevState) => ({
            running: !prevState.running
        }));

        if(this.state.running){ this.setState({text: "Start Simulator"}); }
        else{ this.setState({text: "Stop Simulator"}); }
    }

    flatline = () => {
        let hr = 0;
        this.props.UpdatePatient(this.props.patient, hr, this.props.patient.bodyTempature);
    }

    elevate = () => {
        let hr = this.props.patient.rHeartRate * 2;
        this.props.UpdatePatient(this.props.patient, hr, this.props.patient.bodyTempature);
    }

    resetHR = () => {
        let hr = 75;
        this.props.UpdatePatient(this.props.patient, hr, this.props.patient.bodyTempature);
    }

    fever = () => {
        let bt = 104;
        this.props.UpdatePatient(this.props.patient, this.props.patient.rHeartRate, bt);
    }

    hypothermia = () => {
        let bt = 90;
        this.props.UpdatePatient(this.props.patient, this.props.patient.rHeartRate, bt);
    }

    resetBT = () => {
        let bt = 98;
        this.props.UpdatePatient(this.props.patient, this.props.patient.rHeartRate, bt);
    }

    render(){
        return(
            <div>

            </div>
        )
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        UpdatePatient: (patient, heartRate, bodyTemp) => dispatch(UpdatePatient(patient, heartRate, bodyTemp))
    }
};

export default connect(null, mapDispatchToProps )(PatientList);