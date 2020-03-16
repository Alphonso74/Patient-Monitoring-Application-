import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';
const ul = document.getElementById("patient_list");
//const form = document.getElementById("add_patient_form");

function renderList(doc) {

    let li = document.createElement('li');
    li.className = "collection-item";
    li.setAttribute("data-id", doc.id);

    let name = document.createElement('span');
    name.className = "title";

    let tag = document.createElement('p');
    if (doc.data().triageTag == "Red") {tag.className = "red-text"}
    else if (doc.data().triageTag == "Blue") {tag.className = "blue-text"}
    else if (doc.data().triageTag == "Green") {tag.className = "green-text"}

    name.textContent = doc.data().patientName;
    tag.textContent = doc.data().triageTag;

    let i = document.createElement('i');
    i.className = "material-icons secondary-content red-text";
    i.textContent = "delete";

    i.addEventListener('click', (e) => {
        confirmAlert({
            title: 'Delete Patient',
            message: 'Are you sure you want to delete this patient?',
            buttons: [
                {
                    label: 'Yes',
                    onClick: () => {
                        e.stopPropagation();
                        let id = e.target.parentElement.getAttribute("data-id");
                        db.collection("patients").doc(id).delete();
                        alert("deleted!");
                    }
                },
                {
                    label: 'No',
                    onClick: () => {}
                }
            ]
        });
        
    })

    li.appendChild(i);
    li.appendChild(name);
    li.appendChild(city);
    ul.appendChild(li);
}
// Get data
db.collection("patients").orderBy("id").onSnapshot(
    snapshot => {
        let changes = snapshot.docChanges();
        console.log(changes)
        changes.forEach(change => {
            console.log(change.doc.data());
            if (change.type == "added") {
                renderList(change.doc);
            }else if (change.type == "removed") {
                let li = ul.querySelector('[data-id=' + change.doc.id + ']');
                ul.removeChild(li);
            }
        })
    }
);
