import React from 'react'
import { NavLink } from 'react-router-dom'
import {connect} from 'react-redux'
import {signOut} from "../../store/actions/authActions";

const SignedInLinks = (props) => {

  return (
    <div>
      <ul className="right">
        <li><NavLink to='/create'>Add New Patient</NavLink></li>
          <li><a onClick={props.signOut}> Log Out</a></li>
        {/*<li><NavLink to='/' className="btn btn-floating red lighten-1">AM</NavLink></li>*/}
      </ul>
    </div>
  )
}

const mapDispatchToProps = (dipatch) => {
    return{
        signOut: () => dipatch(signOut())
    }
}

export default connect(null,mapDispatchToProps)(SignedInLinks)
