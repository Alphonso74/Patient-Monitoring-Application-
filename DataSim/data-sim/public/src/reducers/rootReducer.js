import authReducer from "./authReducer";
import patientReducer from "./patientReducer";
import {combineReducers} from "redux";
import { firestoreReducer} from "redux-firestore";
import {firebaseReducer} from "react-redux-firebase";

const rootReducer =  combineReducers({

    auth: authReducer,
    patient: patientReducer,
    firestore: firestoreReducer,
    firebase: firebaseReducer


});

export default rootReducer
