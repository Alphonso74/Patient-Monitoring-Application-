import React, { Component } from "react";
import {connect} from 'react-redux'
import {firestoreConnect} from "react-redux-firebase";
import {compose} from 'redux'
import {Link, Redirect} from 'react-router-dom'
//import DetailButtons from "./DetailButtons";
import {deletePatient, updatePatient} from "../../store/actions/patientActions";
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';
import * as firebase from "firebase";


class EditPatient extends Component {

    constructor (props){
        super (props);
        this.handleChange = this.handleChange.bind(this);
        this.state = {
            patientName: props.patient.patientName,
            description: props.patient.description,
            height: props.patient.height,
            weight: props.patient.weight,
            rHeartRate: props.patient.rHeartRate,
            triageTag: props.patient.triageTag,
            bodyTempature: props.patient.bodyTempature,
            medications: props.patient.medications,
            surgicaHistory: props.patient.surgicaHistory,
            activeNurse: props.patient.activeNurse,
            standingOrder: props.patient.standingOrder,
            department: props.patient.department
        }
    }
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
        this.enabler(false);
        this.setState({triageTag: option.value});
        console.log(this.state.triageTag);
    };
    deptSelect = (option) => {
        this.enabler(false);
        this.setState({department: option.value});
        console.log(this.state.department);
    };
    nurseSelect = (option) => {
        this.enabler(false);
        this.setState({activeNurse: option.value});
        console.log(this.state.activeNurse);
    };
    enabler(val) {
        return val;
    }
    isDoc(){
        return this.props.user.role !== 'Doctor';
    }

    render() {
        console.log(this.props);
        this.enabler(true);
        const tagOptions = [
            'Yellow', 'Green', 'Red', 'Black'
        ];
        const defaultTagOption = tagOptions[1];
        const deptOptions = [
            'General Care', 'Neonatal', 'Post-Operation'
        ];
        const defaultDeptOption = deptOptions[0];
        //this.orderRef = React.createRef();
        const {patient, auth, user} = this.props;
        //if (user.role !== 'Doctor') this.orderRef.disabled = true;
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
                                <input id="delete" type="button" className='button' value='Delete' onChange={this.handleClick}/>
                                <Link to={'/patient/' + this.props.match.params.id}><button>Back</button></Link>
                                <input id="patientName" type="text" defaultValue={this.state.patientName} onChange={this.handleChange}/>
                                </span>
                            <p>Patient Description:
                                <input id="description" type="text"
                                       defaultValue={this.state.description} onChange={this.handleChange.bind(this)}
                                />
                            </p>
                            <p>Height:
                                <input id="height" type="text" value={this.state.height} onChange={this.handleChange}/></p>
                            <p>Weight:
                                <input id="weight" type="text" value={this.state.weight} onChange={this.handleChange}/></p>
                            <p>Body Temperature:
                                <input id="bodyTempature" type="text" defaultValue={this.state.bodyTempature} onChange={this.handleChange}/></p>
                            <p>Heart Rate:
                                <input id="rHeartRate" type="text" defaultValue={this.state.rHeartRate} onChange={this.handleChange}/></p>
                            <p>Triage Tag:
                                <Dropdown options={tagOptions} onChange={this.tagSelect} value={defaultTagOption} placeholder="Edit Triage Tag"/></p>
                            <p>Department:
                                <Dropdown options={deptOptions} onChange={this.deptSelect} value={defaultDeptOption} placeholder="Select your department" /></p>
                            <p>Standing Order:
                                <input id="standingOrder" type="text" defaultValue={this.state.standingOrder} onChange={this.handleChange} disabled={this.isDoc()}/></p>
                            <p>Medications:
                                <input id="medications" type="text" defaultValue={this.state.medications} onChange={this.handleChange}/></p>
                            <p>Surgical History:
                                <input id="surgicaHistory" type="text" defaultValue={this.state.surgicaHistory} onChange={this.handleChange}/></p>
                            <p>Active Nurse: {this.patient.activeNurse}</p>
                            <Dropdown options={nurseOptions} onChange={this.nurseSelect} placeholder="Assign Active Nurse" />
                            <p><input id="submit" type="button" className="button" value="Submit" onClick={this.handleClick} disabled={this.enabler()}/></p>
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
    const userid = state.firebase.auth.uid;
    const users = state.firestore.data.Users;
    const user = users ? users[userid] : null;
    const id = ownProps.match.params.id;
    const patients = state.firestore.data.patients3;
    const patient = patients ? patients[id] : null;
    return{

        patient: patient,
        auth: state.firebase.auth,
        user: user
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
        {collection: 'patients3'}, {collection: 'Users'}
    ])
)(EditPatient)

