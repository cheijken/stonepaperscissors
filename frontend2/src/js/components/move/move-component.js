import React, {PropTypes} from 'react'

const MoveComponent = () => {
  return (
    <div className="move-container">
      <h1>Make move</h1>
      <button className="selectMove" onClick={() => onRock()}> Rock </button>
      <button className="selectMove" onClick={() => onPaper()}> Paper </button>
      <button className="selectMove" onClick={() => onScissors()}> Scissors </button>
    </div>
  )
}

MoveComponent.propTypes = {
    onRock: PropTypes.func.isRequired,
    onPaper: PropTypes.func.isRequired,
    onScissors: PropTypes.func.isRequired
}

export default MoveComponent
