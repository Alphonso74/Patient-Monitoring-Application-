import React, { Component } from 'react';
import { PatientList, patients } from './PatientList';

class Feed extends Component {
    randomize = () => {
        if(this.props.running){
            if(patients[1].tt == "Green") {
                let rand = Math.random() * 100;

                if (rand <= 33) {
                    patients[1].hr = patients[1].hr + 1;
                } else if (rand > 66) {
                    patients[1].hr = patients[1].hr - 1;
                }
            }
            else if(patients[1].tt == "Blue") {
                let rand = Math.random() * 100000;

                if (rand <= 33000) {
                    patients[1].hr = patients[1].hr + 1;
                } else if (rand > 66000 && rand !== 99999) {
                    patients[1].hr = patients[1].hr - 1;
                }
                else if(rand === 99999) {
                    patients[1].hr = 0;
                }
            }
            else if(patients[1].tt == "Red") {
                let rand = Math.random() * 10000;

                if (rand <= 3300) {
                    patients[1].hr = patients[1].hr + 1;
                } else if (rand > 6600 && rand !== 9999) {
                    patients[1].hr = patients[1].hr - 1;
                }
                else if(rand === 9999) {
                    patients[1].hr = 0;
                }
            }
            else if(patients[1].tt == "Black") {
                let rand = Math.random() * 1000;

                if (rand <= 330) {
                    patients[1].hr = patients[1].hr + 1;
                } else if (rand > 660 && rand !== 999) {
                    patients[1].hr = patients[1].hr - 1;
                }
                else if(rand === 999) {
                    patients[1].hr = 0;
                }
            }
            console.log(patients[1].hr);
        }
    };
    componentDidMount(){
        this.interval = setInterval(() => {this.randomize()}, 1000);
    }
    componentWillUnmount(){
        clearInterval(this.interval);
    }
    render() {
        return (
            <div className="section">
                {PatientList}
            </div>
        )
    }
}

export default Feed;