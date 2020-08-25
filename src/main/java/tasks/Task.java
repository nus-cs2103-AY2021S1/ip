package main.java.tasks;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean hasTime;
    protected static int numberOfTasks = 0;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    public Task(String description, int isDone) {
        this.description = description;
        if (isDone == 0) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
        numberOfTasks++;
    }

    /**
     * Reduce the count of tasks
     */
    public static void reduceOneTasks() {
        numberOfTasks--;
    }

    /**
     * Get the tick or cross sign which indicates is a task is complete
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean getHasTime(){
        return hasTime;
    }

    public LocalDate getTime(){
        return null;
    }

    public String getDescription(){
        return description;
    }


    /**
     * mark a task as finished
     * @return
     */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Return the message when a task is marked as done
     * @return mark-as-done Message
     */
    public String markAsDoneMessage() {
        return "Nice!(^∇^) I've marked this task as done:\n"
                + this.toString().replace("\u2718", "\u2713");
    }

    /**
     * Return the message when a new task is added
     */
    public String addMessage() {
        return "Got it.(^∇^) I've added this task:\n"
                + this.toString();
    }

    /**
     * Return the message when a task is deleted
     */
    public String deleteMessage() {
        return "Got it.(^∇^) I've deleted this task:\n"
                + this.toString();
    }

    /**
     * Return info about the task in the format for data storage
     * @return A string
     */
    public String data() {
        return  " | " + (isDone
                ? "1"
                : "0" ) + " | " + description;
    }
}