import React, {PropTypes} from 'react'

const EndComponent = () => {
  return (
    <div className="end-container">
      <h1>YOU win/lose!</h1>
      <button className="input-button" onClick={() => onRestart()}> Restart Game </button>
      <button className="input-button" onClick={() => onNew()}> New Game </button>
    </div>
  )
}

EndComponent.propTypes = {
    onRestart: PropTypes.func.isRequired,
    onNew: PropTypes.func.isRequired
}

export default EndComponent
