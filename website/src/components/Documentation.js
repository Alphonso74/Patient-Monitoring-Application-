import React, { Component } from 'react';
// import { Grid, Cell, List, ListItem, ListItemContent } from 'react-mdl';
import { Grid, Cell } from 'react-mdl';
import Gnatt from 'C:/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/Basic Gantt Chart(2).pdf';
import Schedule from 'C:/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/Detailed Schedule- 488.pdf';
import Model from 'C:/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/Domain Model, Use Cases, Requirements.pdf';
import GUI from 'C:/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/GUI Prototype-4.pdf';
import Proposal from 'C:/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/JavFirst - Patient Monitoring System Proposal .pdf';
import Robust from 'C:/Users/Logan.000/WebstormProjects/Patient-Monitor/website/src/images/Robustness Diagram, Sequence Diagram, Static Class Diagram, Technical Architecure.pdf';

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
                        <div>
                            <nav>
                                <button onClick={this.goToPrevPage}>Prev</button>
                                <button onClick={this.goToNextPage}>Next</button>
                            </nav>

                            <div style={{ width: 600 }}>
                                <Document
                                    file={Proposal}
                                    onLoadSuccess={this.onDocumentLoadSuccess}
                                >
                                    <Page pageNumber={pageNumber} width={600} />
                                </Document>
                            </div>

                            <p>
                                Page {pageNumber} of {numPages}
                            </p>
                        </div>
                        <div>
                            <nav>
                                <button onClick={this.goToPrevPage}>Prev</button>
                                <button onClick={this.goToNextPage}>Next</button>
                            </nav>

                            <div style={{ width: 600 }}>
                                <Document
                                    file={Schedule}
                                    onLoadSuccess={this.onDocumentLoadSuccess}
                                >
                                    <Page pageNumber={pageNumber} width={600} />
                                </Document>
                            </div>

                            <p>
                                Page {pageNumber} of {numPages}
                            </p>
                        </div>
                        <div>
                            <nav>
                                <button onClick={this.goToPrevPage}>Prev</button>
                                <button onClick={this.goToNextPage}>Next</button>
                            </nav>

                            <div style={{ width: 600 }}>
                                <Document
                                    file={Gnatt}
                                    onLoadSuccess={this.onDocumentLoadSuccess}
                                >
                                    <Page pageNumber={pageNumber} width={600} />
                                </Document>
                            </div>

                            <p>
                                Page {pageNumber} of {numPages}
                            </p>
                        </div>

                        <div>
                            <nav>
                                <button onClick={this.goToPrevPage}>Prev</button>
                                <button onClick={this.goToNextPage}>Next</button>
                            </nav>

                            <div style={{ width: 600 }}>
                                <Document
                                    file={Model}
                                    onLoadSuccess={this.onDocumentLoadSuccess}
                                >
                                    <Page pageNumber={pageNumber} width={600} />
                                </Document>
                            </div>

                            <p>
                                Page {pageNumber} of {numPages}
                            </p>
                        </div>
                        <div>
                            <nav>
                                <button onClick={this.goToPrevPage}>Prev</button>
                                <button onClick={this.goToNextPage}>Next</button>
                            </nav>

                            <div style={{ width: 600 }}>
                                <Document
                                    file={Robust}
                                    onLoadSuccess={this.onDocumentLoadSuccess}
                                >
                                    <Page pageNumber={pageNumber} width={600} />
                                </Document>
                            </div>

                            <p>
                                Page {pageNumber} of {numPages}
                            </p>
                        </div>
                        <div>
                            <nav>
                                <button onClick={this.goToPrevPage}>Prev</button>
                                <button onClick={this.goToNextPage}>Next</button>
                            </nav>

                            <div style={{ width: 600 }}>
                                <Document
                                    file={GUI}
                                    onLoadSuccess={this.onDocumentLoadSuccess}
                                >
                                    <Page pageNumber={pageNumber} width={600} />
                                </Document>
                            </div>

                            <p>
                                Page {pageNumber} of {numPages}
                            </p>
                        </div>


                    </Cell>
                </Grid>
            </div>


        )
    }
}

export default Documentation;
