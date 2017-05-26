import React from 'react'
import { connect } from 'react-redux'
import WellcomeComponent from './wellcome-component'
import {startGame} from './../../Game/game-action'

const mapStateToProps = (state) => {
    return {
        currentCount: state.currentCount
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        onStartGame: () => {
            dispatch(startGame("myname"))
        }
    }
}

const WellcomeContainer = connect(mapStateToProps, mapDispatchToProps)(WellcomeComponent)

export default WellcomeContainer
