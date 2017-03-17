import {browserHistory} from 'react-router';

export const START_NEW_GAME = 'START_NEW_GAME';

export const startNewGame = () => {
  console.log('register!');
  browserHistory.push('/register');
  return {
    type    : START_NEW_GAME
  }
};










// ------------------------------------
// Action Handlers
// ------------------------------------
const ACTION_HANDLERS = {
  [START_NEW_GAME]    : (state, action) => browserHistory.push('/register')
};

// ------------------------------------
// Reducer
// ------------------------------------

export default function homeReducer (state, action) {
  console.log('homeReducer!');
  const handler = ACTION_HANDLERS[action.type];

  return handler ? handler(state, action) : state
}
