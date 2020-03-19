import React, { Component } from 'react';
// import { Grid, Cell, List, ListItem, ListItemContent } from 'react-mdl';
import { Grid, Cell } from 'react-mdl';


import patientP from '/Users/alphonsomckenzie/Documents/GitHub/Patient-Monitoring-Application-/website/src/images/patientPlusApp.png';

//import picture from "patientPlusApp.png"
// var imageName = require('./images/patientPlusApp.png')
class About extends Component {
  render() {
    return(
        <div>
          <Grid>
            <Cell col={4}>
              <div style={{textAlign: 'center'}}>
                <img

                    src={patientP}
                    alt="avatar"
                    style={{height: '200px'}}
                />
              </div>

              <h2 style={{paddingTop: '2em'}}>Patient Monitroing System</h2>
              {/*<h4 style={{color: 'grey'}}>Capstone Proposal</h4>*/}
              {/*<hr style={{borderTop: '3px solid #833fb2', width: '50%'}}/>*/}
              {/*<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.</p>*/}
              <hr style={{borderTop: '3px solid red', width: '50%'}}/>
              <h5>School Address</h5>
              <p>777 W Harrisburg Pike, Middletown, PA 17057</p>
              <h5>Phone</h5>
              <p>(301) 741-8086</p>
              <h5>Emails</h5>
              <p>ajm6684@psu.edu</p>
              <h5>Github</h5>
              <p>https://github.com/Alphonso74/Patient-Monitoring-Application-</p>
              <hr style={{borderTop: '3px solid red', width: '50%'}}/>
            </Cell>
            <Cell className="resume-right-col" col={8}>

              <h2>About the Group</h2>

                <h4>Alphonso McKenzie </h4>
                <h4> Sean Todd</h4>
                <h4>Charles Todd</h4>
                <h4>Logan Kollar</h4>
                <h4> Dhruvilkumar Joshi</h4>

              {/*<Education*/}
              {/*    startYear={2002}*/}
              {/*    endYear={2006}*/}
              {/*    schoolName="My University"*/}
              {/*    schoolDescription="Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"*/}
              {/*/>*/}

              {/*<Education*/}
              {/*    startYear={2007}*/}
              {/*    endYear={2009}*/}
              {/*    schoolName="My 2nd University"*/}
              {/*    schoolDescription="Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"*/}
              {/*/>*/}
              <hr style={{borderTop: '3px solid #e22947'}} />

              <h2>Proposal</h2>
                <h4>
                    The Patient Monitoring System is an interface dual web/android application that lets hospital doctors, nurses, and receptionists organize, manage, and monitor the data and wellness of patients that have been admitted into a hospital. Users will be able to look at a feed and scroll through a list of patients alongside their dynamic and static health data. The application allows users to create an account connected to their hospital and enter an interface with administered patients and other hospital doctors, nurses, and receptionists.
                </h4>

              {/*<Experience*/}
              {/*    startYear={2009}*/}
              {/*    endYear={2012}*/}
              {/*    jobName="First Job"*/}
              {/*    jobDescription="Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"*/}
              {/*/>*/}

              {/*<Experience*/}
              {/*    startYear={2012}*/}
              {/*    endYear={2016}*/}
              {/*    jobName="Second Job"*/}
              {/*    jobDescription="Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"*/}

              <hr style={{borderTop: '3px solid #e22947'}} />
              <h2>Ideas For the Future</h2>

                <h4>Soon Come....</h4>
              {/*<Skills*/}
              {/*    skill="javascript"*/}
              {/*    progress={100}*/}
              {/*/>*/}
              {/*<Skills*/}
              {/*    skill="HTML/CSS"*/}
              {/*    progress={80}*/}
              {/*/>*/}
              {/*<Skills*/}
              {/*    skill="NodeJS"*/}
              {/*    progress={50}*/}
              {/*/>*/}
              {/*<Skills*/}
              {/*    skill="React"*/}
              {/*    progress={25}*/}
              {/*/>*/}


            </Cell>
          </Grid>
        </div>


    )
  }
}

export default About;
