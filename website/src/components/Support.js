import React, { Component } from 'react';
// import { Grid, Cell, List, ListItem, ListItemContent } from 'react-mdl';
import { Grid, Cell } from 'react-mdl';
import patientP from '/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/patientplusimage.png';
import { Link } from 'react-router-dom';
import im1 from '/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/IMG_7901.JPG';
import im2 from '/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/IMG_7902.JPG';
import im3 from '/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/IMG_7903.JPG';
import im4 from '/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/IMG_7904.JPG';
import im6 from '/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/IMG_7906.JPG';
import im7 from '/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/IMG_7907.JPG';
import im8 from '/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/IMG_7908.JPG';
import im9 from '/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/IMG_7909.JPG';
import im10 from '/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/IMG_7910.JPG';

class Support extends Component {


    render() {

        return(
            <div style={{paddingTop: '2em'}}>
                <Grid>
                    <Cell col={4} style={{paddingLeft: '5em'}}>

                </Cell>

                    <Cell className="right-col" col={8} style = {{paddingRight: "12m"}}>

                        <h4>How To Use</h4>

                        <p>In this section we will give you a brief tutorial on how to use this product.</p>

                            <div>
                                <h4>Logging In</h4>
                                <img src={im1} style={{height: 'auto',width: '30%', textAlign:'right'}} alt="NOPE.avi" />
                                <p>  You will be given a Username/password by you Hospital. You will be required to login upon first use. Once logged in you will be connected to your departments feed of patients.</p>
                            </div>


                        <div>
                            <h4>Department Feeds</h4>
                            <img src={im6} style={{height: 'auto',width: '20%'}} alt="NOPE.avi" />
                            <p> This displays all of the current patients inside your assigned department.
                                If for any reason you need to check what patients are in a different work area,
                                tap the department name. This will open a dialog box. Select more options and pick
                                your desired feed. The Display will now show all patients belong to that selected department.</p>

                        </div>

                        <div>
                            <h4> Filter </h4>
                            <img src={im10} style={{height: 'auto',width: '20%'}} alt="NOPE.avi" />
                            <p>

                                Users are also able to filter the feeds by heart Rate, Critical Status, and Triage Tag. To access this simply tap the title of the app to display the option

                            </p>
                        </div>

                        <div>
                            <h4> Patient Charts </h4>
                            <img src={im9} style={{height: 'auto',width: '20%'}} alt="NOPE.avi" />
                            <p>
                                Patient Charts are time stamped to show keep the nurses informed. You can edit these charts aswell with any useful information regarding the patient.
                            </p>
                        </div>
                        <div>

                            <h4> Create account</h4>
                            <img src={im7} style={{height: 'auto',width: '20%'}} alt="NOPE.avi" />
                            <p>
                                Make sure to follow the guidlines on this page as this will be your account for the remainder of use.
                            </p>

                        </div>
                        <div>

                            <h4> Data Feed </h4>
                            <img src={im4} style={{height: 'auto',width: '20%'}} alt="NOPE.avi" />
                            <p>

                                Data feed is an overall simplistic feed of all patients. This can be used to showcase the patients the doctor currently has with no frills. The values will change on their own and throw alerts when necessary.

                            </p>

                        </div>

                        <div>

                            <h4> Adding a Patient</h4>
                            <img src={im3} style={{height: 'auto',width: '20%'}} alt="NOPE.avi" />
                                <p>
                                    when a patient is first added to the hosptial they have to be added to the database. This page displays what is needed, Simply type in the patients information in to the specifed fields, choose the triage tag, which department they will belong and a currently available nurse.
                                </p>

                        </div>


                        <div>

                            <h4> Selecting a Patient</h4>
                            <img src={im2} style={{height: 'auto',width: '20%'}} alt="NOPE.avi" />
                            <p> Tap on The patients card will Show you the medical history of said patient. This will be updated in real time and shows the patients current vitals as well as any standing orders previous doctors may have given. In this screen you will also see any charts for the patients and the currently assigned nurse. If you are a Doctor you will be able to generate a standing order aswell on this screen.</p>
                            <h4>When A Patient enters Critical</h4>
                            <p> When this happen an alert will be thrown displaying that the patient has entered critical levels. This will be thrown to the currently assigned doctors, nurses and the main office. This alert will be displayed for any reason such as a drop in body temperature or vice-versa for example.</p>


                        </div>

                        <div>
                            <h4> Chat Function</h4>
                            <img src={im8} style={{height: 'auto',width: '20%'}} alt="NOPE.avi" />
                            <p> At the bottom right hand corner of all screens you will see a chat button.
                                This will open your departments chat to inform others of a current patient
                                status or any other work related topics.</p>

                        </div>




                    </Cell>
                </Grid>
            </div>
            )


    }
}

export default Support;
