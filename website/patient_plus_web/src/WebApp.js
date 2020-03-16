import React from 'react';
import './App.css';

class WebApp extends React.Component {

    var config = {
        apiKey: "AIzaSyBXnXSNYzOmV-BfnzXU9f_WH3nF9uag2b4",
        authDomain: "patient-monitoring-syste-39706.firebaseapp.com",
        databaseURL: "https://patient-monitoring-syste-39706.firebaseio.com",
        projectId: "patient-monitoring-syste-39706",
        storageBucket: "patient-monitoring-syste-39706.appspot.com",
        messagingSenderId: "721554238180"
    };
    firebase.initializeApp(config);
    const db = firebase.firestore();
    db.settings({});
    console.log(db);

    render() {
        return (
            <ul id="patient_list" className="collection">
            </ul>
            <form id="add_patient_form">
                <input name="activeNurse" placeholder="Active Nurse">
                <input name="bodyTemperature" placeholder="Body Temperature">
                <input name="description" placeholder="description">
                <input name="height" placeholder="height">
                <input name="medications" placeholder="medications">
                <input name="patientName" placeholder="patient name">
                <input name="rHeartRate" placeholder="Resting Heart Rate">
                <input name="standingOrder" placeholder="Standing Order">
                <input name="surgicalHistory" placeholder="Surgical History">
                <input name="triageTag" placeholder="Triage Tag">
                <input name="weight" placeholder="Weight">
            </form>
        );
    }
}

export default WebApp;
