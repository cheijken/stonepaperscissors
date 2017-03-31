import React from 'react'
import { createStore } from 'redux'
import { Provider } from 'react-redux'
import { render } from 'react-dom'
import CounterReducer from './Counter/counter-reducer'
import CounterContainer from './Counter/counter-container'

const store = createStore(CounterReducer)

render(
  <Provider store={store}>
    <CounterContainer />
  </Provider>, 
  document.getElementById('main-app')
)
