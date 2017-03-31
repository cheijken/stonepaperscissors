import React, { PropTypes } from 'react'
import { connect } from 'react-redux'
import DuckImage from '../assets/Duck.jpg'
import './HomeView.scss'
import { startNewGame } from './HomeViewAction'




const HomeView = (props) => (
  <div>
    <h4>New GAME</h4>
    <button className="btn btn-default" onClick={props.startNewGame}>Start new game</button>
  </div>
)

HomeView.propTypes = {
  startNewGame: PropTypes.func.isRequired
}

const mapDispatchToProps = (dispatch) => ({
  startNewGame : () => { dispatch(startNewGame()) }
})

const mapStateToProps = (state) => ({
  //pingReply : state.pingReply
})

const HomeViewContainer = connect(mapStateToProps, mapDispatchToProps)(HomeView)

export default HomeViewContainer
