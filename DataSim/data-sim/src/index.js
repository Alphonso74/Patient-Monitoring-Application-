import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import { createStore, applyMiddleware, compose} from "redux";
import rootReducer from "./reducers/rootReducer";
import {Provider} from "react-redux";
import thunk from "redux-thunk";
// import {getFirestore} from 'redux-firestore'
// import {getFirebase} from 'react-redux-firebase'
import {reduxFirestore, getFirestore, createFirestoreInstance} from "redux-firestore";
import { reactReduxFirebase,getFirebase,ReactReduxFirebaseProvider} from "react-redux-firebase";
// import firebaseConfig from './config/firebaseConfig'
import firebase, { firebaseConfig } from './config/firebaseConfig'
import {useSelector} from "react-redux";
import {isLoaded} from "react-redux-firebase";

const store = createStore(rootReducer,
    compose(applyMiddleware(thunk.withExtraArgument({getFirebase,getFirestore})),
        reduxFirestore(firebase,firebaseConfig),
        // reactReduxFirebase(firebaseConfig)
    )
);

const profileSpecificProps = {
    userProfile: 'Users',
    useFirestoreForProfile: true,
    enableRedirectHandling: false,
    resetBeforeLogin: false
}

const rrfProps = {
    firebase,
    config: firebaseConfig,
    config: profileSpecificProps,
    dispatch: store.dispatch,
    createFirestoreInstance,
    userProfile: 'Users',
    sessions: 'sessions'
}

function AuthIsLoaded({children}){
    const auth = useSelector(state => state.firebase.auth)
    if(!isLoaded(auth)) return <div>Loading Screen.....</div>;
    return children
}

ReactDOM.render(
    <Provider store={store}>
    <ReactReduxFirebaseProvider {...rrfProps}>
   <AuthIsLoaded> <App/></AuthIsLoaded> </ReactReduxFirebaseProvider>
    </Provider>,
document.getElementById('root'));

registerServiceWorker();
