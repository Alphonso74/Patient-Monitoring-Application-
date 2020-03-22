import React, { Component } from 'react';
import { Grid, Cell, List, ListItem, ListItemContent } from 'react-mdl';


class Sean extends Component {
    render() {
        return(
            <div className="contact-body">
                <Grid className="contact-grid">
                    <Cell col={6}>
                        <h2>Sean Todd</h2>
                        <img
                            src="https://cdn2.iconfinder.com/data/icons/avatar-2/512/Fred_man-512.png"
                            alt="avatar"
                            style={{height: '250px'}}
                        />
                        <p style={{ width: '75%', margin: 'auto', paddingTop: '1em'}}>Computer Science major at Penn State Harrisburg, expected to graduate Fall 2020.</p>

                    </Cell>
                    <Cell col={6}>
                        <h2>Contact Me</h2>
                        <hr/>

                        <div className="contact-list">
                            <List>
                                <ListItem>
                                    <ListItemContent style={{fontSize: '30px', fontFamily: 'Anton'}}>
                                        <i className="fa fa-phone-square" aria-hidden="true"/>
                                        (717) 713-0395
                                    </ListItemContent>
                                </ListItem>

                                <ListItem>
                                    <ListItemContent style={{fontSize: '30px', fontFamily: 'Anton'}}>
                                        <i className="fa fa-envelope" aria-hidden="true"/>
                                        seantodd114@gmail.com
                                    </ListItemContent>
                                </ListItem>

                                <ListItem>
                                    <ListItemContent style={{fontSize: '20px', fontFamily: 'Anton'}}>
                                        <i className="fa fa-github" aria-hidden="true"/>
                                        https://github.com/seantodd114
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

export default Sean;
