import React from 'react'

export const Opponent = (props) => (
  <div style={{ margin: '0 auto' }} >
    <h2>Select opponent</h2>
    {' '}
    <button className='btn btn-default' onClick={props.selectOpponent}>
      Select Opponent
    </button>
    {' '}
  </div>
)

Opponent.propTypes = {
  availableOpponents  : React.PropTypes.array.isRequired,
  selectOpponent      : React.PropTypes.func.isRequired,
}

export default Opponent
