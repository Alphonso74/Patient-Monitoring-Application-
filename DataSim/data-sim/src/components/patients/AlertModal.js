import React, { Component } from 'react';
import { Modal, Button } from 'react-materialize';

class AlertModal extends Component {
    constructor(props){
        super(props);
    }

    /*componentDidMount() {
        this.modalButton.click();
    }*/

    render() {
        switch (this.props.alert) {
            case "High HR":
                return (
                    <div>
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
                                preventScrolling: false,
                                startingTop: '4%'
                            }}
                            trigger={
                                <Button style={{display: 'none'}}
                                        ref={button => this.modalButton = button}>Show Alert</Button>
                            }
                        >
                            <p>{this.props.patient.patientName}'s heart rate is too high!</p>
                        </Modal>
                    </div>
                )
            case "Low HR":
                return (
                    <div>
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
                                preventScrolling: false,
                                startingTop: '4%'
                            }}
                            trigger={
                                <Button style={{display: 'none'}}
                                        ref={button => this.modalButton = button}>Show Alert</Button>
                            }
                        >
                            <p>{this.props.patient.patientName}'s heart rate is too low!</p>
                        </Modal>
                    </div>
                )
            case "High Temp":
                return (
                    <div>
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
                                preventScrolling: false,
                                startingTop: '4%'
                            }}
                            trigger={
                                <Button style={{display: 'none'}}
                                        ref={button => this.modalButton = button}>Show Alert</Button>
                            }
                        >
                            <p>{this.props.patient.patientName}'s temperature is too high!</p>
                        </Modal>
                    </div>
                )
            case "Low Temp":
                return (
                    <div>
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
                                preventScrolling: false,
                                startingTop: '4%'
                            }}
                            trigger={
                                <Button style={{display: 'none'}}
                                        ref={button => this.modalButton = button}>Show Alert</Button>
                            }
                        >
                            <p>{this.props.patient.patientName}'s temperature is too low!</p>
                        </Modal>
                    </div>
                )
            default:
                return null;
        }
    }
}

export default AlertModal;