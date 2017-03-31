import {browserHistory} from 'react-router';
// ------------------------------------
// Constants
// ------------------------------------
export const MAKE_MOVE = 'MAKE_MOVE';

// ------------------------------------
// Actions
// ------------------------------------
export function makeMove (move = "") {
  console.log('makeMove!');
  browserHistory.push('/gameend');
  return {
    type    : MAKE_MOVE,
    payload : move
  }
}

/*  This is a thunk, meaning it is a function that immediately
    returns a function for lazy evaluation. It is incredibly useful for
    creating async actions, especially when combined with redux-thunk! */

export const actions = {
  makeMove
}

// ------------------------------------
// Action Handlers
// ------------------------------------
const ACTION_HANDLERS = {
  [MAKE_MOVE]    : (state, action) => state + action.payload,
}

// ------------------------------------
// Reducer
// ------------------------------------
const initialState = 0
export default function gameReducer (state = initialState, action) {
  const handler = ACTION_HANDLERS[action.type]

  return handler ? handler(state, action) : state
}
