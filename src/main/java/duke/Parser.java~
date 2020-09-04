package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parser deals with making sense of the user command
 */
public class Parser {
    private String command;
    private String[] commandParts;
    private final String HORIZONTAL_LINE = "_______________________________________________________";

    /**
     * Returns the commandNumber.
     * 
     * @param command The user's input.
     * @return 
     */
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

    /**
     * Creates a new Task object.
     * 
     * @param commandNumber Takes in the commandNumber that parseCommand returns.
     * @return a Task object
     * @throws MissingTaskDescriptionException Exception is thrown when task description is missing.
     */
    public Task createTask(int commandNumber) throws MissingTaskDescriptionException {
        Task task = null;
        if (commandNumber == 1) {
            task = createNewToDo(command);
        } else if (commandNumber == 2) {
            task = createNewDeadline(command);
        } else if (commandNumber == 3) {
            task = createNewEvent(command);
        }
        return task;
    }

    /**
     * Creates a new ToDo object.
     * 
     * @param command Takes in the command that user inputs.
     * @return a ToDo object.
     * @throws MissingTaskDescriptionException Exception is thrown when task description is missing.
     */
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

    /**
     * Creates a new Deadline object.
     *
     * @param command Takes in the command that user inputs.
     * @return a Deadline object.
     * @throws MissingTaskDescriptionException Exception is thrown when task description is missing.
     */
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

    /**
     * Creates a new Event object.
     *
     * @param command Takes in the command that user inputs.
     * @return a Event object.
     * @throws MissingTaskDescriptionException Exception is thrown when task description is missing.
     */
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

<<<<<<< HEAD
    /**
     * Returns the task number of the task for its status to be updated.
     * 
     * @return the task number of the task.
     * @throws MissingTaskNumberException  Exception is thrown when task number is missing.
     */
    public int doneTask() throws MissingTaskNumberException {
=======
    public int getDoneTaskNumber() throws MissingTaskNumberException {
>>>>>>> branch-A-CodingStandard

        int taskNumber = -1;
        taskNumber = markDoneTask(command);
        return taskNumber;
    }

    /**
     * Helper class for doneTask().
     *
     * @param command Takes in the command that user inputs.
     * @return the task number of the task for status to be updated.
     * @throws MissingTaskNumberException Exception is thrown when task number is missing.
     */
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

<<<<<<< HEAD
    /**
     * Returns the task number of the task that is to be deleted.
     * 
     * @return the task number of the task.
     */
    public int deleteTask() {
=======
    public int getDeleteTaskNumber() {
>>>>>>> branch-A-CodingStandard
        int taskNumber = -1;

        try {
            taskNumber = deleteTaskFromList(command);
        } catch (MissingTaskNumberException e) {
            System.out.println(e.getMessage());
        }
        return taskNumber;
    }

    /**
     * Helper class for deleteTask().
     * 
     * @param command Takes in the command that user inputs.
     * @return the task number of the task that is to be deleted.
     * @throws MissingTaskNumberException Exception is thrown when task number is missing.
     */
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