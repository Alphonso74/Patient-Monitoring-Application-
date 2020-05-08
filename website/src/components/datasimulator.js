import React, { Component } from 'react';
import { Header } from 'react-mdl';
import { connect } from 'react-redux';
import { firestoreConnect} from "react-redux-firebase";
import { compose } from "redux";
import { Link } from 'react-router-dom'
import { Grid, Cell } from 'react-mdl';
import 'materialize-css';
import { Button, Card, Row, Col, CardMedia, CardText, CardActions,FlatButton,CardHeader } from 'react-materialize';
import APK from '../images/APK.png';
import WEB from '../images/Azure-Web-App.jpg';
import GIT from '../images/github-mark.png';

class datasimulator extends Component {
    render(){
        return(
            <div>

                <Grid>
                    <Cell col={4} >
                        <div className="col s12 m7" style = {{display: "flex",
                        justifyContent: "center",
                        alignItems: "center"}}>
                            <div className="card horizontal card-small">
                                <div className="card-image">
                                    <img src={APK}/>
                                </div>
                                <div className="card-stacked">
                                    <div className="card-content">
                                        <p>Click on the link below to download the apk. This is currently only compatible with android devices </p>
                                    </div>
                                    <div className="card-action">
                                        <a href={ 'https://www.mediafire.com/file/l81f0dh3g4exo4o/app-debug.apk/file' }>Download</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </Cell>

                    <Cell className="right-col" col={4} style = {{paddingRight: "8m"}}>

                        <div className="col s12 m7">
                            <div className="card horizontal card-small">
                                <div className="card-image">
                                    <img src={WEB}/>
                                </div>
                                <div className="card-stacked">
                                    <div className="card-content">
                                        <p>This is the Web-App Component, Simply click on the link below to head to the web-app.</p>
                                    </div>
                                    <div className="card-action">
                                        <a href={ 'https://patient-monitoring-syste-39706.firebaseapp.com/' }>Download</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </Cell>

                    <Cell className="right-col" col={4} style = {{paddingRight: "8m"}}>

                        <div className="col s12 m7">
                            <div className="card horizontal card-small">
                                <div className="card-image">
                                    <img src={GIT}/>
                                </div>
                                <div className="card-stacked">
                                    <div className="card-content">
                                        <p>Another option for testing the Android application is to pull the current project from our GitHub repository, and running the application within Android Studio.
                                            This can be done by, <br />
                                            1.	 Going to https://github.com/Alphonso74/Patient-Monitoring-Application-<br />
                                            2.	Clicking the green button labeled “Clone or Download”<br />
                                            3.	Once the installation is complete, opening the project folder directly into Android studio should run it.<br />
                                            4.	If the current APK file is throwing an error, simply go to File -> Invalidate Caches/Restart -> Invalidate and Restart<br />
                                            a.	This will clear the current caches within the project and update the project with an unique APK file for the device your running the application on.</p>
                                    </div>
                                    <div className="card-action">
                                        <a href={ 'https://github.com/Alphonso74/Patient-Monitoring-Application' }>Link</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </Cell>






                </Grid>

            </div>

        );
    }
}

export default datasimulator;