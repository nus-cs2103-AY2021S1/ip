package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Parser deals with making sense of the user command
 */
public class Parser {
    private String command;
    private String[] commandParts;

    /**
     * Returns the commandNumber.
     *
     * @param command The user's input.
     * @return commandNumber
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
        } else if (commandParts[0].contains("find")) {
            return 8;
        } else if (commandParts[0].contains("find")) {
            return 9;
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
            task = createNewToDo();
        } else if (commandNumber == 2) {
            task = createNewDeadline();
        } else if (commandNumber == 3) {
            task = createNewEvent();
        }
        return task;
    }

    /**
     * Creates a new ToDo object.
     *
     * @return a ToDo object.
     * @throws MissingTaskDescriptionException Exception is thrown when task description is missing.
     */
    public Task createNewToDo() throws MissingTaskDescriptionException {
        try {

            Task newToDoTask = new ToDo(commandParts[1]);
            return newToDoTask;

        } catch (Exception e) {
            throw new MissingTaskDescriptionException(
                    "Oops! The description cannot be empty :("
            );
        }
    }

    /**
     * Creates a new Deadline object.
     *
     * @return a Deadline object.
     * @throws MissingTaskDescriptionException Exception is thrown when task description is missing.
     */
    public Task createNewDeadline() throws MissingTaskDescriptionException {
        try {

            String[] deadlineParts = commandParts[1].split("/by");
            LocalDate deadline = LocalDate.parse(deadlineParts[1].trim());
            String deadlinePattern = "dd MMM yyyy";
            String afterDateTimeFormat = deadline.format(DateTimeFormatter.ofPattern(deadlinePattern));
            Task newDeadlineTask = new Deadline(deadlineParts[0].trim(), afterDateTimeFormat);
            return newDeadlineTask;

        } catch (Exception e) {
            throw new MissingTaskDescriptionException("Oops! The description cannot be empty :(");
        }
    }

    /**
     * Creates a new Event object.
     *
     * @return a Event object.
     * @throws MissingTaskDescriptionException Exception is thrown when task description is missing.
     */
    public Task createNewEvent() throws MissingTaskDescriptionException {
        try {

            String[] eventParts = commandParts[1].split("/at");
            LocalDate event = LocalDate.parse(eventParts[1].trim());
            String eventPattern = "dd MMM yyyy";
            String afterDateTimeFormat = event.format(DateTimeFormatter.ofPattern(eventPattern));
            Task newEventTask = new Event(eventParts[0].trim(), afterDateTimeFormat);
            return newEventTask;

        } catch (Exception e) {
            throw new MissingTaskDescriptionException("Oops! The description cannot be empty :(");
        }
    }

    /**
     * Returns the task number of the task for its status to be updated.
     *
     * @return the task number of the task.
     * @throws MissingTaskNumberException  Exception is thrown when task number is missing.
     */
    public int getDoneTaskNumber() throws MissingTaskNumberException {

        int taskNumber = -1;
        taskNumber = markDoneTask();
        return taskNumber;
    }

    /**
     * Helper class for doneTask().
     *
     * @return the task number of the task for status to be updated.
     * @throws MissingTaskNumberException Exception is thrown when task number is missing.
     */
    public int markDoneTask() throws MissingTaskNumberException {

        try {
            int taskNumber = Integer.parseInt(commandParts[1].trim()) - 1;
            return taskNumber;

        } catch (Exception e) {
            throw new MissingTaskNumberException("Oops! The task number cannot be missing :(");
        }
    }

    /**
     * Returns the task number of the task that is to be deleted.
     *
     * @return the task number of the task.
     */
    public int getDeleteTaskNumber() {
        int taskNumber = -1;

        try {
            taskNumber = deleteTaskFromList();
        } catch (MissingTaskNumberException e) {
            System.out.println(e.getMessage());
        }
        return taskNumber;
    }

    /**
     * Helper class for deleteTask().
     *
     * @return the task number of the task that is to be deleted.
     * @throws MissingTaskNumberException Exception is thrown when task number is missing.
     */
    public int deleteTaskFromList() throws MissingTaskNumberException {
        try {
            int taskNumber = Integer.parseInt(commandParts[1].trim()) - 1;
            return taskNumber;

        } catch (Exception e) {
            throw new MissingTaskNumberException("Oops! The task number cannot be missing :(");
        }
    }

    public ArrayList<Task> getMatchingTasks(TaskList tasks) throws MissingTaskDescriptionException {
        try {
            ArrayList<Task> matchingTasks = new ArrayList<>();
            boolean doesTaskMatch = false;

            String descriptionToFind = commandParts[1];;

            for (int i = 0; i < tasks.getSize(); i++) {
                ArrayList<Task> listOfTasks = tasks.getTaskList();
                Task task = listOfTasks.get(i);
                String descriptionOfTask = task.getDescription();
                String[] taskDescriptionParts = descriptionOfTask.split("\\s");

                for (int j = 0; j < taskDescriptionParts.length; j++) {
                    if (!doesTaskMatch) {
                        if (taskDescriptionParts[j].contains(descriptionToFind)) {
                            doesTaskMatch = true;
                            break;
                        }
                    }
                }

                if (doesTaskMatch) {
                    matchingTasks.add(task);
                }

                doesTaskMatch = false;
            }
            return matchingTasks;

        } catch (Exception e) {
            throw new MissingTaskDescriptionException("Oops! The description cannot be empty :(");
        }
    }
}
