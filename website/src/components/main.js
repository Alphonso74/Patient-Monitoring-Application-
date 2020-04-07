import React from 'react';
import {Switch, Route, Link} from 'react-router-dom';
import LandingPage from './landingpage';
import Aboutus from './aboutus';
import Alphonso from './alphonso';
import Sean from './sean';
import Charles from './charles';
import Logan from './logan';
import DataSimulator from './datasimulator'
import Dhruvil from './Dhruvil';


import {Navigation} from "react-mdl";
//import Resume from './resume';

{/*<Link to="/alphonso">Alphonso Mckenzie</Link>*/}
{/*<Link to="/sean">Sean Todd</Link>*/}
{/*<Link to="/charles">Charles Todd</Link>*/}
{/*<Link to="/logan">Logan Kollar</Link>*/}
{/*<Link to="/dhruvilkumar">Dhruvilkumar Joshi</Link>*/}

const Main = () => (
  <Switch>
    <Route exact path="/" component={LandingPage} />
    <Route path="/aboutus" component={Aboutus} />
      <Route path="/alphonso" component={Alphonso} />
      <Route path="/sean" component={Sean} />
      <Route path="/charles" component={Charles} />
      <Route path="/logan" component={Logan} />
      <Route path="/dhruvilkumar" component={Dhruvil} />
    /* <Route path="/datasim" component={DataSimulator} />*/







      {/*<Route path="/resume" component={Resume} />*/}
  </Switch>
)

export default Main;
