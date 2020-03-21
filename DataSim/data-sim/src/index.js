import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import { createStore } from 'redux'
import rootReducer from './Store/Reducers/RootReducer'
import { Provider } from 'react-redux'

const store = createStore(rootReducer); // root reducer

ReactDOM.render(<Provider store={store}><App /></Provider>Provider>, document.getElementById('root'));
registerServiceWorker();
