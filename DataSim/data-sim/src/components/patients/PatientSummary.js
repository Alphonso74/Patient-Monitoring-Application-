import React from "react";

const PatientSummary = ({patient}) => {
    if (patient.triageTag === 'Black') {
        return (
            <div className="card z-depth-0 project-summary light grey center">
                <div className="card-content grey-text text-darken-3 hoverable">
                    <span className="card-title ">{patient.patientName}</span>
                    <p>Heart Rate - {patient.rHeartRate}</p>
                    <p>Body Temperature - {patient.bodyTempature}</p>
                    <p>Height - {patient.height}</p>
                    <p>Weight - {patient.weight}</p>
                    <p>Triage Tag - {patient.triageTag}</p>
                    <p>Active Nurse: {patient.activeNurse}</p>
                </div>
            </div>
        )
    }
    else if (patient.triageTag === 'Blue') {
        return (
            <div className="card z-depth-0 project-summary light blue center">
                <div className="card-content grey-text text-darken-3 hoverable">
                    <span className="card-title ">{patient.patientName}</span>
                    <p>Heart Rate - {patient.rHeartRate}</p>
                    <p>Body Temperature - {patient.bodyTempature}</p>
                    <p>Height - {patient.height}</p>
                    <p>Weight - {patient.weight}</p>
                    <p>Triage Tag - {patient.triageTag}</p>
                    <p>Active Nurse: {patient.activeNurse}</p>
                </div>
            </div>
        )
    }
    else if (patient.triageTag === 'Green') {
        return (
            <div className="card z-depth-0 project-summary light green center">
                <div className="card-content grey-text text-darken-3 hoverable">
                    <span className="card-title ">{patient.patientName}</span>
                    <p>Heart Rate - {patient.rHeartRate}</p>
                    <p>Body Temperature - {patient.bodyTempature}</p>
                    <p>Height - {patient.height}</p>
                    <p>Weight - {patient.weight}</p>
                    <p>Triage Tag - {patient.triageTag}</p>
                    <p>Active Nurse: {patient.activeNurse}</p>
                </div>
            </div>
        )
    }
    else if (patient.triageTag === 'Red') {
        return (
            <div className="card z-depth-0 project-summary light red center">
                <div className="card-content grey-text text-darken-3 hoverable">
                    <span className="card-title ">{patient.patientName}</span>
                    <p>Heart Rate - {patient.rHeartRate}</p>
                    <p>Body Temperature - {patient.bodyTempature}</p>
                    <p>Height - {patient.height}</p>
                    <p>Weight - {patient.weight}</p>
                    <p>Triage Tag - {patient.triageTag}</p>
                    <p>Active Nurse: {patient.activeNurse}</p>
                </div>
            </div>
        )
    }
};

export default PatientSummary
