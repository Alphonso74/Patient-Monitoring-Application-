import React, { Component } from 'react';
import Simulator from './Components/Simulator';
import Feed from './Components/Feed';
import { Header } from 'react-mdl';
import { connect } from 'react-redux';
import { firestoreConnect} from "react-redux-firebase";
import { compose } from "redux";

class App extends Component {
    render(){
        const { patients } = this.props;
        return(
            <div className="App">
                <Header className="teal darken-2" title="Patient+ Data Simulator" scroll />
                <div>

                    <div className="row">

                        <div className="col s12 m6">
                            <Feed patients={patients} />
                        </div>

                        <div className="col s12 m6">
                            <Simulator />
                        </div>

                    </div>
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        patients: state.firestore.ordered.patients3
    }
};

export default compose(
    connect(mapStateToProps),
    firestoreConnect([ { collection: 'patients3' } ] )
)(App);
