import React, { Component } from 'react'
import PatientList from '../patients/PatientList'
import Notifications from './Notifications'
import {connect} from 'react-redux'
import {firestoreConnect} from "react-redux-firebase";
import {compose} from "redux";
import {Redirect} from 'react-router-dom'

class Dashboard extends Component {
  render() {

    const {patients, auth} = this.props;
    if(!auth.uid) return <Redirect to='/signin'/>
    return (
      <div className="dashboard container center" >
        {/*<div className="row " >*/}
          <div className="col s12 m6" >
            <PatientList patients={patients} />


          </div>
          {/*<div className="col s12 m5 offset-m1">*/}
          {/*  <Notifications />*/}
          {/*</div>*/}
        {/*</div>*/}
      </div>
    )
  }
}

///test

const mapStateToProps = (state) => {


  return {
    patients: state.firestore.ordered.patients,
      auth: state.firebase.auth
  }
};

export default compose(connect(mapStateToProps), firestoreConnect([{collection: 'patients'}]))(Dashboard)
