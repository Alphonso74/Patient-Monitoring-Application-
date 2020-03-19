import React, { Component } from 'react';
import { Grid, Cell, List, ListItem, ListItemContent } from 'react-mdl';
 import pictureAlphonso from '/Users/alphonsomckenzie/Documents/GitHub/Patient-Monitoring-Application-/website/src/images/picture.jpg'


class Alphonso extends Component {
    render() {
        return(
            <div className="contact-body">
                <Grid className="contact-grid">
                    <Cell col={6}>
                        <h2>Alphonso Mckenzie</h2>
                        <img
                            src={pictureAlphonso}
                        //src={ '/Users/alphonsomckenzie/Documents/GitHub/Patient-Monitoring-Application-/website/src/images/picture.jpg' }
                            alt="avatar"
                            style={{height: '250px'}}
                        />
                        <p style={{ width: '75%', margin: 'auto', paddingTop: '1em'}}>I am currently employed at Penn State Harrisburg as I work with the ITS Service Desk we have on campus. I have been employed since May of 2018 and will be until I graduate in the spring of 2020. </p>

                    </Cell>
                    <Cell col={6}>
                        <h2>Contact Me</h2>
                        <hr/>

                        <div className="contact-list">
                            <List>
                                <ListItem>
                                    <ListItemContent style={{fontSize: '30px', fontFamily: 'Anton'}}>
                                        <i className="fa fa-phone-square" aria-hidden="true"/>
                                        (301) 741-8086
                                    </ListItemContent>
                                </ListItem>

                                <ListItem>
                                    <ListItemContent style={{fontSize: '16px', fontFamily: 'Anton'}}>
                                        <i className="fa fa-linkedin" aria-hidden="true"/>
                                        https://www.linkedin.com/in/alphonso-mckenzie-a32440189/
                                    </ListItemContent>
                                </ListItem>

                                <ListItem>
                                    <ListItemContent style={{fontSize: '20px', fontFamily: 'Anton'}}>
                                        <i className="fa fa-envelope" aria-hidden="true"/>
                                        alphonso6809@gmail.com
                                    </ListItemContent>
                                </ListItem>

                                <ListItem>
                                    <ListItemContent style={{fontSize: '20px', fontFamily: 'Anton'}}>
                                        <i className="fa fa-github" aria-hidden="true"/>
                                        https://github.com/Alphonso74
                                    </ListItemContent>
                                </ListItem>


                            </List>
                        </div>
                    </Cell>
                </Grid>
            </div>
        )
    }
}

export default Alphonso;
