
export const START_GAME = 'START_GAME'
export const SET_PLAYER_ID = 'SET_PLAYER_ID'
export const MOVE = 'MOVE'
export const OPPONENT_MOVE = 'OPPONENT_MOVE'
export const OPPONENT_ACTIVE = 'OPPONENT_ACTIVE'
export const WIN = 'WIN'
export const LOOSE = 'LOOSE'
export const RESTART = 'RESTART'
export const NEW_GAME = 'NEW_GAME'

export const startGame = (nickname) => ({
    type: START_GAME,
    nickname: nickname
})

export const setPlayerId = (playerId) => ({
    type: SET_PLAYER_ID,
    playerId: playerId
})

export const makeMove = (move) => ({
    type: MOVE,
    move: move
})

export const opponentMoved = (move) => ({
    type: MOVE,
    opponentMove: opponentMove
})

export const opponentActive = (opponentName) => ({
    type: OPPONENT_ACTIVE,
    opponentName: opponentName
})


export const win = () => ({
    type: WIN,
})

export const loose = () => ({
    type: LOOSE,
})

export const restart = () => ({
    type: RESTART,
})

export const newGame = () => ({
    type: NEW_GAME,
})

