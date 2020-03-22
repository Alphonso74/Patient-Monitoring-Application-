const initState = {
    patients: [
        {id: '1', patientName: 'Alphonso Mckenzie',description: 'Sick sick sick', height: "5'10", weight: 220, rHeartRate: '75', triageTag: 'BLUE', bodyTempature: '95 degrees', medications: 'None', surgicaHistory: 'None',activeNurse: 'NA'  },
        {id: '2', patientName: 'Bob Billy',description: 'Badly Ill', height: "5'10", weight: 130, rHeartRate: '65', triageTag: 'RED', bodyTempature: '100 degrees', medications: 'None', surgicaHistory: 'None',activeNurse: 'NA' },
        {id: '3', patientName: 'Doug Dimmadome',description: 'Corona', height: "5'10", weight: 2220, rHeartRate: '80', triageTag: 'RED', bodyTempature: '103 degrees', medications: 'Surp', surgicaHistory: 'None',activeNurse: 'NA'  },
        {id: '4', patientName: 'Doug12311 Dimmadome',description: 'Corona', height: "5'10", weight: 2220, rHeartRate: '80', triageTag: 'RED', bodyTempature: '103 degrees', medications: 'Surp', surgicaHistory: 'None',activeNurse: 'NA'  }

    ]
}

const patientReducer = (state = initState, action) => {

    switch (action.type) {
        case 'CREATE_PATIENT':
        console.log('created patient', action.patient);
        return state;

        case 'CREATE_PATIENT_ERROR':
            console.log('create patient error', action.err);
            return state;
        default:
            return state;

    }


}


export default patientReducer ///()?
