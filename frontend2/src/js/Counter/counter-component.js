/** Counter presnetatoinal react component **/
import React, {PropTypes} from 'react'

const CounterComponent = ({currentCount, onCountDown, onCountUp}) => {
  return (
    <div>
      <h1>React redux counter</h1>
      <p>Current count: {currentCount}</p>
      <button onClick={() => onCountDown()}>-</button>
      <button onClick={() => onCountUp()}>+</button>
    </div>
  )
}

CounterComponent.propTypes = {
  currentCount: PropTypes.number.isRequired,
  onCountDown: PropTypes.func.isRequired,
  onCountUp: PropTypes.func.isRequired
}

export default CounterComponent
