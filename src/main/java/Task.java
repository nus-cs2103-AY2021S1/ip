package main.java;

import java.util.Arrays;

class Task {
    String name;
    boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    public static Task fromData(String data) throws Exception {
        String[] params = data.split(" \\| ");
        Task task;
        switch (params[0]) {
        case "T":
            task = new Todo(params[2]);
            break;
        case "D":
            task = new Deadline(params[2], params[3]);
            break;
        case "E":
            task = new Event(params[2], params[3]);
            break;
        default:
            throw new Exception("Invalid data structure");
        }

        task.done = Boolean.parseBoolean(params[1]);
        return task;
    }

    public String toData() {
        return done + " | " + name;
    }

    @Override
    public String toString() {
        return "[" + (done ? "✓" : "✗") + "] " + name;
    }
}
