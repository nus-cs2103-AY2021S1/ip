package main.java;

import java.time.LocalDate;

/**
 * Tasks are items that need to be done
 *
 * @author Lio
 */
class Task {
    public final String name;
    public LocalDate date;
    private boolean isDone = false;

    /**
     * Constructor
     *
     * @param name Name of the task
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Constructor
     *
     * @param name Name of the task
     * @param date Date relevant to that task
     */
    public Task(String name, String date) {
        this.name = name;
        this.date = LocalDate.parse(date);
    }

    /**
     * Constructs a task from data form
     *
     * @param data Data form of the task
     * @return Task
     * @throws Exception If the data structure is invalid
     */
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

        task.isDone = Boolean.parseBoolean(params[1]);
        return task;
    }

    /**
     * Converts the task to data form
     */
    public String toData() {
        return isDone + " | " + name;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "Y" : "N") + "] " + name;
    }

    public boolean contains(String keyword) {
        return this.name.contains(keyword);
    }

    public void setDone() {
        this.isDone = true;
    }
}
