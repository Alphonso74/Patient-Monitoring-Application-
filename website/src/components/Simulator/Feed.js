import React from 'react';
import PatientList from './PatientList';

const Feed = ({patients}) => {
    return (
        <div className="section">
            { patients && patients.map(patient => {
                return(
                    <PatientList patient={patient} key={patient.id}/>
                )
            })}
        </div>
    )
};

export default Feed;