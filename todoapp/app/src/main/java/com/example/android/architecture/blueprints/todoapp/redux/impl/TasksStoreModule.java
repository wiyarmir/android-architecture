package com.example.android.architecture.blueprints.todoapp.redux.impl;

import com.example.android.architecture.blueprints.todoapp.redux.Store;
import dagger.Module;
import dagger.Provides;

@Module
public class TasksStoreModule {
    @Provides
    Store<TaskActionFactory.Action, TaskState> provideTasksStore() {
        return Store.create(TaskState.create(), new TaskReducer());
    }

    @Provides
    TaskActionFactory provideTaskActionFactory(Store<TaskActionFactory.Action, TaskState> store){
        return new TaskActionFactory(store);
    }
}
