import React, { Component } from 'react'
import PatientList from '../patients/PatientList'
//import Notifications from './Notifications'
import {connect} from 'react-redux'
import {firestoreConnect} from "react-redux-firebase";
import {compose} from "redux";
import {Redirect} from 'react-router-dom'
import Dropdown from "../patients/EditPatient";
import * as firebase from "firebase";

class Dashboard extends Component {
  state = {
   feedChoice: 'General Care'
  };

  onSelect = (option) =>{
    this.setState({feedChoice: option.value});
    console.log(this.state.feedChoice);
  };

  render() {
    const {patients, auth, user} = this.props;
    console.log(patients);
    console.log(user);
    let feed = '';
    let pats = [];
    //let patDept = '';
    if (user) {
      feed = user.department;
      if (feed === "Post Op") feed = "Post-Operation";
      //patDept = patients.department;

      firebase.firestore().collection("patients3").where("department", "==", feed)
          .get()
          .then(function (querySnapshot) {
            querySnapshot.forEach(function (doc) {
              // doc.data() is never undefined for query doc snapshots
              console.log(doc.id, " => ", doc.data());
              pats = [...pats, doc.data()];
              console.log(pats);

            })
          })
          .catch(function (error) {
            console.log("Error getting documents: ", error);
          });
    }

    const feedOptions = [
        'General Care', 'Neonatal', 'Post-Operation'
    ];
    if(!auth.uid) return <Redirect to='/signin'/>;
    //console.dir(auth.uid);
    /*const feedRef = firebase.database().ref("Users/").orderByKey().equalTo(auth.uid).on("child_added", function(data){
      this.setState({feed: data.department});
      console.dir(data);
    }.bind(this));//.equalTo(auth.uid);
    console.dir(this.state.feed);*/
    /*let user = feedRef.orderByKey().equalTo(auth.uid).on("value", function(data){
      console.dir(data.val());
    });*/

    //let feed = user.department;
    //console.log(feed);
    //const patientRef = firebase.database().ref("patients3/");


    return (
        <div className="dashboard container center" >
        {/*<div className="row " >*/}
          <div className="col s12 m6" >

            <PatientList patients={patients} />
            {/*<Dropdown options={feedOptions} onChange={this.onSelect} value={this.state.feedChoice} placeholder="Switch Feeds"/>*/}

          </div>
          {/*<div className="col s12 m5 offset-m1">*/}
          {/*  <Notifications />*/}
          {/*</div>*/}
        {/*</div>*/}
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  const id = state.firebase.auth.uid;
  const users = state.firestore.data.Users;
  const user = users ? users[id] : null;
  return {
    patients: state.firestore.ordered.patients3,
      auth: state.firebase.auth,
    user: user
  }
};

export default compose(connect(mapStateToProps), firestoreConnect([{collection: 'patients3'}, {collection: 'Users'}]))(Dashboard)
