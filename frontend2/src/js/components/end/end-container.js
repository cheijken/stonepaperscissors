import React from 'react'
import { connect } from 'react-redux'
import EndComponent from './end-component'
import {restart, newGame} from './../../Game/game-action'

const mapStateToProps = (state) => {
    return {
        currentCount: state.currentCount
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        onRestart: () => {
            dispatch(startGame())
        },
        onNew: () => {
            dispatch(newGame())
        }
    }
}

const EndContainer = connect(mapStateToProps, mapDispatchToProps)(EndComponent)

export default EndContainer
