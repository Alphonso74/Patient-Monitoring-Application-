import React, {Component} from "react";
import {connect} from 'react-redux'
import {firestoreConnect} from "react-redux-firebase";
import {compose} from 'redux'
//import {Redirect} from 'react-router-dom'
import * as firebase from "firebase";
import mapDispatchToProps from "react-redux/es/connect/mapDispatchToProps";
import {updatePatient} from "../../store/actions/patientActions";


class DetailButtons extends Component{
    state = {
        order: '',
        newChart: '',
        nurse: ''
    };

    handleClick = (e) => {
        function updateOrder(patient, order){
            return firebase.update(patient, {standingOrder: order});
        }
        function updateChart(patient, chart){
            return firebase.update(patient, {chart: chart});
        }
        if (e.target.value === 'Standing Order'){
            this.setState({order: prompt("Enter the doctor's standing order:")});
            updateOrder(this.patient, this.state.order)
        }
        else if (e.target.value === 'Chart'){
            this.setState({newChart: prompt("Enter a note for the patient's chart:")});
            updateChart(this.patient, this.state.order)
        }
        else if (e.target.value === 'Assign Nurse'){
            this.setState({nurse: prompt("Enter nurse's name:")})
        }
    };

    render() {

        //const { patient } = this.props;

        return(
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

/*const mapDispatchToProps = (dispatch) => {
    return{
        updatePatient: (patient) => dispatch(updatePatient(patient))
    }
};*/

export default compose(
    connect(mapStateToProps, mapDispatchToProps),
    firestoreConnect([
        {collection: 'patients'}
    ])
)(DetailButtons)
