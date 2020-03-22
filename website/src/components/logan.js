import React, { Component } from 'react';
import { Grid, Cell, List, ListItem, ListItemContent } from 'react-mdl';


class Logan extends Component {
    render() {
        return(
            <div className="contact-body">
                <Grid className="contact-grid">
                    <Cell col={6}>
                        <h2>Logan Kollar</h2>
                        {/*<img*/}
                        {/*    src="https://cdn2.iconfinder.com/data/icons/avatar-2/512/Fred_man-512.png"*/}
                        {/*    alt="avatar"*/}
                        {/*    style={{height: '250px'}}*/}
                        {/*/>*/}
                        <p style={{ width: '75%', margin: 'auto', paddingTop: '1em'}}>I am currently employed in the hospitality industry, where i use my down time to work on side projects</p>

                    </Cell>
                    <Cell col={6}>
                        <h2>Contact Me</h2>
                        <hr/>

                        <div className="contact-list">
                            <List>
                                <ListItem>
                                    <ListItemContent style={{fontSize: '30px', fontFamily: 'Anton'}}>
                                        <i className="fa fa-phone-square" aria-hidden="true"/>
                                        (717) 283-9889
                                    </ListItemContent>
                                </ListItem>

                                <ListItem>
                                    <ListItemContent style={{fontSize: '30px', fontFamily: 'Anton'}}>
                                        <i className="fa fa-envelope" aria-hidden="true"/>
                                        ayleids@gmail.com
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

export default Logan;
