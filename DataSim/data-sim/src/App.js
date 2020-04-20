import React, { Component } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom'
import Navbar from './components/layout/Navbar'
import Dashboard from './components/dashboard/Dashboard'
import ProjectDetails from './components/patients/PatientDetails'
import SignIn from './components/auth/SignIn'
import SignUp from './components/auth/SignUp'
import CreatePatient from './components/patients/CreatePatient';
import Datasim from './components/Sim/Components/Main';

class App extends Component {
  render() {
    return (
        <BrowserRouter>
          <div className="App">
            <Navbar />
            <Switch>
              <Route path='/' exact component={Dashboard} />
              <Route path='/patient/:id'exact component={ProjectDetails} />
              <Route path='/signin'exact component={SignIn} />
              <Route path='/signup' exact component={SignUp} />
              <Route path='/ds' exact component={Datasim} />
            </Switch>
          </div>
        </BrowserRouter>
    );
  }
}

export default App;
