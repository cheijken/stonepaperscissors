import {browserHistory} from 'react-router';
// ------------------------------------
// Constants
// ------------------------------------
export const NEW_GAME = 'NEW_GAME'

// ------------------------------------
// Actions
// ------------------------------------
export function newGame (value = 1) {
  console.log('newGame!');
  browserHistory.push('/');
  return {
    type    : NEW_GAME,
    payload : value
  }
}

/*  This is a thunk, meaning it is a function that immediately
    returns a function for lazy evaluation. It is incredibly useful for
    creating async actions, especially when combined with redux-thunk! */

export const actions = {
  newGame
}

// ------------------------------------
// Action Handlers
// ------------------------------------
const ACTION_HANDLERS = {
  [NEW_GAME]    : (state, action) => state + action.payload,
}

// ------------------------------------
// Reducer
// ------------------------------------
const initialState = 0
export default function gameendReducer (state = initialState, action) {
  const handler = ACTION_HANDLERS[action.type]

  return handler ? handler(state, action) : state
}
