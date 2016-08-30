package com.example.android.architecture.blueprints.todoapp.redux.impl;

import com.example.android.architecture.blueprints.todoapp.data.Task;
import com.example.android.architecture.blueprints.todoapp.redux.Reducer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TaskReducer implements Reducer<ActionFactory.Action, TaskState> {
    @Override
    public TaskState reduce(ActionFactory.Action action, TaskState state) {
        switch (action.getType()) {
            case ActionFactory.Action.ADD:
                return state.add(new Task(action.getTitle(), action.getDescription()));

            case ActionFactory.Action.DELETE:
                return state.remove(state.find(action.getId()));

            case ActionFactory.Action.COMPLETE:
                final Task taskCompleted = state.find(action.getId());
                return state
                    .remove(taskCompleted)
                    .add(new Task(taskCompleted.getTitle(), taskCompleted.getDescription(), taskCompleted.getId(), action.isCompleted()));

            case ActionFactory.Action.COMPLETE_ALL:
                final Set<Task> todoListToComplete = state.getTasks();
                final List<Task> completedList = new ArrayList<>(todoListToComplete.size());

                for (Task task : todoListToComplete) {
                    completedList.add(new Task(task.getTitle(), task.getDescription(), task.getId(), action.isCompleted()));
                }
                return TaskState.from(completedList);

            case ActionFactory.Action.CLEAR_ALL_COMPLETED:
                final Set<Task> todoListToClean = state.getTasks();
                final List<Task> clearedList = new ArrayList<>(todoListToClean.size());

                for (Task task : todoListToClean) {
                    if (!task.isCompleted()) {
                        clearedList.add(new Task(task.getTitle(), task.getDescription(), task.getId(), false));
                    }
                }
                return TaskState.from(clearedList);
            default:
                return state;
        }
    }
}
