import React, {Component} from "react";
import {connect} from 'react-redux'
import {firestoreConnect} from "react-redux-firebase";
import {compose} from 'redux'
import {Redirect} from 'react-router-dom'

class DetailButtons extends Component {
    handleClick = (e) => {
        if (e.target.value === 'Standing Order'){
            this.setState(this.patient.standingOrder = prompt("Enter the doctor's standing order:"));
        }
        else if (e.target.value === 'Chart'){
            //patient.chart = prompt("Enter chart information:")
        }
        else if (e.target.value === 'Assign Nurse'){
            this.setState(this.patient.activeNurse = prompt("Enter nurse's name:"))
        }
    };

    render() {
        return (
            <div className="container center">
                <p>
                    <input type="button" className="" value="Standing Order" onClick={this.handleClick}/>
                    <input type="button" className="" value="Chart" onClick={this.handleClick}/>
                </p>
                <p>
                    <input type="button" className="" value="Assign Nurse" onClick={this.handleClick}/>
                </p>
            </div>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    const id = ownProps.patient.id;
    const patients = state.firestore.data.patients;
    const patient = patients ? patients[id] : null;
    return{

        patient: patient,
        auth: state.firebase.auth

    }
};

export default compose(
    connect(mapStateToProps),
    firestoreConnect([
        {collection: 'patients'}
    ])
)(DetailButtons)
