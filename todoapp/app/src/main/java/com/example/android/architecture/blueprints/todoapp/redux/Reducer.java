package com.example.android.architecture.blueprints.todoapp.redux;

public interface Reducer<A extends Action, S extends State> {
    S reduce(A action, S state);
}