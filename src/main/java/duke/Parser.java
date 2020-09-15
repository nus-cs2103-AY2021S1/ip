package duke;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        String commandKeyword = commandParts[0];
        int commandNumber = -1;

        if (commandKeyword.contains("todo")) {
            commandNumber = 1;
        } else if (commandKeyword.contains("deadline")) {
            commandNumber = 2;
        } else if (commandKeyword.contains("event")) {
            commandNumber = 3;
        } else if (commandKeyword.contains("done")) {
            commandNumber = 4;
        } else if (commandKeyword.contains("list")) {
            commandNumber = 5;
        } else if (commandKeyword.contains("bye")) {
            commandNumber = 6;
        } else if (commandKeyword.contains("delete")) {
            commandNumber = 7;
        } else if (commandKeyword.contains("find")) {
            commandNumber = 8;
        } else if (commandKeyword.contains("help")) {
            commandNumber = 9;
        }
        return commandNumber;
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
            DateFormat deadlineInputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            DateFormat deadlineOutputFormat = new SimpleDateFormat("dd MMM yyyy hh:mm aa");
            Date deadline = deadlineInputFormat.parse(deadlineParts[1].trim());
            String afterDateTimeFormat = deadlineOutputFormat.format(deadline);
            Task newDeadlineTask = new Deadline(deadlineParts[0].trim(), afterDateTimeFormat);
            return newDeadlineTask;

        } catch (Exception e) {
            String emptyDescription = "Oops! The description cannot be empty :(";
            String deadlineKeyword = "Did you remember to put /by ?";
            String deadlineFormat = "The format should be dd/MM/yyyy HH:mm (24 hour clock)";
            throw new MissingTaskDescriptionException(emptyDescription
                    + "\r\n"
                    + deadlineKeyword
                    + "\r\n"
                    + deadlineFormat);
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
            String[] dateAndTime = eventParts[1].trim().split("\\s");
            String[] timeParts = dateAndTime[1].split("-");

            DateFormat eventDateInputFormat = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat eventTimeInputFormat = new SimpleDateFormat("HH:mm");
            DateFormat eventDateOutputFormat = new SimpleDateFormat("dd MMM yyyy");
            DateFormat eventTimeOutputFormat = new SimpleDateFormat("hh:mm aa");
            Date eventDate = eventDateInputFormat.parse(dateAndTime[0]);
            Date eventStartTime = eventTimeInputFormat.parse(timeParts[0]);
            Date eventEndTime = eventTimeInputFormat.parse(timeParts[1]);
            String eventDateFormatted = eventDateOutputFormat.format(eventDate);
            String eventStartTimeFormatted = eventTimeOutputFormat.format(eventStartTime);
            String eventEndTimeFormatted = eventTimeOutputFormat.format(eventEndTime);
            String dateAndTimeFormatted = (eventDateFormatted
                    + " " + eventStartTimeFormatted + " - " + eventEndTimeFormatted);
            Task newEventTask = new Deadline(eventParts[0].trim(), dateAndTimeFormatted);
            return newEventTask;

        } catch (Exception e) {
            String emptyDescription = "Oops! The description cannot be empty :(";
            String eventKeyword = "Did you remember to put /at ?";
            String eventFormat = "The format should be dd/MM/yy HH:mm-HH:mm (24 hour clock)";
            throw new MissingTaskDescriptionException(emptyDescription
                    + "\r\n"
                    + eventKeyword
                    + "\r\n"
                    + eventFormat);
        }
    }

    /**
     * Throws TaskNotFoundException if task number user inputs is bigger than size of TaskList.
     *
     * @param taskNumber Task number user inputs.
     * @param sizeOfTasks Size of existing Task.
     * @throws TaskNotFoundException thrown when task number is bigger than size of TaskList.
     */
    public void biggerThanSizeOfTasks(int taskNumber, int sizeOfTasks) throws TaskNotFoundException {
        biggerThanSizeOfTasksHelper(taskNumber + 1, sizeOfTasks);
    }

    /**
     *Helper class for biggerThanSizeOfTasks.
     *
     * @param sizeOfTasks Size of existing Task.
     * @throws TaskNotFoundException thrown when task number is bigger than size of TaskList.
     */
    public void biggerThanSizeOfTasksHelper(int taskNumber, int sizeOfTasks) throws TaskNotFoundException {
        try {
            if (taskNumber > sizeOfTasks || taskNumber == 0) {
                throw new TaskNotFoundException("Task not found :(");
            }
        } catch (Exception e) {
            throw new TaskNotFoundException("Task not found :(");
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
     * Helper class for getDoneTaskNumber().
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
    public int getDeleteTaskNumber() throws MissingTaskNumberException {
        int taskNumber = -1;
        taskNumber = deleteTaskFromList();
        return taskNumber;
    }

    /**
     * Helper class for getDeleteTaskNumber().
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
