import React, { Component } from 'react'
import PatientList from '../patients/PatientList'
//import Notifications from './Notifications'
import {connect} from 'react-redux'
import {firestoreConnect} from "react-redux-firebase";
import {compose} from "redux";
import {Redirect} from 'react-router-dom'
import Spinner from "react-materialize/lib/Spinner";
//import Dropdown from "../patients/EditPatient";
//import * as firebase from "firebase";

class Dashboard extends Component {
  state = {
    //feedChoice: "",
    //pats: [],
    //patFeed: [],
    //isAuthenticating: true
  };

  /*componentDidMount() {
    //this.authUser().then((user) => {
      this.loadStuff();
     // this.setState({ isAuthenticating: false });
    //}, (error) => {
      //this.setState({ isAuthenticating: false });
      //alert(error);
    //});

  }*/

  onSelect = (option) => {
    this.setState({feedChoice: option.value});
    console.log(this.state.feedChoice);
  };

  /*shouldComponentUpdate(nextProps, nextState, nextContext) {
    if (this.props.user)
    return !nextState.pats;
    else return true;
  }*/

  /*async loadStuff() {
    //if (this.props.user) {

    let pats = [];
    //if (!this.props.user) await this.props.user;
    if (!this.props.user) {
      //await this.props.user;
    }
    console.log("Dept in loadStuff: " + this.props.user.department);
    await this.setState({feedChoice: this.props.user.department});

    if (this.state.feedChoice === "Post Op") await this.setState({feedChoice: "Post-Operation"});
    //patDept = patients.department;
    console.log("feedChoice: " + this.state.feedChoice);
    firebase.firestore().collection("patients3").where("department", "==", this.state.feedChoice)
        .get()
        .then(function (querySnapshot) {
          querySnapshot.forEach(function (doc) {
            // doc.data() is never undefined for query doc snapshots
            //console.log(doc.id, " => ", doc.data());
            pats = [...pats, doc.id];
            console.dir("Pats in Load: " + pats);
          })
        })
        .catch(function (error) {
          console.log("Error getting documents: ", error);
        });

    console.log("Pats after Load: " + pats);
    this.setState({pats: pats});
  }*/
      //this.setState({pats: pats});
      //let patFeed = [];
      //console.log("Pats state in render: " + this.state.pats);
      //let i = 0;
      /*await this.state.pats.forEach(function(pat){
        firebase.database().ref('patients3/').child(pat).once("value", function(snapshot) {
          console.log(snapshot);
          patFeed = [...patFeed, snapshot];
          //console.dir("PatFeed: " + patFeed);
        })
      });*/
      /*  i++;
        console.log("index: " + i);
        console.dir("patFeed: " + patFeed);
      });*/
      //console.dir("patFeed: " + patFeed);
      //}
      //}
  render() {
    //if (this.state.isAuthenticating) return null;
    const {patients, auth, user} = this.props;
    //console.dir(patients);
    //console.dir(user);
    //let feed = '';
    //let pats = [];
    //let patDept = '';
    //if (user) {

    if (!auth.uid) return <Redirect to='/signin'/>;
    //console.dir("Pats:" + this.state.pats);
    //if (!this.state.pats) return <div>Loading... <spinner/></div>;
    //console.dir("Pats in render: " + this.state.props);
    if (user) {
      return (
          <div className="dashboard container center">
            {/*<div className="row " >*/}
            <div className="col s12 m6">

              <PatientList patients={patients} user={user}/>
              {/*<Dropdown options={feedOptions} onChange={this.onSelect} value={this.state.feedChoice} placeholder="Switch Feeds"/>*/}

            </div>
            {/*<div className="col s12 m5 offset-m1">*/}
            {/*  <Notifications />*/}
            {/*</div>*/}
            {/*</div>*/}
          </div>);
    } else {
      return <div>Loading user... <Spinner/></div>
    }
  }
  /*authUser() {
    return new Promise(function (resolve, reject) {
      firebase.auth().onAuthStateChanged(function(user) {
        if (user) {
          resolve(user);
        } else {
          reject('User not logged in');
        }
      });
    });
  }*/




    /*const feedOptions = [
      'General Care', 'Neonatal', 'Post-Operation'
    ];*/
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


}

const mapStateToProps = (state) => {
  const id = state.firebase.auth.uid;
  const users = state.firestore.data.Users;
  const user = users ? users[id] : null;
  console.log("User: " + user);
  return {
    patients: state.firestore.ordered.patients3,
    auth: state.firebase.auth,
    user: user
  }
};

export default compose(connect(mapStateToProps), firestoreConnect([{collection: 'patients3'}, {collection: 'Users'}]))(Dashboard)
