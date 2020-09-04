package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    private String command;
    private String[] commandParts;
    private final String HORIZONTAL_LINE = "_______________________________________________________";

    public int parseCommand(String command) {
        this.command = command;
        this.commandParts = command.split("\\s", 2);

        if (commandParts[0].contains("todo")) {
            return 1;
        } else if (commandParts[0].contains("deadline")) {
            return 2;
        } else if (commandParts[0].contains("event")) {
            return 3;
        } else if (commandParts[0].contains("done")) {
            return 4;
        } else if (commandParts[0].contains("list")) {
            return 5;
        } else if (commandParts[0].contains("bye")) {
            return 6;
        } else if (commandParts[0].contains("delete")) {
            return 7;
        } else {
            return -1;
        }
    }

    public Task createTask(int commandNumber) throws MissingTaskDescriptionException {
        Task task = null;
//        try {
        if (commandNumber == 1) {
            task = createNewToDo(command);
        } else if (commandNumber == 2) {
            task = createNewDeadline(command);
        } else if (commandNumber == 3) {
            task = createNewEvent(command);
        }
//        } catch (MissingTaskDescriptionException e) {
//            System.out.println(e.getMessage());
//        }
        return task;
    }

    public Task createNewToDo(String command) throws MissingTaskDescriptionException {
        try {

            Task newToDoTask = new ToDo(commandParts[1]);
            return newToDoTask;

        } catch (Exception e) {
            throw new MissingTaskDescriptionException(HORIZONTAL_LINE
                    + "\r\n"
                    + "Oops! The description cannot be empty :("
                    + "\r\n"
                    + HORIZONTAL_LINE);
        }
    }

    public Task createNewDeadline(String command) throws MissingTaskDescriptionException {
        try {

            String[] deadlineParts = commandParts[1].split("/by");
            LocalDate deadline = LocalDate.parse(deadlineParts[1].trim());
            String afterDateTimeFormat = deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
            Task newDeadlineTask = new Deadline(deadlineParts[0].trim(), afterDateTimeFormat);
            return newDeadlineTask;

        } catch (Exception e) {

            throw new MissingTaskDescriptionException(HORIZONTAL_LINE
                    + "\r\n"
                    + "Oops! The description cannot be empty :("
                    + "\r\n"
                    + HORIZONTAL_LINE);
        }
    }

    public Task createNewEvent(String command) throws MissingTaskDescriptionException {
        try {

            String[] eventParts = commandParts[1].split("/at");
            LocalDate event = LocalDate.parse(eventParts[1].trim());
            String afterDateTimeFormat = event.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
            Task newEventTask = new Event(eventParts[0].trim(), afterDateTimeFormat);
            return newEventTask;

        } catch (Exception e) {

            throw new MissingTaskDescriptionException(HORIZONTAL_LINE
                    + "\r\n"
                    + "Oops! The description cannot be empty :("
                    + "\r\n"
                    + HORIZONTAL_LINE);
        }
    }

    public int getDoneTaskNumber() throws MissingTaskNumberException {

        int taskNumber = -1;
        taskNumber = markDoneTask(command);
        return taskNumber;
    }
        
    
    public int markDoneTask(String command) throws MissingTaskNumberException {
        
        try {
            int taskNumber = Integer.parseInt(commandParts[1].trim()) - 1;
            return taskNumber;

        } catch (Exception e) {

            throw new MissingTaskNumberException(HORIZONTAL_LINE
                    + "\r\n"
                    + "Oops! The task number cannot be missing :("
                    + "\r\n"
                    + HORIZONTAL_LINE);
        }
    }

    public int getDeleteTaskNumber() {
        int taskNumber = -1;

        try {
            taskNumber = deleteTaskFromList(command);
        } catch (MissingTaskNumberException e) {
            System.out.println(e.getMessage());
        }
        return taskNumber;
    }

    public int deleteTaskFromList(String command) throws MissingTaskNumberException {
        try {
            int taskNumber = Integer.parseInt(commandParts[1].trim()) - 1;
            return taskNumber;

        } catch (Exception e) {

            throw new MissingTaskNumberException(HORIZONTAL_LINE
                    + "\r\n"
                    + "Oops! The task number cannot be missing :("
                    + "\r\n"
                    + HORIZONTAL_LINE);
        }
    }
}