import React, { Component } from 'react';
import './App.css';
import { Layout, Header, Navigation, Drawer, Content } from 'react-mdl';
import Main from './components/main';
import { Link } from 'react-router-dom';

class App extends Component {
  render() {
    return (
      <div className="demo-big-content">
    <Layout>
        <Header className="header-color" title={<Link style={{textDecoration: 'none', color: 'white'}} to="/">Home</Link>} scroll>
            <Navigation>
                <Link to="/aboutus">About Us</Link>
                {/*<a href="patient-monitoring-syste-39706.firebaseapp.com" rel="noopener noreferrer" target="_blank">*/}
                {/*    <i className="fa fa-github-square" aria-hidden="true" />*/}
                {/*</a>*/}
                <Link to="/datasimulator">Data Simulator</Link>
                <Link to="/alphonso">Alphonso Mckenzie</Link>
                <Link to="/sean">Sean Todd</Link>
                <Link to="/charles">Charles Todd</Link>
                <Link to="/logan">Logan Kollar</Link>
                <Link to="/dhruvilkumar">Dhruvilkumar Joshi</Link>




            </Navigation>
        </Header>
        <Drawer title={<Link style={{textDecoration: 'none', color: 'black'}} to="/">Home</Link>}>
            <Navigation>
              <Link to="/aboutus">About Us</Link>
                <Link to="/datasimulator">Data Simulator</Link>
                <Link to="/alphonso">Alphonso Mckenzie</Link>
                <Link to="/sean">Sean Todd</Link>
                <Link to="/charles">Charles Todd</Link>
                <Link to="/logan">Logan Kollar</Link>
                <Link to="/dhruvilkumar">Dhruvilkumar Joshi</Link>

            </Navigation>
        </Drawer>
        <Content>
            <div className="page-content" />
            <Main/>
        </Content>
    </Layout>
</div>

    );
  }
}

export default App;
