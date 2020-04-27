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


                <Link to="/datasimulator">Try Me</Link>
                <Link to="/Support">Support</Link>
                <Link to="/Documentation">Documentation</Link>
                <Link to="/aboutus">About Us</Link>




            </Navigation>
        </Header>
        <Drawer title={<Link style={{textDecoration: 'none', color: 'black'}} to="/">Home</Link>}>
            <Navigation>
                <Link to="/datasimulator">Try Me</Link>
                <Link to="/Support">Support</Link>
                <Link to="/Documentation">Documentation</Link>
                <Link to="/aboutus">About Us</Link>


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
