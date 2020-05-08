import React, { Component } from 'react';
// import { Grid, Cell, List, ListItem, ListItemContent } from 'react-mdl';
import { Grid, Cell } from 'react-mdl';
import patientP from '/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/patientplusimage.png';
import { Link } from 'react-router-dom';
import im1 from '/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/IMG_7901.JPG';
import im2 from '/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/IMG_7902.JPG';
import im3 from '/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/IMG_7903.JPG';
import im4 from '/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/IMG_7904.JPG';
import im5 from '/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/IMG_7905.JPG';
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

                        <img src={im1} style={{height: 'auto',width: '30%'}} alt="NOPE.avi" />
                        <br class='blank' />
                        <img src={im2} style={{height: 'auto',width: '20%'}} alt="NOPE.avi" />
                        <br className='blank'/>
                        <img src={im3} style={{height: 'auto',width: '20%'}} alt="NOPE.avi" />
                        <br className='blank'/>
                        <img src={im4} style={{height: 'auto',width: '20%'}} alt="NOPE.avi" />
                        <br className='blank'/>
                        <img src={im5} style={{height: 'auto',width: '20%'}} alt="NOPE.avi" />
                        <br className='blank'/>
                        <img src={im6} style={{height: 'auto',width: '20%'}} alt="NOPE.avi" />
                        <br className='blank'/>
                        <img src={im7} style={{height: 'auto',width: '20%'}} alt="NOPE.avi" />
                        <br className='blank'/>
                        <img src={im8} style={{height: 'auto',width: '20%'}} alt="NOPE.avi" />
                        <br className='blank'/>
                        <img src={im9} style={{height: 'auto',width: '20%'}} alt="NOPE.avi" />
                        <br className='blank'/>
                        <img src={im10} style={{height: 'auto',width: '20%'}} alt="NOPE.avi" />

                </Cell>

                    <Cell className="right-col" col={8} style = {{paddingRight: "3em"}}>

                        <h1> **** Will be adding Pictures of App **** </h1>

                        <h4>How To Use</h4>



                        <p>In this section we will give you a brief tutorial on how to use this product.</p>
                            <div>




                                <h4>Logging In</h4>
                                <p> You will be given a Username/password by you Hospital. You will be required to login upon first use. Once logged in you will be connected to your departments feed of patients.</p>


                            </div>
                            <h4>Department Feeds</h4>
                                <p> This displays all of the current patients inside your assigned department. If for any reason you need to check what patients are in a different work area, tap the department name. This will open a dialog box. Select more options and pick your desired feed. The Display will now show all patients belong to that selected department.</p>
                            <h4> Selecting a Patient</h4>
                                    <p> Tap on The patients card will Show you the medical history of said patient. This will be updated in real time and shows the patients current vitals as well as any standing orders previous doctors may have given. In this screen you will also see any charts for the patients and the currently assigned nurse. If you are a Doctor you will be able to generate a standing order aswell on this screen.</p>
                            <h4>When A Patient enters Critical</h4>
                                        <p> When this happen an alert will be thrown displaying that the patient has entered critical levels. This will be thrown to the currently assigned doctors, nurses and the main office. This alert will be displayed for any reason such as a drop in body temperature or vice-versa for example.</p>
                            <h4> Chat Function</h4>
                                            <p> At the bottom right hand corner of all screens you will see a chat button. This will open your departments chat to inform others of a current patient status or any other work related topics.</p>


                    </Cell>
                </Grid>
            </div>
            )


    }
}

export default Support;
