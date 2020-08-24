// DatabaseStub.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura;

import java.util.List;

import java.io.IOException;

import ikura.task.Task;

import ikura.util.InvalidDatabaseException;


public class DatabaseStub extends Database {

    private List<Task> tasks;

    public DatabaseStub(List<Task> tasks) {
        super("asdf");
        this.tasks = tasks;
    }

    @Override
    public List<Task> loadTasks() throws IOException, InvalidDatabaseException {
        return this.tasks;
    }

    public void save(List<Task> list) throws IOException {
        this.tasks = list;
    }
}
