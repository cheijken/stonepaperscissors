import HomeViewContainer from './components/HomeView'
import { injectReducer } from '../../store/reducers'

// Sync route definition
export default {
  component : HomeViewContainer,
  getComponent (nextState, cb) {
    /*  Webpack - use 'require.ensure' to create a split point
     and embed an async module loader (jsonp) when bundling   */
    require.ensure([], (require) => {
      /*  Webpack - use require callback to define
       dependencies for bundling   */
      const HomeViewContainer = require('./components/HomeView').default
      const reducer = require('./components/HomeViewReducer').default

      /*  Add the reducer to the store on key 'counter'  */
      injectReducer(store, { key: 'homeView', reducer })

      /*  Return getComponent   */
      cb(null, HomeViewContainer)

      /* Webpack named bundle   */
    }, 'homeView')
  }

}

