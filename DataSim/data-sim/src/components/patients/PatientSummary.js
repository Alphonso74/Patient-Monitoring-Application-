import React from "react";

const PatientSummary = ({patient}) => {
    return (
        <div className="card z-depth-0 project-summary light grey center">
            <div className="card-content grey-text text-darken-3 hoverable">
                <span className="card-title ">{patient.patientName}</span>
                <p>Heart Rate - {patient.rHeartRate}</p>
                <p>Body Temperature - {patient.bodyTempature}</p>
                <p>Height - {patient.height}</p>
                <p>Weight - {patient.weight}</p>
                <p>Triage Tag - {patient.triageTag}</p>
                <p className="grey-text">Active Nurse: {patient.activeNurse}</p>
            </div>
        </div>
    )
}

export default PatientSummary
