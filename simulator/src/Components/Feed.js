import React from 'react';
import PatientList from './PatientList';

/*class Feed extends Component {
    componentDidMount(){
        this.interval = setInterval(() => {this.randomize()}, 1000);
    }
    componentWillUnmount(){
        clearInterval(this.interval);
    }
}*/

const Feed = ({patients}) => {
    return (
        <div className="section">
            { patients && patients.map(patient => {
                return(
                    <PatientList patient={patient} key={patient.id}/>
                )
            })}
        </div>
    )
};

export default Feed;