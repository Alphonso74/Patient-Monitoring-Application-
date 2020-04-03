import firebase from 'firebase/app';
import 'firebase/firestore';

// Your web app's Firebase configuration
var firebaseConfig = {
    apiKey: "AIzaSyDxAY67_nHCZSirHgkMQ_lyraHRvsR1HHk",
    authDomain: "patient-db-test.firebaseapp.com",
    databaseURL: "https://patient-db-test.firebaseio.com",
    projectId: "patient-db-test",
    storageBucket: "patient-db-test.appspot.com",
    messagingSenderId: "997536831238",
    appId: "1:997536831238:web:ad8a0e72e5962d8aaeb087",
    measurementId: "G-JJQSGKGZPV"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);
//firebase.analytics();
firebase.firestore().settings({});

export default firebase;