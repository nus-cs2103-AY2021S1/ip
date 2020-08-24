// TaskList.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Stream;

import java.io.IOException;

import ikura.task.Task;
import ikura.util.InvalidDatabaseException;

public class TaskList {

    //* this can't be final, because the compiler is dumb
    private List<Task> tasks;
    private final Database db;

    public TaskList(Database db) {
        this.db = db;

        try {
            this.tasks = db.loadTasks();
        } catch (IOException e) {
            System.out.printf("error occured while reading/creating the task list:\n%s\n", e);
            this.tasks = new ArrayList<>();
        } catch (InvalidDatabaseException e) {
            System.out.printf("malformed line while reading task list:\n%s\n", e);
            this.tasks = new ArrayList<>();
        }
    }

    public void save() {

        try {
            this.db.saveTasks(this.tasks);
        } catch (IOException e) {
            System.out.printf("failed to save task list to disk:\n%s\n", e);
        }
    }

    public int count() {
        return this.tasks.size();
    }

    public Stream<Task> stream() {
        return this.tasks.stream();
    }

    public long getNumPendingTasks() {
        return this.tasks.stream().filter(x -> !x.isDone()).count();
    }

    public long getNumCompletedTasks() {
        return this.tasks.stream().filter(x -> x.isDone()).count();
    }

    public void addTask(Task task) {
        assert task != null;

        this.tasks.add(task);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    public void clearTasks() {
        this.tasks.clear();
    }

    public Optional<Task> getTaskByNumber(int number) {
        // asdf
        try {
            return Optional.of(this.tasks.get(number - 1));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }
}
