import {browserHistory} from 'react-router';
// ------------------------------------
// Constants
// ------------------------------------

export const REGISTER_WANTED_NICKNAME = 'REGISTER_WANTED_NICKNAME'
export const UPDATE_WANTED_NICKNAME = 'UPDATE_WANTED_NICKNAME'
// ------------------------------------
// Actions
// ------------------------------------
export function updateWantedNickname  (value = "") {
  console.log('updateWantedNickname!');
  return {
    type    : UPDATE_WANTED_NICKNAME,
    payload : value
  }
}

/*  This is a thunk, meaning it is a function that immediately
    returns a function for lazy evaluation. It is incredibly useful for
    creating async actions, especially when combined with redux-thunk! */

export const registerWantedNickname= () => {
  console.log('registerWantedNickname!');
  browserHistory.push('/opponent');
  return (dispatch, getState) => {
    return new Promise((resolve) => {
      setTimeout(() => {
        dispatch({
          type    : REGISTER_WANTED_NICKNAME,
          payload :  { nickNameApproved: false, nickNameId: -1}

      })
        resolve()
      }, 200)
    })
  }
}

export const actions = {
  updateWantedNickname,
  registerWantedNickname
}

// ------------------------------------
// Action Handlers
// ------------------------------------
const ACTION_HANDLERS = {
  [UPDATE_WANTED_NICKNAME]    : (state, action) => state.wantedNickname = action.payload,
  [REGISTER_WANTED_NICKNAME]  : (state, action) => {
                state.nickNameApproved = action.payload.nickNameApproved;
                state.nickNameId = action.payload.nickNameId
          }
}

// ------------------------------------
// Reducer
// ------------------------------------
const initialState = {
  wantedNickname : '',
  nickNameApproved: false,
  nickNameId: -1
}
export default function registerReducer (state = initialState, action) {
  const handler = ACTION_HANDLERS[action.type]

  return handler ? handler(state, action) : state
}
