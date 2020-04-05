export const UpdatePatient = (patient, heartRate, bodyTemp) => {
    return(dispatch, getState, { getFirebase, getFirestore }) => {
        // access db
        const firestore = getFirestore();
        firestore.collection('patients').doc(patient.id).update( { hr: heartRate, temp: bodyTemp } ).then(() => { dispatch({ type: 'UPDATE_PATIENT', patient }); });
    }
};