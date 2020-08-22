package main.java;

import java.time.LocalDate;

public class ToDo extends Task {
    ToDo(String title) {
        super(title);
    }
    ToDo(String title, boolean isDone) {
        super(title, isDone);
    }

    static ToDo of(String command) throws DukeException {
        if (command.length() <= 5) {
            throw new DukeException("ToDo cannot be empty.");
        }
        String content = command.substring(5);
        return new ToDo(content);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String print() {
        return "T | " + super.print();
    }

    @Override
    LocalDate getDate() {
        return null;
    }
}
