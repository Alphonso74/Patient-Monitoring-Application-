import React, { Component } from 'react';
import { Header } from 'react-mdl';
import { connect } from 'react-redux';
import { firestoreConnect} from "react-redux-firebase";
import { compose } from "redux";

class datasimulator extends Component {
    render(){
        return(
            <div>
                <h4>
                    MAke it Fancy
                </h4>

                <a>
                    https://www.mediafire.com/file/l81f0dh3g4exo4o/app-debug.apk/file
                </a>

            </div>
        );
    }
}

export default datasimulator;