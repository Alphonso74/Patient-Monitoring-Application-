import firebase from "firebase/app";
import 'firebase/firestore'
import 'firebase/auth'
import 'firebase/analytics'


export var firebaseConfig = {
    apiKey: "AIzaSyB0ODKW_reMr84xHfAaPOeneey-LuOZbL0",
    authDomain: "patient-monitoring-syste-39706.firebaseapp.com",
    databaseURL: "https://patient-monitoring-syste-39706.firebaseio.com",
    projectId: "patient-monitoring-syste-39706",
    storageBucket: "patient-monitoring-syste-39706.appspot.com",
    messagingSenderId: "721554238180",
    appId: "1:721554238180:web:98224e49e7ba52badfdaf1",
    measurementId: "G-EFZKZEJ2MM"
};
firebase.initializeApp(firebaseConfig);
// Initialize Firebase
firebase.analytics();
firebase.firestore().settings({timestampsInSnapshots: true});




export default firebase;

// export default firebaseConfig;
