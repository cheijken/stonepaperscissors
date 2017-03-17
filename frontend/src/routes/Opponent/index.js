import { injectReducer } from '../../store/reducers'

export default (store) => ({
  path : 'opponent',
  /*  Async getComponent is only invoked when route matches   */
  getComponent (nextState, cb) {
    /*  Webpack - use 'require.ensure' to create a split point
        and embed an async module loader (jsonp) when bundling   */
    require.ensure([], (require) => {
      /*  Webpack - use require callback to define
          dependencies for bundling   */
      const Opponent = require('./containers/OpponentContainer').default
      const reducer = require('./modules/opponent').default

      /*  Add the reducer to the store on key 'opponent'  */
      injectReducer(store, { key: 'opponent', reducer })

      /*  Return getComponent   */
      cb(null, Opponent)

    /* Webpack named bundle   */
    }, 'opponent')
  }
})
