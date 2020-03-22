import React from 'react'
import PatientSummary from './PatientSummary'
import {Link } from 'react-router-dom'

const PatientList = ({patients}) => {
  return (
    <div className="project-list section">


        {patients && patients.map(patient => {


            return (
                <Link to={'/patient/' + patient.id} key={patient.id}>
                <PatientSummary patient={patient} />
        </Link>
            )

        })}



    </div>
  )
}

export default PatientList
