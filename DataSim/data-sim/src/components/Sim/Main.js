import React, { Component } from 'react';
import Simulator from './Simulator';
import Feed from './Feed';
import { connect } from 'react-redux';
import { firestoreConnect} from "react-redux-firebase";
import { compose } from "redux";

class Main extends Component {
    render(){
        const { patients } = this.props;
        return(
            <div className="App">
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
)(Main);
