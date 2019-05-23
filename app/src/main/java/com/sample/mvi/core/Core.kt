package com.sample.mvi.core

interface Action

open class State

interface Reducer {
    fun reduce(state: State, action: Action): State
}

interface SideEffects {
    fun runEffects(action: Action, currentState: State, store: Store)
}

interface Middleware {
    fun applyMiddleware(action: Action, currentState: State)
}

interface Store {
    fun getState(): State
    fun dispatch(action: Action)
    fun subscribe(listener: StateChangeListener)
    fun unsubscribe(listener: StateChangeListener)
}

interface StateChangeListener {
    fun onUpdate(state: State)
}