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

    public static void reduceOneTasks() {
        numberOfTasks--;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean getHasTime(){
        return hasTime;
    }

    public LocalDate getTime(){
        return null;
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String markAsDoneMessage() {
        return "Nice!(^∇^) I've marked this task as done:\n"
                + this.toString().replace("\u2718", "\u2713");
    }

    public String addMessage() {
        return "Got it.(^∇^) I've added this task:\n"
                + this.toString();
    }

    public String deleteMessage() {
        return "Got it.(^∇^) I've deleted this task:\n"
                + this.toString();
    }

    public String data() {
        return  " | " + (isDone
                ? "1"
                : "0" ) + " | " + description;
    }
}