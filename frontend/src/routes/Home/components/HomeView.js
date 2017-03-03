import React, { PropTypes } from 'react'
import { connect } from 'react-redux'
import DuckImage from '../assets/Duck.jpg'
import './HomeView.scss'
import { ping } from './HomeViewAction'

const HomeView = (props) => (
  <div>
    <h4>Welcome!</h4>
    <button onClick={props.pingBackend}>Ping!</button>
    <p>Reply: {props.pingReply}</p>
    <img
      alt='This is a duck, because Redux!'
      className='duck'
      src={DuckImage} />
  </div>
)

HomeView.propTypes = {
  pingReply: PropTypes.string,
  pingBackend: PropTypes.func.isRequired
}

const mapDispatchToProps = (dispatch) => ({
    pingBackend : () => { dispatch(ping()) }
})

const mapStateToProps = (state) => ({
  pingReply : state.pingReply
})

const HomeViewContainer = connect(mapStateToProps, mapDispatchToProps)(HomeView)

export default HomeViewContainer
