import React, { Component } from 'react';
// import { Grid, Cell, List, ListItem, ListItemContent } from 'react-mdl';
import { Grid, Cell } from 'react-mdl';
import Gnatt from '../images/Basic Gantt Chart(2).pdf';
import Schedule from '../images/Detailed Schedule- 488.pdf';
import Model from '../images/Domain Model, Use Cases, Requirements.pdf';
import GUI from '../images/GUI Prototype-4.pdf';
import Proposal from '../images/JavFirst - Patient Monitoring System Proposal .pdf';
import Robust from '../images/Robustness Diagram, Sequence Diagram, Static Class Diagram, Technical Architecure.pdf';

import { Link } from 'react-router-dom';
import { Document, Page, pdfjs } from 'react-pdf';
pdfjs.GlobalWorkerOptions.workerSrc = `//cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.js`;

//import picture from "patientPlusApp.png"
// var imageName = require('./images/patientPlusApp.png')
class Documentation extends Component {
    state = { numPages: null, pageNumber: 1};

    onDocumentLoadSuccess = ({ numPages }) => {
        this.setState({ numPages });
    };

    goToPrevPage = (e) =>{
        e.preventDefault();
        this.setState(state => ({
            pageNumber: state.pageNumber - 1
        }))
    };

    goToNextPage = (e) =>{
        e.preventDefault();
        this.setState(state => ({
            pageNumber: state.pageNumber + 1
        }))
    };

    render() {
        const { pageNumber, numPages } = this.state;

        return(
            <div style={{paddingTop: '2em'}}>
                <Grid>

                    <Cell col={4} style={{paddingLeft: '5em'}}>

                </Cell>

                    <Cell className="right-col" col={8} style = {{paddingRight: "5em"}}>

                        <h4>Our Documentation</h4>

                        <h5> Proposal </h5>

                        <div >
                            <div >
                                <Document
                                          file={Proposal}
                                    onLoadSuccess={this.onDocumentLoadSuccess}
                                >
                                    {[1,2,3].map(page => (
                                        <Page pageNumber={page} />
                                    ))}
                                </Document>
                            </div>
                        </div>

                        <h5> Schedule </h5>

                        <div>
                            <div >
                                <Document
                                    file={Schedule}
                                    onLoadSuccess={this.onDocumentLoadSuccess}
                                >
                                    {[1,2].map(page => (
                                        <Page pageNumber={page} />
                                    ))}
                                </Document>
                            </div>
                        </div>

                        <h5> Gnatt </h5>

                        <div>
                            <div>
                                <Document
                                    file={Gnatt}
                                    onLoadSuccess={this.onDocumentLoadSuccess}
                                >
                                    <Page pageNumber={pageNumber} width={600} />
                                </Document>
                            </div>
                        </div>

                        <h5> Domain Model, Use Cases, Requriements </h5>

                        <div>

                            <div>
                                <Document
                                    file={Model}
                                    onLoadSuccess={this.onDocumentLoadSuccess}
                                >
                                    {[1,2,3,4,5,6,7].map(page => (
                                        <Page pageNumber={page} />
                                    ))}
                                </Document>
                            </div>
                        </div>

                        <h5> Robust Diagrams </h5>

                        <div>
                            <div style={{ width: 600 }}>
                                <Document
                                    file={Robust}
                                    onLoadSuccess={this.onDocumentLoadSuccess}
                                >
                                    {[1,2,3,4,5,6,7,8,9,10,11].map(page => (
                                        <Page pageNumber={page} />
                                    ))}
                                </Document>
                            </div>
                        </div>

                        <h5> GUI </h5>

                        <div>
                            <div>
                                <Document
                                    file={GUI}
                                    onLoadSuccess={this.onDocumentLoadSuccess}
                                >
                                    {[1].map(page => (
                                        <Page pageNumber={page} />
                                    ))}
                                </Document>
                            </div>

                        </div>


                    </Cell>
                </Grid>
            </div>


        )
    }
}

export default Documentation;
