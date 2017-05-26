import React from 'react'
import { createStore } from 'redux'
import { Provider } from 'react-redux'
import { render } from 'react-dom'

import Root from './components/Root'

import GameReducer from './Game/game-reducer'

const gameStore = createStore(GameReducer)

render(
    <Root store={gameStore} />,
    document.getElementById('root')
)

