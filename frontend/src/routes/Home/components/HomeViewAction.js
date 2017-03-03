export const PING = 'PING'

export const ping = () => {
  console.log('ping called!')
  return {
    type    : PING,
    payload : { reply : 'pong' }
  }
}










// ------------------------------------
// Action Handlers
// ------------------------------------
const ACTION_HANDLERS = {
  [PING]    : (state, action) => action.payload.reply
}

// ------------------------------------
// Reducer
// ------------------------------------
const initialState = {pingReply: 'No reply yet'}
export default function homeReducer (state = initialState, action) {
  const handler = ACTION_HANDLERS[action.type]

  return handler ? handler(state, action) : state
}
