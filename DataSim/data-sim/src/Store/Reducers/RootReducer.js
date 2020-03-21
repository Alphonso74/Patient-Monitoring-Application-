import projectReducer from './ProjectReducer'
import authReducer from './authReducer'
import { combineReducers } from 'redux'

const rootReducer = combineReducers({

    auth: authReducer,
    project: rojectReducer

})

export  default rootReducer