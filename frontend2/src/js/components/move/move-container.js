import React from 'react'
import { connect } from 'react-redux'
import MoveComponent from './move-component'
import {makeMove} from './../../Game/game-action'

const mapStateToProps = (state) => {
    return {
        currentCount: state.currentCount
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        onRock: () => {
            dispatch(makeMove("ROCK"))
        },
        onPaper: () => {
            dispatch(makeMove("PAPER"))
        },
        onScissors: () => {
            dispatch(makeMove("SCISSORS"))
        }
    }
}

const MoveContainer = connect(mapStateToProps, mapDispatchToProps)(MoveComponent)

export default MoveContainer
