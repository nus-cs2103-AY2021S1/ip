package main.java;

/**
 * Tasks are items that need to be done
 *
 * @author Lio
 */
class Task {
    String name;
    boolean done = false;

    /**
     * Constructor
     *
     * @param name Name of the task
     */
    public Task(String name) {
        this.name = name;
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

        task.done = Boolean.parseBoolean(params[1]);
        return task;
    }

    /**
     * Converts the task to data form
     */
    public String toData() {
        return done + " | " + name;
    }

    @Override
    public String toString() {
        return "[" + (done ? "✓" : "✗") + "] " + name;
    }
}
