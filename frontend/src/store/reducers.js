import { combineReducers } from 'redux'
import locationReducer from './location'
import HomeView from '../routes/Home/components/HomeViewAction'

export const makeRootReducer = (asyncReducers) => {
  return combineReducers({
    homeView: HomeView,
    location: locationReducer,
    ...asyncReducers
  })
}

export const injectReducer = (store, { key, reducer }) => {
  if (Object.hasOwnProperty.call(store.asyncReducers, key)) return

  store.asyncReducers[key] = reducer
  store.replaceReducer(makeRootReducer(store.asyncReducers))
}

export default makeRootReducer
