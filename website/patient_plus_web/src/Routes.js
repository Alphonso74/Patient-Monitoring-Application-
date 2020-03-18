import React from 'react';
import { BrowserRouter, IndexRoute, Route, Switch } from 'react-router-dom';

import App from './App.js';
import Simulator from './Simulator.js';

export default (
    <BrowserRouter>
        <Switch>
            <Route exact path={"/"} component={App} />
            <Route path="/simulator" component={Simulator} />
        </Switch>
    </BrowserRouter>
);