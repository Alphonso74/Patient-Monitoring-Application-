import React from "react";
import {connect} from 'react-redux'
import {firestoreConnect} from "react-redux-firebase";
import {compose} from 'redux'
import {Redirect} from 'react-router-dom'


const PatientDetails = (props) => {
    const {patient, auth} = props;
    if(!auth.uid) return <Redirect to='/signin'/>

    if(patient){

        return(
            <div className="container section project-details">
                <div className="card z-depth-0">
                    <div className="card-content">
                        <span className="card-title center">{patient.patientName}</span>
                        <p>Patient Description: {patient.description}</p>
                        <p>Height: {patient.height}</p>
                        <p>Weight: {patient.weight}</p>
                        <p>Body Temperature: {patient.bodyTempature}</p>
                        <p>Heart Rate: {patient.rHeartRate}</p>
                        <p>Triage Tag: {patient.triageTag}</p>
                        <DetailButtons patient={{patient}}/>


                    </div>
                    <div className="card-action grey lighten-4 grey-text">
                        {/*<div>Posted by The Net Ninja</div>*/}
                        {/*<div>2nd September, 2am</div>*/}
                    </div>
                </div>
            </div>
        )
    }
    else {


        return (
            <div className="container center">
            <p>Loading Patient Data one second....</p>
            </div>

        )
    }
};

const mapStateToProps = (state, ownProps) => {
    const id = ownProps.match.params.id;
    const patients = state.firestore.data.patients;
    const patient = patients ? patients[id] : null;
    return{

        patient: patient,
        auth: state.firebase.auth

    }
}

export default compose(
    connect(mapStateToProps),
    firestoreConnect([
        {collection: 'patients'}
    ])
)(PatientDetails)
