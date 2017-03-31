import React from 'react'

export const Game = (props) => (
  <div style={{ margin: '0 auto' }} >
    <h2>Make move</h2>
    {' '}
    <button className='btn btn-default' onClick={props.makeMoveStone}>
      Stone
    </button>
    <button className='btn btn-default' onClick={props.makeMovePaper}>
      Paper
    </button>
    <button className='btn btn-default' onClick={props.makeMoveScissors}>
      Scissors
    </button>
    {' '}
  </div>
)

Game.propTypes = {
  makeMoveStone      : React.PropTypes.func.isRequired,
  makeMovePaper      : React.PropTypes.func.isRequired,
  makeMoveScissors      : React.PropTypes.func.isRequired
}

export default Game
