package com.sample.mvi.core

class ReduxStore(initialState: State, val reducers: List<Reducer>, val sideEffects: List<SideEffects>, val middleware: List<Middleware>) : Store {

    var currentState = initialState
    val stateChangeListeners = mutableListOf<StateChangeListener>()

    override fun dispatch(action: Action) {
        val newState = applyReducers(action)
        currentState = newState

        notifyListeners()

        sideEffects.forEach {
            it.runEffects(action, currentState, this)
        }

        middleware.forEach{
            it.applyMiddleware(action, currentState)
        }
    }

    private fun applyReducers(action: Action): State {
        var state = currentState
        for (reducer in reducers) {
            state = reducer.reduce(state, action)
        }

        return state
    }

    override fun getState(): State {
        return currentState
    }

    override fun subscribe(listener: StateChangeListener) {
        stateChangeListeners.add(listener)
    }

    override fun unsubscribe(listener: StateChangeListener) {
        stateChangeListeners.remove(listener)
    }

    fun notifyListeners() {
        stateChangeListeners.forEach{
            it.onUpdate(currentState)
        }
    }
}
