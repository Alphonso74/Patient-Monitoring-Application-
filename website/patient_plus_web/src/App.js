import React from 'react';
import { BrowserRouter, Switch, Route, Link } from 'react-router-dom';
import './App.css';
import Simulator from './Simulator';
//import routes from './Routes';

class App extends React.Component {
    render() {
        return (
            <div className="App">

                <ul className="sidenav" id="mobile-id">
                    <li>
                        <div className="user-view">
                            <div className="background">
                                <img src="%PUBLIC_URL%/images/bg1.jpg"  alt={'picture'}/>
                            </div>
                            <a href="#user"><img className="circle" href="%PUBLIC_URL%/images/"  alt={''}/></a>
                            <a href="#name"><span className="white-text name">Alphonso Mackenzie</span></a>
                            <a href="#email"><span className="white-text email">@psu.edu</span></a>
                            <a href="#name"><span className="white-text name">Sean Todd</span></a>
                            <a href="#email"><span className="white-text email">spt5295@psu.edu</span></a>
                            <a href="#name"><span className="white-text name">Charles Todd</span></a>
                            <a href="#email"><span className="white-text email">@psu.edu</span></a>
                            <a href="#name"><span className="white-text name">Dhruvilkumar Joshi</span></a>
                            <a href="#email"><span className="white-text email">@psu.edu</span></a>
                            <a href="#name"><span className="white-text name">Logan Kollar</span></a>
                            <a href="#email"><span className="white-text email">lrk5177@psu.edu</span></a>
                        </div>
                    </li>
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Download</a></li>
                    <li><a href="#">Support</a></li>
                    <li><a href="#">Simulation</a></li>
                    <li><a href="#">About</a></li>
                </ul>


                <section>
                    <div className="container">
                        <h3 className="center-align">The Application</h3>

                        <div className="carousel">
                            <a className="carousel-item" href="#one!"><img
                                src="https://lorempixel.com/250/250/nature/2"  alt={''}/></a>
                            <a className="carousel-item" href="#two!"><img
                                src="https://lorempixel.com/250/250/nature/2"  alt={''}/></a>
                            <a className="carousel-item" href="#three!"><img
                                src="https://lorempixel.com/250/250/nature/2"  alt={''}/></a>
                            <a className="carousel-item" href="#four!"><img
                                src="https://lorempixel.com/250/250/nature/2"  alt={''}/></a>
                            <a className="carousel-item" href="#five!"><img
                                src="https://lorempixel.com/250/250/nature/2"  alt={''}/></a>
                        </div>


                        <div className="row mt-5">
                            <div className="col s12 m4">
                                <div className="services-container center-align">
                                    <i className="material-icons my-icon">airplay</i>
                                    <h3>Why this topic?</h3>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                                        sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                                        Facilisis gravida neque convallis a cras. Ultricies mi quis hendrerit dolor
                                        magna eget.
                                        Morbi non arcu risus quis varius. Nullam ac tortor vitae purus faucibus.
                                        Elementum facilisis
                                        leo vel fringilla est. Varius morbi enim nunc faucibus a pellentesque sit amet
                                        porttitor.
                                        Pretium aenean pharetra magna ac placerat vestibulum lectus mauris ultrices.
                                        Dolor purus non
                                        enim praesent elementum. Eleifend mi in nulla posuere sollicitudin aliquam.
                                        Scelerisque varius
                                        morbi enim nunc. Leo duis ut diam quam. Ipsum a arcu cursus vitae congue mauris
                                        rhoncus aenean vel.
                                        Convallis convallis tellus id interdum velit. Volutpat blandit aliquam etiam
                                        erat velit scelerisque
                                        in dictum.
                                    </p>
                                    <button className="btn bg-primary">Some button</button>
                                </div>

                            </div>

                            <div className="col s12 m4">
                                <div className="services-container center-align">
                                    <i className="material-icons my-icon">blur_circular</i>
                                    <h3>Effectiveness</h3>
                                    <p>Vivamus at augue eget arcu dictum varius duis at. Lectus magna fringilla urna
                                        porttitor.
                                        Mauris sit amet massa vitae tortor. Aliquam eleifend mi in nulla posuere
                                        sollicitudin aliquam.
                                        Pretium nibh ipsum consequat nisl vel. Pellentesque nec nam aliquam sem. Vitae
                                        tempus quam pellentesque
                                        nec nam aliquam sem et. Ridiculus mus mauris vitae ultricies leo integer
                                        malesuada. Ultricies integer quis
                                        auctor elit sed vulputate mi. Ullamcorper dignissim cras tincidunt lobortis
                                        feugiat vivamus at augue eget.
                                        Tortor posuere ac ut consequat semper.
                                    </p>
                                    <button className="btn bg-primary">Some Button</button>
                                </div>

                            </div>

                            <div className="col s12 m4">
                                <div className="services-container center-align">
                                    <i className="material-icons my-icon">build</i>
                                    <h3>Some of Third Main Point</h3>
                                    <p>Tempus quam pellentesque nec nam aliquam sem et. Nulla aliquet porttitor
                                        lacus luctus accumsan tortor posuere ac. Morbi leo urna molestie at elementum.
                                        Tortor dignissim convallis aenean et tortor at risus viverra. Maecenas
                                        volutpat blandit aliquam etiam erat velit scelerisque. Nunc congue nisi vitae
                                        suscipit tellus mauris. Nec feugiat in fermentum posuere. Nullam vehicula ipsum
                                        a
                                        arcu cursus vitae congue mauris rhoncus. Interdum velit euismod in pellentesque
                                        massa
                                        placerat duis ultricies. Sed vulputate mi sit amet. Dolor purus non enim
                                        praesent. Iaculis
                                        at erat pellentesque adipiscing commodo elit at. Porta lorem mollis aliquam ut.
                                    </p>
                                    <button className="btn bg-primary">Some Button</button>
                                </div>

                            </div>

                        </div>
                    </div>

                </section>

                <main>
                    {this.props.children}
                </main>


                <footer className="page-footer">
                    <div className="container">
                        <div className="row">
                            <div className="col l6 s12">
                                <h5 className="white-text">Footer Content</h5>
                                <p className="grey-text text-lighten-4">We will put links or our github information</p>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        );
    }
}

export default App;
