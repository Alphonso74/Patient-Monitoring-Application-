import React, { Component } from "react";
import {connect} from 'react-redux'
import {firestoreConnect} from "react-redux-firebase";
import {compose} from 'redux'
import {Redirect} from 'react-router-dom'
//import DetailButtons from "./DetailButtons";
import {deletePatient, updatePatient} from "../../store/actions/patientActions";
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';
import * as firebase from "firebase";


class EditPatient extends Component {
    state = {
        patientName: this.props.patient.patientName,
        description: this.props.patient.description,
        height: this.props.patient.height,
        weight: this.props.patient.weight,
        rHeartRate: this.props.patient.rHeartRate,
        triageTag:  this.props.patient.triageTag,
        bodyTempature: this.props.patient.bodyTempature,
        medications: this.props.patient.medications,
        surgicaHistory: this.props.patient.surgicaHistory,
        activeNurse: this.props.patient.activeNurse,
        standingOrder: this.props.patient.standingOrder,
        department: this.props.patient.department
    };

    handleClick = (e) => {
        switch(e.target.id){
            /*case "name":
                this.setState({patientName: prompt("Enter new name:", this.state.patientName)});
                break;*/

            case "delete":
                const input = prompt("Enter YES if you're sure you want to delete:");
                if (input === 'YES') {
                    e.preventDefault();
                    this.props.history.push('/');

                    console.log(this.state + 'deleted!');
                    this.props.deletePatient(this.state, this.props.match.params.id);
                }
                break;

            /*case "desc":
                this.setState({description: prompt("Enter new description:", this.state.description)});
                break;

            case "height":
                this.setState({height: prompt("Enter new height:", this.state.height)});
                break;

            case "weight":
                this.setState({weight: parseInt(prompt("Enter new weight:", this.state.weight))});
                break;

            case "temp":
                this.setState({bodyTempature: parseInt(prompt("Enter new body temperature:", this.state.bodyTempature))});
                break;

            case "hRate":
                this.setState({rHeartRate: parseInt(prompt("Enter new resting heart rate:", this.state.rHeartRate))});
                break;

            case "tag":
                this.setState({triageTag: prompt("Enter new triage tag:", this.state.triageTag)});
                break;

            case "order":
                this.setState({standingOrder: prompt("Enter new doctor standing order:", this.state.standingOrder)});
                break;

            case "meds":
                this.setState({medications: prompt("Enter patient's medications:", this.state.medications)});
                break;

            case "surgHist":
                this.setState({surgicaHistory: prompt("Enter patient's surgical history:", this.state.surgicaHistory)});
                break;*/

            case "submit":
                const id = this.props.match.params.id;
                e.preventDefault();
                this.props.history.push('/');

                console.log(this.state);
                this.props.updatePatient(this.state, id);
                break;

            default:
        }
    };
    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value

        })
    };
    tagSelect = (option) => {
        this.setState({triageTag: option.value});
        console.log(this.state.triageTag);
    };
    deptSelect = (option) => {
        this.setState({department: option.value});
        console.log(this.state.department);
    };
    nurseSelect = (option) => {
        this.setState({activeNurse: option.value});
        console.log(this.state.activeNurse);
    };

    render() {
        const tagOptions = [
            'Yellow', 'Green', 'Red', 'Black'
        ];
        const defaultTagOption = tagOptions[1];
        const deptOptions = [
            'General Care', 'Neonatal', 'Post-Operation'
        ];
        const defaultDeptOption = deptOptions[0];

        const {patient, auth} = this.props;
        let nurseOptions = [];
        firebase.firestore().collection('Users').where("position", "==", "Nurse").get().then(function (querySnapshot) {
            querySnapshot.forEach(function(doc) {
                console.log(doc.id, ' => ', doc.data());
                console.log(doc.data().fullName);
                nurseOptions.push(doc.data().fullName);
            });
        });
        //const defaultNurse = {nurseOptions};
        console.log("patient: " + patient + ", auth: " + auth);
        if (!auth.uid) return <Redirect to='/signin'/>;

        if (patient) {

            return (
                <div className="container section project-details">
                    <div className="card z-depth-0">
                        <div className="card-content">
                            <span className="card-title center">
                                <input id="name" type="text" value={this.state.patientName} onChange={this.handleChange}/>
                                <input id="delete" type="button" className='button' value='Delete' onChange={this.handleClick}/></span>
                            <p>Patient Description:
                                <input id="desc" type="text" value={this.state.description} onChange={this.handleChange}/></p>
                            <p>Height:
                                <input id="height" type="text" value={this.state.height} onChange={this.handleChange}/></p>
                            <p>Weight:
                                <input id="weight" type="text" value={this.state.weight} onChange={this.handleChange}/></p>
                            <p>Body Temperature:
                                <input id="temp" type="text" value={this.state.bodyTempature} onChange={this.handleChange}/></p>
                            <p>Heart Rate:
                                <input id="hRate" type="text" value={this.state.rHeartRate} onChange={this.handleChange}/></p>
                            <p>Triage Tag:
                                <Dropdown options={tagOptions} onChange={this.tagSelect} value={defaultTagOption} placeholder="Edit Triage Tag"/></p>
                            <p>Department:
                                <Dropdown options={deptOptions} onChange={this.deptSelect} value={defaultDeptOption} placeholder="Select your department" /></p>
                            <p>Standing Order:
                                <input id="order" type="text" value={this.state.standingOrder} onChange={this.handleChange}/></p>
                            <p>Medications:
                                <input id="meds" type="text" value={this.state.medications} onChange={this.handleChange}/></p>
                            <p>Surgical History:
                                <input id="surgHist" type="text" value={this.state.surgicaHistory} onChange={this.handleChange}/></p>
                            <p>Active Nurse: {patient.activeNurse}</p>
                            <Dropdown options={nurseOptions} onChange={this.nurseSelect} placeholder="Assign Active Nurse" />
                            <p><input id="submit" type="button" className="button" value="Submit" onClick={this.handleClick}/></p>
                        </div>
                    </div>
                </div>
            )
        } else {
            console.log('No patient found.');

            return (
                <div className="container center">
                    <p>Loading Patient Data. One second....</p>
                </div>

            )
        }
    }
};

const mapStateToProps = (state, ownProps) => {
    const id = ownProps.match.params.id;
    const patients = state.firestore.data.patients3;
    const patient = patients ? patients[id] : null;
    return{

        patient: patient,
        auth: state.firebase.auth

    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        updatePatient: (patient, id) => dispatch(updatePatient(patient, id)),
        deletePatient: (patient, id) => dispatch(deletePatient(patient, id))
    }
};

export default compose(
    connect(mapStateToProps, mapDispatchToProps),
    firestoreConnect([
        {collection: 'patients3'}
    ])
)(EditPatient)
