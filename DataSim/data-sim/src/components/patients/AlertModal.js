import React from 'react';
import { Modal, Button } from 'react-materialize';

const AlertModal = ({patient}, alertType) => {
    switch(alertType){
        case "High HR":
            return(
                <Modal
                    actions={[
                        <Button flat modal="close" node="button" waves="red">Close</Button>
                    ]}
                    bottomSheet={false}
                    fixedFooter={false}
                    header="Alert"
                    id="Modal-0"
                    open={true}
                    options={{
                        dismissible: true,
                        endingTop: '10%',
                        inDuration: 250,
                        onCloseEnd: null,
                        onCloseStart: null,
                        onOpenEnd: null,
                        onOpenStart: null,
                        opacity: 0.5,
                        outDuration: 250,
                        preventScrolling: true,
                        startingTop: '4%'
                    }}
                >
                    <p>{patient.patientName}'s heart rate is too high!</p>
                </Modal>
            )
        case "Low HR":
            return(
                <Modal
                    actions={[
                        <Button flat modal="close" node="button" waves="red">Close</Button>
                    ]}
                    bottomSheet={false}
                    fixedFooter={false}
                    header="Alert"
                    id="Modal-1"
                    open={true}
                    options={{
                        dismissible: true,
                        endingTop: '10%',
                        inDuration: 250,
                        onCloseEnd: null,
                        onCloseStart: null,
                        onOpenEnd: null,
                        onOpenStart: null,
                        opacity: 0.5,
                        outDuration: 250,
                        preventScrolling: true,
                        startingTop: '4%'
                    }}
                >
                    <p>{patient.patientName}'s heart rate is too low!</p>
                </Modal>
            )
        case "High Temp":
            return(
                <Modal
                    actions={[
                        <Button flat modal="close" node="button" waves="red">Close</Button>
                    ]}
                    bottomSheet={false}
                    fixedFooter={false}
                    header="Alert"
                    id="Modal-2"
                    open={true}
                    options={{
                        dismissible: true,
                        endingTop: '10%',
                        inDuration: 250,
                        onCloseEnd: null,
                        onCloseStart: null,
                        onOpenEnd: null,
                        onOpenStart: null,
                        opacity: 0.5,
                        outDuration: 250,
                        preventScrolling: true,
                        startingTop: '4%'
                    }}
                >
                    <p>{patient.patientName}'s temperature is too high!</p>
                </Modal>
            )
        case "Low Temp":
            return(
                <Modal
                    actions={[
                        <Button flat modal="close" node="button" waves="red">Close</Button>
                    ]}
                    bottomSheet={false}
                    fixedFooter={false}
                    header="Alert"
                    id="Modal-3"
                    open={true}
                    options={{
                        dismissible: true,
                        endingTop: '10%',
                        inDuration: 250,
                        onCloseEnd: null,
                        onCloseStart: null,
                        onOpenEnd: null,
                        onOpenStart: null,
                        opacity: 0.5,
                        outDuration: 250,
                        preventScrolling: true,
                        startingTop: '4%'
                    }}
                >
                    <p>{patient.patientName}'s temperature is too low!</p>
                </Modal>
            )
        default:
            return null;
    }
}

export default AlertModal;