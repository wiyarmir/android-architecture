package com.example.android.architecture.blueprints.todoapp.redux;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.android.architecture.blueprints.todoapp.data.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TaskState implements State {
    private static final Comparator<Task> TASK_COMPARATOR = new Comparator<Task>() {
        @Override
        public int compare(Task lhs, Task rhs) {
            return lhs.getId().compareTo(rhs.getId());
        }
    };

    private final TreeSet<Task> mTaskSet;

    public static TaskState create() {
        return new TaskState();
    }

    public static TaskState from(Task todo) {
        return new TaskState(Collections.singleton(todo));
    }

    public TaskState() {
        mTaskSet = new TreeSet<>(TASK_COMPARATOR);
    }

    private TaskState(@Nullable Collection<Task> tasks) {
        mTaskSet = new TreeSet<>(TASK_COMPARATOR);
        mTaskSet.addAll(tasks);
    }

    public TaskState add(@NonNull Task todo) {
        final TaskState copy = new TaskState(mTaskSet);
        copy.mTaskSet.add(todo);
        return copy;
    }

    public TaskState remove(@NonNull Task todo) {
        final TaskState copy = new TaskState(mTaskSet);
        copy.mTaskSet.remove(todo);
        return copy;
    }

    public Task find(@NonNull String id) {
        for (Task task : mTaskSet) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        throw new IllegalArgumentException("Can't find task with id " + id);
    }

    public Set<Task> getTasks() {
        return Collections.unmodifiableSet(mTaskSet);
    }
}
