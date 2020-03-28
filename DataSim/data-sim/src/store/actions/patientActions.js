

export const createPatient = (patient) =>{

    return(dispatch,getState, {getFirebase, getFirestore}) => {
        // make a sync call to db

        const firestore = getFirestore();
        // console.log('Test');

        firestore.collection('patients3').add({

            ...patient

            // authorFirstName: 'Net'
        }).then(() =>{
            console.log('Test');
            dispatch({type: 'CREATE_PATIENT', patient});


        }).catch((err) => {
            dispatch({type: 'CREATE_PATIENT_ERROR', err});
        })



    }
}
