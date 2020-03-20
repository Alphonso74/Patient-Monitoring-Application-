import React, { Component } from 'react';
import Notifications from './Notifications';
import Feed from './Feed';
import { Header } from 'react-mdl';

class App extends Component {
    render(){
        return(
            <div className="App">
                <Header className="teal darken-2" title="Patient+ Data Simulator" scroll />
                <div>

                    <dic className="row">

                        <div className="col s12 m6">
                            <Feed />
                        </div>

                        <div className="col s12 m6">
                            <Notifications />
                        </div>

                    </dic>
                </div>
            </div>
        );
    }
}

export default App;
