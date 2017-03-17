import { injectReducer } from '../../store/reducers'

export default (store) => ({
  path : 'gameend',
  /*  Async getComponent is only invoked when route matches   */
  getComponent (nextState, cb) {
    /*  Webpack - use 'require.ensure' to create a split point
        and embed an async module loader (jsonp) when bundling   */
    require.ensure([], (require) => {
      /*  Webpack - use require callback to define
          dependencies for bundling   */
      const Gameend = require('./containers/GameendContainer').default
      const reducer = require('./modules/gameend').default

      /*  Add the reducer to the store on key 'gameend'  */
      injectReducer(store, { key: 'gameend', reducer })

      /*  Return getComponent   */
      cb(null, Gameend)

    /* Webpack named bundle   */
    }, 'gameend')
  }
})
