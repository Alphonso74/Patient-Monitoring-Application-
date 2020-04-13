import firebase from 'firebase/app';
import 'firebase/firestore';
import 'firebase/analytics';

// Your web app's Firebase configuration
var firebaseConfig = {
    apiKey: "AIzaSyB0ODKW_reMr84xHfAaPOeneey-LuOZbL0",
    authDomain: "patient-monitoring-syste-39706.firebaseapp.com",
    databaseURL: "https://patient-monitoring-syste-39706.firebaseio.com",
    projectId: "patient-monitoring-syste-39706",
    storageBucket: "patient-monitoring-syste-39706.appspot.com",
    messagingSenderId: "721554238180",
    appId: "1:721554238180:web:108f787ed05b3a49dfdaf1",
    measurementId: "G-G6TMTR9P65"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);
firebase.analytics();
firebase.firestore().settings({});

export default firebase;