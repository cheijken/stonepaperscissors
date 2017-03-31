import React from 'react'

export const Register = (props) => (
  <div style={{ margin: '0 auto' }} >
    <h2>Register player</h2>
    <span class="warning">{props.nicknameWarning}</span>
    {' '}
    <label for="inputNickname">Enter nickname</label>
    {' '}
    <input id="inputNickname" name="nickname" onchange={props.updateWantedNickname}/>
    {' '}
    <button className='btn btn-default' onClick={props.registerWantedNickname}>
      Register
    </button>
  </div>
)

Register.propTypes = {
  wantedNickname : React.PropTypes.string.isRequired,
  nicknameWarning : React.PropTypes.string.isRequired,
  updateWantedNickname : React.PropTypes.func.isRequired,
  registerWantedNickname   : React.PropTypes.func.isRequired
}

export default Register
