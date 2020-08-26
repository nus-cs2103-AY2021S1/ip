package duke;

import java.util.ArrayList;

/**
 * A general task class that handles the status of any task and methods to add a task to the task list.
 */

public class Task {
    private String description;
    private Boolean isDone;

     public Task(String name) {
        this.description = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
         this.description = name;
         this.isDone = isDone;
    }

    public boolean findTask(String name) {
        return description.contains(name);
    }

    /**
     * method to add tasks from given string format
     * @param task task in a string format
     * @param store the list of tasks
     * @param print should the task be printed
     */

    public static void addTaskFromFile(String task, ArrayList<Task> store, boolean print) {
        String type = task.split(" ", 2)[0];
        String remain = task.split(" ", 2)[1];
        String isDone = remain.split(" ", 2)[0];
        String title = remain.split(" ", 2)[1];
        //System.out.println(title);
        if (type.equals("todo")) {
            store.add(new Todo(title, Boolean.valueOf(isDone)));
        } else {
            String description = title.split("/", 2)[0];
            Task newTask;

            if (type.equals("deadline")) {
                String by = title.split("/by", 2)[1];
                newTask = new Deadline(description, by, Boolean.valueOf(isDone));
                store.add(newTask);
            }
            if (type.equals("event")) {
                String at = title.split("/at", 2)[1];
                newTask = new Event(description, at, Boolean.valueOf(isDone));
                store.add(newTask);
            }
        }
        if (print) {
            System.out.println("Got it. I've added this task:\n" + store.get(store.size() - 1));
        }
    }

    public String inputStyle() {
         return this.isDone + " " +description;
    }

    /**
     * display icon for a done/not done task
     * @return
     */
    public String getStatusIcon() {
         return (isDone) ? "\u2713" : "\u2718";
    }

    public void markAsDone() {
         this.isDone = true;
    }
    @Override
    public String toString() {
         return "[" + this.getStatusIcon() + "] " + description;
    }
}
