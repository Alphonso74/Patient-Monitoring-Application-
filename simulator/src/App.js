import React, { Component } from 'react';
import Simulator from './Simulator';
import Feed from './Feed';
import { Header } from 'react-mdl';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            running: null
        };
        this.startSim = this.startSim.bind(this);
    }

    startSim = () => {
        this.setState((prevState) => ({
            running: !prevState.running
        }));
    };

    render(){
        return(
            <div className="App">
                <Header className="teal darken-2" title="Patient+ Data Simulator" scroll />
                <div>

                    <div className="row">

                        <div className="col s12 m6">
                            <Feed running={this.state.running} />
                        </div>

                        <div className="col s12 m6">
                            <Simulator running={this.state.running} startSim={this.startSim} />
                        </div>

                    </div>
                </div>
            </div>
        );
    }
}

export default App;
