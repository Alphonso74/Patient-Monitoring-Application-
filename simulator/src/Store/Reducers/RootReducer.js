import { combineReducers } from 'redux';
import PatientReducer from './PatientReducer';
import { firestoreReducer } from 'redux-firestore';

const RootReducer = combineReducers({
   patient: PatientReducer,
   firestore: firestoreReducer
});

export default RootReducer;