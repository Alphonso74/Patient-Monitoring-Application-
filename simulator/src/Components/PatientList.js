import React from 'react';

const PatientList = ({patient}) => {
    return(
        <div className="card">
            <div className="card-content">
                <span className="card-title">{patient.name}</span>
                <div className="divider"/>
                <p>Heart Rate: {patient.hr}</p>
                <p>Triage Tag: {patient.tt}</p>
                <p>Body Temp: {patient.temp}</p>
                <a className="waves-effect waves-light red darken-2 btn">Flat-line</a>
                <a className="waves-effect waves-light red darken-2 btn">Elevate</a>
            </div>
        </div>
    )
};

export default PatientList;