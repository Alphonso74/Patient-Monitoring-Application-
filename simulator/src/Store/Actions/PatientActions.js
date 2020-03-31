export const UpdatePatients = (patients) => {
    return(dispatch, getState, { getFirebase, getFirestore }) => {
        // access db
        const firestore = getFirestore();

        dispatch({ type: 'UPDATE_PATIENTS', patients });
    }
};