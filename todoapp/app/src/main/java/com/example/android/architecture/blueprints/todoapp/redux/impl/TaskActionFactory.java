package com.example.android.architecture.blueprints.todoapp.redux.impl;

import com.example.android.architecture.blueprints.todoapp.redux.Store;
import com.google.common.base.MoreObjects;

import static com.google.common.base.Preconditions.checkNotNull;

public class TaskActionFactory {
    private final Store<Action, TaskState> store;

    public TaskActionFactory(Store<Action, TaskState> store) {
        this.store = store;
    }

    public void init() {
        store.dispatch(Action.forInit());
    }

    public void add(String text, String description) {
        store.dispatch(Action.forAdd(text, description));
    }

    public void delete(String id) {
        store.dispatch(Action.forDelete(id));
    }

    public void complete(String id, boolean isCompleted) {
        store.dispatch(Action.forComplete(id, isCompleted));
    }

    public void completeAll(boolean isCompleted) {
        store.dispatch(Action.forCompleteAll(isCompleted));
    }

    public void clearCompleted() {
        store.dispatch(Action.forClearCompleted());
    }

    public static class Action implements com.example.android.architecture.blueprints.todoapp.redux.Action {
        public static final String INIT = "INIT";
        public static final String ADD = "ADD";
        public static final String DELETE = "DELETE";
        public static final String COMPLETE = "COMPLETE";
        public static final String COMPLETE_ALL = "COMPLETE_ALL";
        public static final String CLEAR_ALL_COMPLETED = "CLEAR_ALL_COMPLETED";
        public static final String UPDATE = "UPDATE";

        private final String type;
        private final String title;
        private final String description;
        private final String id;
        private final Boolean isCompleted;

        private Action(String type, String title, String description, String id, Boolean isCompleted) {
            this.type = type;
            this.title = title;
            this.description = description;
            this.isCompleted = isCompleted;
            this.id = id;
        }

        static Action forInit() {
            return new Action(INIT, null, null, null, null);
        }

        static Action forAdd(String text, String description) {
            return new Action(ADD, text, description, null, null);
        }

        static Action forDelete(String id) {
            return new Action(DELETE, null, null, id, null);
        }

        static Action forComplete(String id, boolean isCompleted) {
            return new Action(COMPLETE, null, null, id, isCompleted);
        }

        static Action forCompleteAll(boolean isCompleted) {
            return new Action(COMPLETE_ALL, null, null, null, isCompleted);
        }

        static Action forClearCompleted() {
            return new Action(CLEAR_ALL_COMPLETED, null, null, null, null);
        }

        String getType() {
            return type;
        }

        String getId() {
            return checkNotNull(id, "Id is undefined for this action(" + type + ")");
        }

        boolean isCompleted() {
            return checkNotNull(isCompleted, "isCompleted is undefined for this action(" + type + ")");
        }

        String getTitle() {
            return checkNotNull(title, "title is undefined for this action(" + type + ")");
        }

        String getDescription() {
            return checkNotNull(description, "title is undefined for this action(" + type + ")");
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                .add("type", type)
                .add("id", id)
                .add("isCompleted", isCompleted)
                .add("title", title)
                .add("description", description)
                .toString();
        }
    }
}
