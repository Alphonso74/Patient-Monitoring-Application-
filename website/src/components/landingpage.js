import React, { Component } from 'react';
import { Grid, Cell } from 'react-mdl';
import logo from "../images/patientplusimage.png"


class Landing extends Component {
  render() {
    return(
      <div className="Home">

      <div style={{width: '100%', margin: 'auto'}}>
        <Grid className="landing-grid">
          <Cell col={12}>
            <img
              src={logo}
              alt="Patient Plus"
              className="avatar-img"
              />

            <div className="banner-text">
              <h1>Patient Monitoring System</h1>

            <hr/>

          <p>React | Java | JavaScript |  | Node | Firebase | Bootstrap | HTML/CSS</p>

        <div className="social-links">

          {/*/!* LinkedIn *!/*/}
          {/*<a href="http://google.com" rel="noopener noreferrer" target="_blank">*/}
          {/*  <i className="fa fa-linkedin-square" aria-hidden="true" />*/}
          {/*</a>*/}

          {/* Github */}
          <a href="https://github.com/Alphonso74/Patient-Monitoring-Application-" rel="noopener noreferrer" target="_blank">
            <i className="fa fa-github-square" aria-hidden="true" />
          </a>
          <p>The Patient Monitoring System is a program to help hospital personnel manage their patients in an effective manner, while putting the convenience of patient information in arms reach at all point in time. </p>


          {/* Youtube */}
          <a href="https://www.youtube.com/" rel="noopener noreferrer" target="_blank">
            <i className="fa fa-youtube-square" aria-hidden="true" />
          </a>


        </div>



            </div>



          </Cell>



        </Grid>




      </div>




      </div>
    )
  }
}

export default Landing;
