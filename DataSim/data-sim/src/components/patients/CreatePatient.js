import React, { Component } from 'react'

class CreatePatient extends Component {
    state = {
        patientName: '',
        description: '',
        patientHeight: '',
        patientWeight: '',
        patientHeartRate: '',
        triageTag:  '',
        bodyTemp: '',
        currentMedications: '',
        surgicalHistory: ''

    }
    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }
    handleSubmit = (e) => {
        e.preventDefault();
        console.log(this.state);
    }
    render() {
        return (
            <div className="container">
                <form className="white" onSubmit={this.handleSubmit}>
                    <h5 className="grey-text text-darken-3">Create a New Project</h5>

                    <div className="input-field">
                        <label htmlFor="patientName">Patient Name</label>
                        <input type="text" id='patientName' onChange={this.handleChange} />

                    </div>
                    <div className="input-field">
                        <label htmlFor="description">Project Description</label>
                        <textarea id="description" className="materialize-textarea" onChange={this.handleChange}></textarea>

                    </div>

                    <div className="input-field">
                        <label htmlFor="patientHeight">Patient Height</label>
                        <input type="text" id='patientHeight' onChange={this.handleChange} />

                    </div>

                    <div className="input-field">
                        <label htmlFor="patientWeight">Patient Weight</label>
                        <input type="number" id='patientWeight' onChange={this.handleChange} />

                    </div>

                    <div className="input-field">
                        <label htmlFor="patientHeartRate">Patient Resting Heart Rate</label>
                        <input type="number" id='patientHeartRate' onChange={this.handleChange} />

                    </div>

                    <div className="input-field">
                        <label htmlFor="triageTag">TriageTag</label>
                        <input type="text" id='triageTag' onChange={this.handleChange} />

                    </div>


                    <div className="input-field">
                        <label htmlFor="bodyTemp">Patient Resting Heart Rate</label>
                        <input type="number" id='bodyTemp' onChange={this.handleChange} />

                    </div>

                    <div className="input-field">
                        <label htmlFor="currentMedications">Current Medication (Separate With Commas)</label>
                        <input type="text" id='currentMedications' onChange={this.handleChange} />

                    </div>

                    <div className="input-field">
                        <label htmlFor="surgicalHistory">Surgical History (Separate With Commas)</label>
                        <input type="text" id='surgicalHistory' onChange={this.handleChange} />

                    </div>


                    <div className="input-field">
                        <button className="btn red lighten-1">Add Patient</button>
                    </div>
                </form>
            </div>
        )
    }
}

export default CreatePatient
