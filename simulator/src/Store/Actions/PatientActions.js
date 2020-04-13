export const UpdatePatient = (patient, heartRate, bodyTemp) => {
    return(dispatch, getState, { getFirebase, getFirestore }) => {
        // access db
        const firestore = getFirestore();
        firestore.collection('patients3').doc(patient.id).update( { rHeartRate: heartRate, bodyTempature: bodyTemp } ).then(() => { dispatch({ type: 'UPDATE_PATIENT', patient }); });
    }
};