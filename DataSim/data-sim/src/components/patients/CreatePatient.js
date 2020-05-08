import React, { Component } from 'react'
import {createPatient} from "../../store/actions/patientActions";
import {connect} from 'react-redux'
//import {Link} from 'react-router-dom'
//import history from "../../history";
import {Redirect} from 'react-router-dom'
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';

class CreatePatient extends Component {
    state = {
        patientName: '',
        description: '',
        height: '',
        weight: 0,
        rHeartRate: 0,
        triageTag:  '',
        bodyTempature: 0,
        medications: '',
        surgicaHistory: '',
        activeNurse: '',
        standingOrder: '',
        department: ''
    };
    handleChange = (e) => {
        this.setState({
             [e.target.id]: e.target.value

        })
    };

    handleChangeNumber1 = (e) => {

        // handlePasswordChange: function(e) {
        this.setState({weight: parseInt(e.target.value)});
        // }

    };

    handleChangeNumber2 = (e) => {

        // handlePasswordChange: function(e) {
        this.setState({rHeartRate: parseInt(e.target.value)});
        // }

    };

    handleChangeNumber3 = (e) => {

        // handlePasswordChange: function(e) {
        this.setState({bodyTempature: parseInt(e.target.value)});
        // }

    };

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.history.push('/');


        console.log(this.state);
        this.props.createPatient(this.state)
    };

    tagSelect = (option) => {
        this.setState({triageTag: option.value});
        console.log(this.state.triageTag);
    };
    deptSelect = (option) => {
        this.setState({department: option.value});
        console.log(this.state.department);
    };


    render() {
        const tagOptions = [
            'Yellow', 'Green', 'Red', 'Black'
        ];
        const defaultTagOption = tagOptions[1];
        const deptOptions = [
            'General Care', 'Neonatal', 'Post-Operation'
        ];
        const defaultDeptOption = deptOptions[0];
        const {auth} = this.props;
        if(!auth.uid) return <Redirect to='/signin'/>
        return (
            <div className="container">
                <form className="white" onSubmit={this.handleSubmit}>
                    <h5 className="grey-text text-darken-3">Add a New Patient</h5>

                    <div className="input-field">
                        <label htmlFor="patientName">Patient Name</label>
                        <input type="text" id='patientName' onChange={this.handleChange} />
                    </div>
                    <div className="input-field">
                        <label htmlFor="description">Patient Description</label>
                        <textarea id="description" className="materialize-textarea" onChange={this.handleChange}></textarea>
                    </div>

                    <div className="input-field">
                        <label htmlFor="height">Patient Height</label>
                        <input type="text" id='height' onChange={this.handleChange} />
                    </div>

                    <div className="input-field">
                        <label htmlFor="weight">Patient Weight</label>
                        <input type="number" id='weight' onChange={this.handleChangeNumber1} />
                    </div>

                    <div className="input-field">
                        <label htmlFor="rHeartRate">Patient Resting Heart Rate</label>
                        <input type="number" id='rHeartRate' onChange={this.handleChangeNumber2} />
                    </div>

                    <div className="input-field">
                        <label htmlFor="triageTag">Triage Tag</label>
                        <Dropdown options={tagOptions} onChange={this.tagSelect} value={defaultTagOption} placeholder="Edit Triage Tag"/>
                    </div>

                    <div className="input-field">
                        <label htmlFor="department">Department</label>
                        <Dropdown options={deptOptions} onChange={this.deptSelect} value={defaultDeptOption} placeholder="Select your department" />
                    </div>

                    <div className="input-field">
                        <label htmlFor="bodyTempature">Body Temperature</label>
                        <input type="number" id='bodyTempature' onChange={this.handleChangeNumber3} />
                    </div>

                    <div className="input-field">
                        <label htmlFor="medications">Current Medication (Separate With Commas)</label>
                        <input type="text" id='medications' onChange={this.handleChange} />
                    </div>

                    <div className="input-field">
                        <label htmlFor="surgicaHistory">Surgical History (Separate With Commas)</label>
                        <input type="text" id='surgicaHistory' onChange={this.handleChange} />
                    </div>


                    <div className="input-field">
                        {/*<p>Test</p>*/}
                        <button className="btn red lighten-1" >Add Patient</button>
                        {/*<Link*/}
                        {/*    to="/" >*/}
                        {/*    <button className="btn red lighten-1">Add Patient</button>*/}

                        {/*</Link>*/}
                    </div>
                </form>
            </div>
        )
    }
}



const mapStateToProps = (state) => {
    return {
        auth: state.firebase.auth

    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        createPatient: (patient) => dispatch(createPatient(patient))
    }
};

export default connect(mapStateToProps,mapDispatchToProps)(CreatePatient)
