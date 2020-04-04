export const UpdatePatient = (patient, value) => {
    return(dispatch, getState, { getFirebase, getFirestore }) => {
        // access db
        const firestore = getFirestore();
        firestore.collection('patients').doc(patient.id).set( { name: patient.name, hr: value, tt: patient.tt } ).then(() => { dispatch({ type: 'UPDATE_PATIENT', patient }); });
    }
};