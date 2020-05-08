import firebase from 'firebase';

class firebase {

    constructor() {
        this.init();
    }
    init = () => {
        if (!firebase.apps.length) {
            firebase.initializeApp({
                apiKey: 'AIzaSyBXnXSNYzOmV-BfnzXU9f_WH3nF9uag2b4'
/*
                authDomain: ''
*/
                databaseURL: 'https://patient-monitoring-syste-39706.firebaseio.com/'
                projectId: 'patient-monitoring-syste-39706'
            });
        }
};
    get uid() {
    return (firebase.auth().currentUser || {}).uid;
}
    get ref() {
        return firebase.database().ref('messages');
    }

    parse = snapshot => {
        const { timestamp: numberStamp, text, user } = snapshot.val();
        const { key: _id } = snapshot;
        const timestamp = new Date(numberStamp);
        const message = {
            _id,
            timestamp,
            text,
            user,
        };
        return message;
    };

    on = callback =>
        this.ref
            .limitToLast(20)
            .on('child_added', snapshot => callback(this.parse(snapshot)));

    get timestamp() {
        return firebase.database.ServerValue.TIMESTAMP;
    }
    // send the message to the Backend
    send = messages => {
        for (let i = 0; i < messages.length; i++) {
            const { text, user } = messages[i];
            const message = {
                text,
                user,
                timestamp: this.timestamp,
            };
            this.append(message);
        }
    };
    append = message => this.ref.push(message);

    // close the connection to the Backend
    off() {
        this.ref.off();
    }
}

Fire.shared = new Fire();
export default Fire;
