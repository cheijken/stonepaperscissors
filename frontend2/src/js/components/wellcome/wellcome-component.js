import React, {PropTypes} from 'react'

const WellcomeComponent = () => {
  return (
    <div className="startpage-container">
      <h1>Welcome to Rock Papers Scissors!</h1>
      <input type="text" placeholder="Nickname" className="input-nickname"/>
      <button className="input-button" onClick={() => onStartGame()}> Start Game </button>
    </div>
  )
}

WellcomeComponent.propTypes = {
    nickname: PropTypes.string.isRequired,
    onStartGame: PropTypes.func.isRequired
}

export default WellcomeComponent
