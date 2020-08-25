package main.java.task;

import java.time.LocalDateTime;

class Todo extends Task {
    public Todo(String description) {
        super(description, LocalDateTime.MIN);
        super.type = "todo";
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                (super.isDone ? "\u2713" : "\u2718"),
                super.description);
    }
}
