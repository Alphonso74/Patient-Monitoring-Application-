import React, { Component } from "react";
import {connect} from 'react-redux'
import {firestoreConnect} from "react-redux-firebase";
import {compose} from 'redux'
import {Link, Redirect} from 'react-router-dom'
//import DetailButtons from "./DetailButtons";
import {deletePatient} from "../../store/actions/patientActions";
import 'react-dropdown/style.css';
//import EditPatient from "./EditPatient";


class PatientDetails extends Component {
    handleClick = (e) => {
        switch(e.target.id) {
            case "delete":
                const input = prompt("Enter YES if you're sure you want to delete:");
                if (input === 'YES') {
                    e.preventDefault();
                    this.props.history.push('/');

                    console.log(this.state + 'deleted!');
                    this.props.deletePatient(this.state, this.props.match.params.id);
                }
                break;
            case "edit":
                /*return(
                    <EditPatient patient={this.props.patient} id={this.props.match.params.id}/>
                );*/
                break;

            default:
        }
    };
    render() {
        const {patient, auth} = this.props;
        {/*const newTo = {
            pathname: "/patient/edit/" + this.props.match.params.id,
            patient: patient,
            id: this.props.match.params.id
        };*/}
        const id = this.props.match.params.id;
        if (!auth.uid) return <Redirect to='/signin'/>;

        if (patient) {
            return (
                <div className="container section project-details">
                    <div className="card z-depth-0">
                        <div className="card-content">
                            <span className="card-title center">{patient.patientName}
                                <Link to={'/edit/'+ id} key={id}><input id="edit" type="button" className='button' value='Edit'/></Link>
                                <input id="delete" type="button" className='button' value='Delete' onClick={this.handleClick}/></span>
                            <p>Patient Description: {patient.description}</p>
                            <p>Height: {patient.height}</p>
                            <p>Weight: {patient.weight}</p>
                            <p>Body Temperature: {patient.bodyTempature}</p>
                            <p>Heart Rate: {patient.rHeartRate}</p>
                            <p>Triage Tag: {patient.triageTag}</p>
                            <p>Department: {patient.department}</p>
                            <p>Standing Order: {patient.standingOrder}</p>
                            <p>Medications: {patient.medications}</p>
                            <p>Surgical History: {patient.surgicaHistory}</p>
                            <p>Active Nurse: {patient.activeNurse}</p>
                        </div>
                    </div>
                </div>
            )
        } else {


            return (
                <div className="container center">
                    <p>Loading Patient Data. One second....</p>
                </div>

            )
        }
    }
}

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
        deletePatient: (patient, id) => dispatch(deletePatient(patient, id))
    }
};

export default compose(
    connect(mapStateToProps, mapDispatchToProps),
    firestoreConnect([
        {collection: 'patients3'}
    ])
)(PatientDetails)
