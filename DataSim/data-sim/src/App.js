import React, { Component } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom'
import Navbar from './components/layout/Navbar'
import Dashboard from './components/dashboard/Dashboard'
import ProjectDetails from './components/patients/PatientDetails'
import SignIn from './components/auth/SignIn'
import SignUp from './components/auth/SignUp'
import CreatePatient from './components/patients/CreatePatient';
import EditPatient from './components/patients/EditPatient';
import DS from './components/Sim/Components/Main';
import Chat from './components/dashboard/Chat'

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
                <Route path='/chat' component={Chat}/>
              <Route path='/ds' component={DS}/>
            </Switch>
          </div>
        </BrowserRouter>
    );
  }
}

export default App;
