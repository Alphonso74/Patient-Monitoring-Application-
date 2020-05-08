import React, {Component, ReactPropTypes} from 'react'
import PatientSummary from './PatientSummary'
import Dropdown from "react-dropdown";
import 'react-dropdown/style.css';
import {compose} from "redux";
import {connect} from "react-redux";
import {firestoreConnect} from "react-redux-firebase";



class PatientList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      deptChoice: this.props.user.department,
      user: this.props.user,
      patients: this.props.patients
    };
    this.feedSelect = this.feedSelect.bind(this);
    if (this.state.deptChoice === "Post Op") this.state.deptChoice = "Post-Operation";
    //console.log(this.props);
   // console.log(this.state.deptChoice);
  }
  feedSelect = (option) => {
    this.setState({deptChoice: option.value});
  };

  render() {

    const feedOptions = [
          'All',
          'General Care',
          'Neonatal',
          'Post-Operation'
    ];
    let defaultFeed;
    if (this.state.deptChoice === "General Care")
      defaultFeed = feedOptions[1];
    else if (this.state.deptChoice === "Neonatal")
      defaultFeed = feedOptions[2];
    if (this.state.deptChoice === "Post-Operation")
      defaultFeed = feedOptions[3];

    return (
        <div className="project-list section">
          <div><Dropdown options={feedOptions} onChange={this.feedSelect} value={defaultFeed} placeholder="Switch Feeds"/></div>


          {this.state.patients && this.state.patients.map(patient => {
            if (this.state.deptChoice === 'All') {
              return (
                  <div>
                    <PatientSummary patient={patient}/>
                  </div>
              )
            }
            else if (patient.department === this.state.deptChoice) {
              return (
                  <div>
                    <PatientSummary patient={patient}/>
                  </div>
              )
            }
          })};


        </div>
    )
  }
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

export default compose(connect(mapStateToProps), firestoreConnect([{collection: 'patients3'}, {collection: 'Users'}]))(PatientList)
