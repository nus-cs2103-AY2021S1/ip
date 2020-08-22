package main.java;

import java.time.LocalDate;

public class Todo extends Task{

    public Todo(String description) {
        this(description,false);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String saveFormat() {
        return "T" + "~" + super.saveFormat();
    }

    @Override
    public boolean hasSameDate(LocalDate theDate) {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
