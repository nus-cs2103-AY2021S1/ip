package main.java;

import java.time.LocalDate;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
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
