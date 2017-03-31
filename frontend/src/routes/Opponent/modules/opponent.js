import {browserHistory} from 'react-router';
// ------------------------------------
// Constants
// ------------------------------------
export const SELECT_OPPONENT = 'SELECT_OPPONENT'
export const OPPONENT_DOUBLE_ASYNC = 'OPPONENT_DOUBLE_ASYNC'

// ------------------------------------
// Actions
// ------------------------------------
export function selectOpponent (value = 1) {
  console.log('selectOpponent!');
  browserHistory.push('/game');
  return {
    type    : SELECT_OPPONENT,
    payload : value
  }
}

/*  This is a thunk, meaning it is a function that immediately
    returns a function for lazy evaluation. It is incredibly useful for
    creating async actions, especially when combined with redux-thunk! */

export const actions = {
  selectOpponent
}

// ------------------------------------
// Action Handlers
// ------------------------------------
const ACTION_HANDLERS = {
  [SELECT_OPPONENT]    : (state, action) => state + action.payload,
}

// ------------------------------------
// Reducer
// ------------------------------------
const initialState = 0
export default function opponentReducer (state = initialState, action) {
  const handler = ACTION_HANDLERS[action.type]

  return handler ? handler(state, action) : state
}
