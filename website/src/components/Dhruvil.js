import React, { Component } from 'react';
import { Grid, Cell, List, ListItem, ListItemContent } from 'react-mdl';


class Dhruvil extends Component {
    render() {
        return(
            <div className="contact-body">
                <Grid className="contact-grid">
                    <Cell col={6}>
                        <h2>Dhruvilkumar V Joshi</h2>
                        <img
                      //  src=
                           alt="avatar"
                        {    style={{height: '250px'}}*/}

                        <p style={{ width: '75%', margin: 'auto', paddingTop: '1em'}}> My name is Dhruvilkumar Joshi also known as Dhruvil. Currently a Senior at Pennsylvania State University majoring in Computer Science with a minor in Business and Security and Risk Analysis</p>

                    </Cell>
                    <Cell col={6}>
                        <h2>Contact Me</h2>
                        <hr/>

                        <div className="contact-list">
                            <List>
                                <ListItem>
                                    <ListItemContent style={{fontSize: '30px', fontFamily: 'Anton'}}>
                                        <i className="fa fa-phone-square" aria-hidden="true"/>
                                        (267) 210-9651
                                    </ListItemContent>
                                </ListItem>

                               <ListItem>
                                    <ListItemContent style={{fontSize: '16px', fontFamily: 'Anton'}}>
                                     <i className="fa fa-linkedin" aria-hidden="true"/>
                                  https://www.linkedin.com/in/dhruvilkumarjoshi/
                                </ListItemContent>
                               </ListItem>

                                <ListItem>
                                    <ListItemContent style={{fontSize: '30px', fontFamily: 'Anton'}}>
                                        <i className="fa fa-envelope" aria-hidden="true"/>
                                        someone@example.com
                                    </ListItemContent>
                                </ListItem>

                                <ListItem>
                                    <ListItemContent style={{fontSize: '30px', fontFamily: 'Anton'}}>
                                        <i className="fa fa-skype" aria-hidden="true"/>
                                        MySkypeID
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

export default Dhruvil;
