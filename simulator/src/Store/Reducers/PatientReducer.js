const initState = {
    patients: [
        { id: '0', name: "Patient 1", hr: 70, tt: "Green" },
        { id: '1', name: "Patient 2", hr: 80, tt: "Blue" },
        { id: '2', name: "Patient 3", hr: 90, tt: "Red" },
        { id: '3', name: "Patient 4", hr: 100, tt: "Black" }
    ]
};

const PatientReducer = (state =  initState, action) => {
    switch(action.type){
        case 'UPDATE_PATIENTS':
            console.log("Patient data updated.");
            return state;
        default:
            return state;
    }
};

export default PatientReducer;