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
                                        <a href={ 'https://patient-monitoring-syste-39706.web.app/' }>Download</a>
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