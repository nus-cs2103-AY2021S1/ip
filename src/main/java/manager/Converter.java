package main.java.manager;

import main.java.exceptions.InvalidCommandException;
import main.java.tasks.Deadline;
import main.java.tasks.Event;
import main.java.tasks.Task;
import main.java.tasks.Todo;

public class Converter {

    public Task getTaskType(String input) {
        try {
            if (input.startsWith("deadline") && input.contains("/by")) {
                String description = input.substring("deadline".length(), input.indexOf("/by")).trim();
                String endTime = input.substring(input.indexOf("/by") + "/by".length()).trim();
                return new Deadline(description, endTime);

            } else if (input.startsWith("event") && input.contains("/at")) {
                String description = input.substring("event".length(), input.indexOf("/at")).trim();
                String time = input.substring(input.indexOf("/at") + "/at".length()).trim();
                return new Event(description, time);

            } else if (input.startsWith("todo")) {
                String description = input.substring("todo".length()).trim();
                return new Todo(description);

            } else {
                throw new InvalidCommandException("Not sure what you mean. " +
                        "Please ensure your command format is correct and try again.");
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
