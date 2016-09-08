package com.example.android.architecture.blueprints.todoapp.redux.impl;

import com.example.android.architecture.blueprints.todoapp.redux.Store;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {TasksStoreModule.class})
public interface TasksStoreComponent {
    Store<TaskActionFactory.Action, TaskState> tasksStore();
    TaskActionFactory taskActionFactory();
}
