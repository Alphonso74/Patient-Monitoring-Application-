import React, { Component } from "react";
import AlertModal from './AlertModal';

class PatientSummary extends Component {
    constructor(props) {
        super(props);
        this.state ={ // use to limit number of modals
            highHR: false,
            lowHR: false,
            fever: false,
            hypotherm: false
        };
    }

    componentDidMount(){
        this.interval = setInterval(() => {this.checkAlerts()}, 1000);
    }
    componentWillUnmount(){
        clearInterval(this.interval);
    }

    checkAlerts(){
        // MODALS
        // High Heart Rate
        if(this.props.patient.rHeartRate > 100 && this.state.highHR === false){
            this.setState({ highHR: true });
            console.dir(this.state.highHR);
        }
        else if(this.props.patient.rHeartRate < 100 && this.state.highHR === true){
            this.setState({ highHR: false });
        }

        // Low Heart Rate
        if(this.props.patient.rHeartRate < 50 && this.state.lowHR === false){
            this.setState({ lowHR: true });
            console.dir(this.state.lowHR);
        }
        else if(this.props.patient.rHeartRate < 50 && this.state.lowHR === true){
            this.setState({ lowHR: false });
        }

        // High Temperature
        if(this.props.patient.bodyTempature > 110 && this.state.fever === false){
            this.setState({ fever: true });
            console.dir(this.state.fever);
        }
        else if(this.props.patient.bodyTempature < 110 && this.state.fever === true){
            this.setState({ fever: false });
        }

        // Low Temperature
        if(this.props.patient.bodyTempature < 90 && this.state.hypotherm === false){
            this.setState({ hypotherm: true });
            console.dir(this.state.hypotherm);
        }
        else if(this.props.patient.bodyTempature > 90 && this.state.hypotherm === true){
            this.setState({ hypotherm: false });
        }
        return;
    }

    render(){
        const patient = this.props.patient;
        if (this.props.patient.triageTag === 'Black') {
            return (
                <div>
                    <div className="card z-depth-0 project-summary light grey center">
                        <div className="card-content grey-text text-darken-3 hoverable">
                            <span className="card-title ">{this.props.patient.patientName}</span>
                            <p>Heart Rate - {this.props.patient.rHeartRate}</p>
                            <p>Body Temperature - {this.props.patient.bodyTempature}</p>
                            <p>Height - {this.props.patient.height}</p>
                            <p>Weight - {this.props.patient.weight}</p>
                            <p>Triage Tag - {this.props.patient.triageTag}</p>
                            <p>Active Nurse: {this.props.patient.activeNurse}</p>
                        </div>
                    </div>
                    {patient.rHeartRate > 100 && this.state.highHR === false &&
                    <AlertModal patient={this.props.patient} alert={"High HR"} />
                    }
                    {patient.rHeartRate < 50 && this.state.lowHR === false &&
                    <AlertModal patient={this.props.patient} alert={"Low HR"} />
                    }
                    {patient.bodyTempature > 110 && this.state.fever === false &&
                    <AlertModal patient={this.props.patient} alert={"High Temp"} />
                    }
                    {patient.bodyTempature < 90 && this.state.hypotherm === false &&
                    <AlertModal patient={this.props.patient} alert={"Low Temp"} />
                    }
                </div>
            )
        }
        else if (this.props.patient.triageTag === 'Yellow') {
            return (
                <div>
                    <div className="card z-depth-0 project-summary light yellow center">
                        <div className="card-content grey-text text-darken-3 hoverable">
                            <span className="card-title ">{this.props.patient.patientName}</span>
                            <p>Heart Rate - {this.props.patient.rHeartRate}</p>
                            <p>Body Temperature - {this.props.patient.bodyTempature}</p>
                            <p>Height - {this.props.patient.height}</p>
                            <p>Weight - {this.props.patient.weight}</p>
                            <p>Triage Tag - {this.props.patient.triageTag}</p>
                            <p>Active Nurse: {this.props.patient.activeNurse}</p>
                        </div>
                    </div>
                    {this.props.patient.rHeartRate > 100 && this.state.highHR === false &&
                    <AlertModal patient={this.props.patient} alert={"High HR"} />
                    }
                    {this.props.patient.rHeartRate < 50 && this.state.lowHR === false &&
                    <AlertModal patient={this.props.patient} alert={"Low HR"} />
                    }
                    {this.props.patient.bodyTempature > 110 && this.state.fever === false &&
                    <AlertModal patient={this.props.patient} alert={"High Temp"} />
                    }
                    {this.props.patient.bodyTempature < 90 && this.state.hypotherm === false &&
                    <AlertModal patient={this.props.patient} alert={"Low Temp"} />
                    }
                </div>
            )
        }
        else if (this.props.patient.triageTag === 'Green') {
            return (
                <div>
                    <div className="card z-depth-0 project-summary light green center">
                        <div className="card-content grey-text text-darken-3 hoverable">
                            <span className="card-title ">{this.props.patient.patientName}</span>
                            <p>Heart Rate - {this.props.patient.rHeartRate}</p>
                            <p>Body Temperature - {this.props.patient.bodyTempature}</p>
                            <p>Height - {this.props.patient.height}</p>
                            <p>Weight - {this.props.patient.weight}</p>
                            <p>Triage Tag - {this.props.patient.triageTag}</p>
                            <p>Active Nurse: {this.props.patient.activeNurse}</p>
                        </div>
                    </div>
                    {this.props.patient.rHeartRate > 100 && this.state.highHR === false &&
                    <AlertModal patient={this.props.patient} alert={"High HR"} />
                    }
                    {this.props.patient.rHeartRate < 50 && this.state.lowHR === false &&
                    <AlertModal patient={this.props.patient} alert={"Low HR"} />
                    }
                    {this.props.patient.bodyTempature > 110 && this.state.fever === false &&
                    <AlertModal patient={this.props.patient} alert={"High Temp"} />
                    }
                    {this.props.patient.bodyTempature < 90 && this.state.hypotherm === false &&
                    <AlertModal patient={this.props.patient} alert={"Low Temp"} />
                    }
                </div>
            )
        }
        else if (this.props.patient.triageTag === 'Red') {
            return (
                <div>
                    <div className="card z-depth-0 project-summary light red center">
                        <div className="card-content grey-text text-darken-3 hoverable">
                            <span className="card-title ">{this.props.patient.patientName}</span>
                            <p>Heart Rate - {this.props.patient.rHeartRate}</p>
                            <p>Body Temperature - {this.props.patient.bodyTempature}</p>
                            <p>Height - {this.props.patient.height}</p>
                            <p>Weight - {this.props.patient.weight}</p>
                            <p>Triage Tag - {this.props.patient.triageTag}</p>
                            <p>Active Nurse: {this.props.patient.activeNurse}</p>
                        </div>
                    </div>
                    {this.props.patient.rHeartRate > 100 && this.state.highHR === false &&
                    <AlertModal patient={this.props.patient} alert={"High HR"} />
                    }
                    {this.props.patient.rHeartRate < 50 && this.state.lowHR === false &&
                    <AlertModal patient={this.props.patient} alert={"Low HR"} />
                    }
                    {this.props.patient.bodyTempature > 110 && this.state.fever === false &&
                    <AlertModal patient={this.props.patient} alert={"High Temp"} />
                    }
                    {this.props.patient.bodyTempature < 90 && this.state.hypotherm === false &&
                    <AlertModal patient={this.props.patient} alert={"Low Temp"} />
                    }
                </div>
            )
        }
    }
};

export default PatientSummary
