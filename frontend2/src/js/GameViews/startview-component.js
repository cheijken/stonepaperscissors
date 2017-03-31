import React, {PropTypes} from 'react'

const StartPageComponent = () => {
  return (
    <div className="startpage-container">
      <h1>Welcome to Rock Papers Scissors!</h1>
      <input type="text" placeholder="Nickname" className="input-nickname"/>
      <button className="input-button"> Start Game </button>
    </div>
  )
}

StartPageComponent.propTypes = {
}

export default StartPageComponent
