import React from 'react'

export const Gameend = (props) => (
  <div style={{ margin: '0 auto' }} >
    <h2>You loose/win</h2>
    {' '}
    <button className='btn btn-default' onClick={props.newGame}>
      New game
    </button>
    {' '}
  </div>
)

Gameend.propTypes = {
  newGame      : React.PropTypes.func.isRequired
}

export default Gameend
