/** Counter container - links the presentational react component with redux  **/

import React from 'react'
import { connect } from 'react-redux'
import CounterComponent from './counter-component'
import {countDown, countUp} from './counter-action'

const mapStateToProps = (state) => {
  return {
    currentCount: state.currentCount
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onCountDown: () => {
      dispatch(countDown())
    },
    onCountUp: () => {
      dispatch(countUp())
    },
  }
}

const CounterContainer = connect(mapStateToProps, mapDispatchToProps)(CounterComponent)

export default CounterContainer
