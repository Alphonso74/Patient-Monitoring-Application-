import React from 'react'
import PatientSummary from './PatientSummary'

const PatientList = ({patients}) => {
  return (
    <div className="project-list section">



        {patients && patients.map(patient => {
            return (
                <div>
                    <PatientSummary patient={patient} />
                </div>
            )
        })}



    </div>
  )
}

export default PatientList
