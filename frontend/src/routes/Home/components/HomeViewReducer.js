import {
  START_NEW_GAME,
} from './HomeViewAction'

const initialState = {
  loading: false,
  pingReply: 'No reply yet'
}

const HomeViewReducer = (state = initialState, action = {}) => {
  console.log('HomeViewReducer!');
  switch (action.type) {
    case START_NEW_GAME:
      return {
        state: state
      }
    default:
      return state
  }
}

export default HomeViewReducer
