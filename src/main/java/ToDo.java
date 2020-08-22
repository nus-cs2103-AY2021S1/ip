package main.java;

import java.time.LocalDate;

public class ToDo extends Task {
    ToDo(String title) {
        super(title);
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

    @Override
    LocalDate getDate() {
        return null;
    }
}
