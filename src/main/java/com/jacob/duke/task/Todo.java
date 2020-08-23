package main.java.com.jacob.duke.task;


import main.java.com.jacob.duke.task.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.type = "T";
    }

}
