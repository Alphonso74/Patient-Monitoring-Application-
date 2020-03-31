

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

export const updatePatient = (patient, id) => {
    return(dispatch, getState, {getFirebase, getFirestore}) => {
        const firestore = getFirestore();

        firestore.collection('patients').doc(id).delete().then(()=> console.log("Delete successful!"))
            .catch((err)=>{dispatch({type: 'UPDATE_PATIENT_ERROR', err});
        });

        firestore.collection('patients').add({
            ...patient
        }).then(()=>{
            dispatch({type: 'UPDATE_PATIENT', patient});
        }).catch((err) =>{
            dispatch({type:'UPDATE_PATIENT_ERROR', err});
        })
    }
};

export const deletePatient = (patient, id) => {
    return(dispatch, getState, {getFirebase, getFirestore}) => {
        const firestore = getFirestore();

        firestore.collection('patients').doc(id).delete().then(()=> {
            dispatch({type: 'DELETE_PATIENT', patient});
        })
            .catch((err)=>{dispatch({type: 'DELETE_PATIENT_ERROR', err});
            });
    }
};
