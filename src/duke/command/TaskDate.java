package duke.command;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exception.InvalidTaskDateException;

public class TaskDate {
    protected static String getDate(String userInput) throws InvalidTaskDateException {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d MMM yyyy, EEEE hh:mm a");
        String taskDateOutput = "";

        try {
            LocalDateTime taskDate = LocalDateTime.parse(userInput, inputFormat);
            taskDateOutput = taskDate.format(outputFormat);
        } catch (DateTimeException e) {
            throw new InvalidTaskDateException(userInput);
        }

        return taskDateOutput;
    }
}
