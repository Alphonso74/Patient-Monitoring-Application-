import React, { Component } from 'react';
import { BrowserRouter, Switch, Route, withRouter } from 'react-router-dom'
import Navbar from './components/layout/Navbar'
import Dashboard from './components/dashboard/Dashboard'
import ProjectDetails from './components/patients/PatientDetails'
import SignIn from './components/auth/SignIn'
import SignUp from './components/auth/SignUp'
import CreatePatient from './components/patients/CreatePatient';
import EditPatient from './components/patients/EditPatient';
import Main from './components/Sim/Main';

class App extends Component {
  render() {
    return (
        <BrowserRouter>
          <div className="App">
            <Navbar />
            <Switch>
              <Route exact path='/' component={Dashboard} />
              <Route path='/patient/:id' component={ProjectDetails} />
              <Route path='/signin' component={SignIn} />
              <Route path='/signup' component={SignUp} />
              <Route path='/create' component={CreatePatient} />
              <Route path='/edit/:id' component={EditPatient} />
              <Route path='/ds' component={withRouter(Main)} />
              <Route path='/chat' component={Chat} />
            </Switch>
          </div>
        </BrowserRouter>
    );
  }
}

export default App;
