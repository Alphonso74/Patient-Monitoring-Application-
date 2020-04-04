export const UpdatePatient = (patient, value) => {
    return(dispatch, getState, { getFirebase, getFirestore }) => {
        // access db
        const firestore = getFirestore();
        firestore.database().set( { hr: value } ).then(() => { dispatch({ type: 'UPDATE_PATIENT', patient }); });
    }
};