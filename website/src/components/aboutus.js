import React, { Component } from 'react';
// import { Grid, Cell, List, ListItem, ListItemContent } from 'react-mdl';
import { Grid, Cell } from 'react-mdl';
import patientP from '/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/patientplusimage.png';
import { Link } from 'react-router-dom';





//import picture from "patientPlusApp.png"
// var imageName = require('./images/patientPlusApp.png')
class About extends Component {


  render() {

    return(
        <div style={{paddingTop: '2em'}}>
          <Grid>
            <Cell col={4} style={{paddingLeft: '5em'}}>
              <div style={{paddingLeft: '5em'}}>
                <img
                    src={patientP}
                    alt="avatar"
                    style={{height: '200px', borderRadius: 400/2}}
                />
              </div>

              <h2 style={{paddingTop: '2em'}}>Contact Information</h2>

              <hr style={{borderTop: '3px solid red', width: '75%'}}/>
              <h5>School Address</h5>
              <p>777 W Harrisburg Pike, Middletown, PA 17057</p>
              <h5>Phone</h5>
              <p>(301) 741-8086</p>
              <h5>Emails</h5>
              <p>ajm6684@psu.edu</p>
              <h5>Github</h5>
              <p>https://github.com/Alphonso74/Patient-Monitoring-Application-</p>
              <hr style={{borderTop: '3px solid red', width: '75%'}}/>
            </Cell>

            <Cell className="right-col" col={8} style = {{paddingRight: "5em"}}>

              <h3>Patient Monitroing System</h3>
              <hr style={{borderTop: '3px solid #e22947'}} />
              <h4> Our team </h4>
                <h6> <Link style={{textDecoration: 'underline black', color: 'black'}} to="/alphonso">Alphonso Mckenzie</Link> ·
                <Link style={{textDecoration: 'underline black', color: 'black'}} to="/sean">Sean Todd</Link> ·
                <Link style={{textDecoration: 'underline black', color: 'black'}} to="/charles">Charles Todd</Link> ·
                  <Link style={{textDecoration: 'underline black', color: 'black'}} to="/logan">Logan Kollar</Link> ·
                <Link style={{textDecoration: 'underline black', color: 'black'}} to="/dhruvilkumar">Dhruvilkumar Joshi</Link> </h6>


              <h4>Our Idea</h4>
                <p>
                      We wanted to create an application that doctors, nurses, and hospital receptionists would be able to use in order to monitor patient medical conditions and
                  statuses live all at the same time on one feed. This would in turn help in the effectiveness of the hospital. In the main feed display, doctors and nurses
                  will be able to visibly see medical data for each patient as well as live monitored including the patient’s heartbeat rate, blood pressure, and body temperature.
                  If a doctor or nurse clicks directly on a patient icon, they are able to see a full digest of the patients’ information such as the diagnosis, sleep patterns, diet,
                  pain level, amounts of fatigue, and amount of nausea, etc.
                </p>
              <p>
                    The Patient monitoring System does just that, users will be able to look at a feed and scroll through a list of patients alongside their health data. This applications
                  allows users to create and account and connect to their fellow coworkers all in the efforts of preserving lives by allowing doctors to be on the same page, with fast
                  and efficient response times.
              </p>
              <p>

                    The idea came from watching as doctors had to scramble to find out what was wrong with a patient and had to find a nurse with the given information of the problem.
                  This allows for too many errors and increases the amount of time before the patient receives the care they need.
                </p>

              <h4>Ideas For the Future</h4>

                <p>We want to be able to eliminate chance for error going forward. We hope this can be achieved by being able to sync directly with the patient upon entry eliminating the need for data to be manually entered. Along with being able to sync directly to the monitors in the room more efficiently.  We want to be able to provide this App to hospitals in the region.</p>

            </Cell>
          </Grid>
        </div>


    )
  }
}

export default About;
