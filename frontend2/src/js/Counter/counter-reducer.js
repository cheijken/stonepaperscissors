import {INCREMENT, DECREMENT} from './counter-action'

const initialState = {
  currentCount: 0,
}

const CounterReducer = (state = initialState, action = {}) => {
  switch (action.type) {
    case DECREMENT: {
      return  Object.assign({}, state, { currentCount: state.currentCount - action.payload })
    }
    case INCREMENT: {
      return  Object.assign({}, state, { currentCount: state.currentCount + action.payload })
    }
    default:
      return state
  }
}

export default CounterReducer
