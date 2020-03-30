import React from 'react';

let patients = [
    {
        name: "Patient 1",
        hr: 70,
        tt: "Green"
    },
    {
        name: "Patient 2",
        hr: 80,
        tt: "Blue"
    },
    {
        name: "Patient 3",
        hr: 90,
        tt: "Red"
    },
    {
        name: "Patient 4",
        hr: 100,
        tt: "Black"
    }];

const PatientList = patients.map((patients) =>
    <div className="card">
        <div className="card-content">
            <span className="card-title">{patients.name}</span>
            <div className="divider"/>
            <p>Heart Rate: {patients.hr}</p>
            <p>Triage Tag: {patients.tt}</p>
            <a className="waves-effect waves-light red darken-2 btn">Flat-line</a>
            <a className="waves-effect waves-light red darken-2 btn">Elevate</a>
        </div>
    </div>
);

export { PatientList, patients };