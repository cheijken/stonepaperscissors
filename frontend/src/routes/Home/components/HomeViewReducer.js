import {
  PING,
} from './HomeViewAction'

const initialState = {
  loading: false,
  pingReply: 'No reply yet'
}

const HomeViewReducer = (state = initialState, action = {}) => {
  switch (action.type) {
    case PING:
      return {
        ...state,
        pingReply:  action.payload.reply
      }
    default:
      return state
  }
}

export default HomeViewReducer
