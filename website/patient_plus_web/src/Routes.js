import React from 'react';
import { BrowserRouter, Route, Switch, Link } from 'react-router-dom';

import App from './App.js';
import Simulator from './Simulator.js';

class Routes extends React.Component {
    render(){
        return(
            <BrowserRouter>
                <nav>
                    <div className="nav-wrapper">
                        <a href="#" className="brand-logo center">Patent+</a>
                        <a href="#" data-target="mobile-id" className="sidenav-trigger">
                            <i className="material-icons">menu</i></a>
                        <ul className="left hide-on-med-and-down">
                            <li><Link to="/"><i className="material-icons left">home</i>Home</Link></li>
                            <li><a href="#"><i className="material-icons left">file_download</i>Download</a></li>
                            <li><a href="#"><i className="material-icons left">local_library</i>Application</a></li>
                            <li><a href="#"><i className="material-icons left">assignment_ind</i>About</a></li>
                            <li><a href="#"> <i className="material-icons left">call_end</i>Contact Us</a></li>
                            <li><Link to="/simulator"><i className="material-icons left">healing</i>Simulator</Link></li>
                        </ul>
                    </div>
                </nav>

                <Switch>
                    <Route exact path="/" component={App} />
                    <Route path="/simulator" component={Simulator} />
                </Switch>

            </BrowserRouter>
        );
    }
}

export default Routes;