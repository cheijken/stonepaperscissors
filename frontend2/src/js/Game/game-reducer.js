import {START_GAME, SET_PLAYER_ID, MOVE, OPPONENT_MOVE, WIN, LOOSE, RESTART, NEW_GAME} from './game-action'

const initialState = {
  playerName: '',
  opponentName: '',
  playerId: 0,
  move: '',
  opponentMove: '',
  result: ''
};

const GameReducer = (state = initialState, action = {}) => {
  switch (action.type) {
    case START_GAME: {
      return  Object.assign({}, state, { playerName: action.playerName })
    }
    case SET_PLAYER_ID: {
      return  Object.assign({}, state, { playerId: action.playerId })
    }
    case MOVE: {
      return  Object.assign({}, state, { move: action.move })
    }
    case OPPONENT_MOVE: {
      return  Object.assign({}, state, { opponentMove: action.opponentMove })
    }
    case WIN: {
      return  Object.assign({}, state, { result: 'WIN' })
    }
    case LOOSE: {
      return  Object.assign({}, state, { result: 'LOOSE' })
    }
    case RESTART: {
      return  Object.assign({}, state, { result: '', move: '', opponentMove: '' })
    }
    case NEW_GAME: {
      return  initialState
    }
    default:
      return state
  }
};

export default GameReducer
