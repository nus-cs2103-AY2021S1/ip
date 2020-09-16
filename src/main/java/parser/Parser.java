package parser;

import java.time.LocalDate;

import data.Deadline;
import data.Events;
import data.Task;
import data.TaskList;
import data.ToDo;

import exception.CommandException;
import exception.DateTimeException;
import exception.DescriptionException;
import exception.DukeException;
import exception.TrackingException;

import storage.Storage;

/**
 * Parser for Duke class.
 * Parses the various commands and makes necessary changes to the TaskList and Storage.
 */
public class Parser {
    private TaskList tasks;
    private Storage storage;
    private boolean isExit = false;
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Checks for the validity of date time input.
     *
     * @param dateString The date in string.
     * @return True if the date input is valid.
     * @throws DateTimeException
     */

    private boolean isValidDate(String dateString) throws DateTimeException {
        if (dateString.length() != 10) {
            throw new DateTimeException();
        }
        String[] dateArray = dateString.split("-");
        // Checks if the date and time are in the correct format.
        if (dateArray.length != 3
            || dateArray[0].length() != 4 && dateArray[1].length() != 2 && dateArray[2].length() != 2) {
            throw new DateTimeException();
        }

        return true;

    }

    /**
     * Checks for validity of input given.
     *
     * @param fullInput user's full input
     * @return true if the input is valid.
     * @throws DukeException
     */
    private boolean isValidInput(String fullInput) throws DukeException {
        if (fullInput.equals("event") || fullInput.equals("deadline") || fullInput.equals("todo")
            || fullInput.equals("done") || fullInput.equals("find") || fullInput.equals("snooze")) {

            throw new DescriptionException(fullInput);

        } else if (fullInput.startsWith("snooze ")) {

            String[] commands = fullInput.split(" ");
            String dateString = commands[2];

            isValidDate(dateString);
            return true;

        } else if (fullInput.startsWith("find ")) {
            if (fullInput.length() <= 5) {
                throw new DescriptionException("find");
            }
            return true;
        } else if (fullInput.startsWith("todo ")) {
            if (fullInput.length() <= 5) {
                throw new DescriptionException("todo");
            }
            return true;
        } else if (fullInput.startsWith("deadline ")) {

            if (fullInput.length() <= 9) {
                throw new DescriptionException("deadline");
            }

            String descriptionDate = fullInput.substring(9);
            if (!descriptionDate.contains(" /by ")) {
                throw new TrackingException("deadline");
            }

            String[] temp = descriptionDate.split(" /by ");

            //Checks for validity of the processed input.
            if (temp.length == 1) {
                throw new TrackingException("event");
            }
            String description = temp[0];
            String deadlineString = temp[1];

            if (description.length() == 0) {
                throw new DescriptionException("deadline");
            }
            if (deadlineString.length() == 0) {
                throw new TrackingException("deadline");
            }

            isValidDate(deadlineString);
            return true;

        } else if (fullInput.startsWith("event ")) {
            if (fullInput.length() <= 6) {
                throw new DescriptionException("event");
            }
            String descriptionDate = fullInput.substring(6);
            if (!fullInput.contains(" /at ")) {
                throw new TrackingException("event");
            }

            String[] temp = descriptionDate.split(" /at ");

            //Checks for validity of processed input.
            if (temp.length == 1) {
                throw new TrackingException("event");
            }

            String description = temp[0];
            String atString = temp[1];

            if (description.length() == 0) {
                throw new DescriptionException("event");
            }
            if (atString.length() == 0) {
                throw new TrackingException("event");
            }

            isValidDate(atString);
            return true;

        } else if (fullInput.equals("list") || fullInput.startsWith("delete ") || fullInput.startsWith("done ")) {
            return true;

        } else {
            throw new CommandException(fullInput);
        }
    }

    /**
     * Parses the input, and makes necessary changes to tasks and storage.
     *
     * @param fullInput Command from the user.
     */

    public String parse(String fullInput) {
        assert fullInput.length() > 0;
        String result = "";

        try {
            isValidInput(fullInput);

            if (fullInput.equals("list")) {
                //Lists the tasks in the task list.
                result = tasks.list();
            } else if (fullInput.startsWith("delete ")) {
                //Deletes a task in the task list.
                int num = Integer.parseInt(fullInput.substring(7));
                result = tasks.delete(num);
            } else if (fullInput.startsWith("done ")) {
                //Marks a task as done in the task list.
                int num = Integer.parseInt(fullInput.substring(5));
                result = tasks.doTask(num);
            } else if (fullInput.startsWith("snooze ")) {
                //Snoozes a task, and postpone the task to a later date.
                String[] commands = fullInput.split(" ");
                int taskNum = Integer.parseInt(commands[1]) - 1;
                String dateString = commands[2];
                String[] dateArray = dateString.split("-");
                result = tasks.snoozeTask(taskNum, LocalDate.of(Integer.parseInt(dateArray[0]),
                    Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2])));

            } else if (fullInput.equals("bye")) {
                //Saves the task list into the hard drive and terminates the program.
                this.storage.save(tasks);
                this.isExit = true;
                result = "Bye. Hope to see you again soon! Bahahahaha!\n"
                    + "____________________________________________________________\n";

            } else if (fullInput.startsWith("find ")) {

                result = tasks.find(fullInput.substring(5));

            } else if (fullInput.startsWith("todo ")) {

                String description = fullInput.substring(5);
                Task newTask = new ToDo(description);
                //Adds task into the task list.
                result = tasks.add(newTask);

            } else if (fullInput.startsWith("deadline ")) {

                String descriptionDate = fullInput.substring(9);

                //Processes the input
                String[] temp = descriptionDate.split(" /by ");
                String description = temp[0];
                String deadlineString = temp[1];
                String[] dateTime = deadlineString.split("-");
                LocalDate by = LocalDate.of(Integer.parseInt(dateTime[0]), Integer.parseInt(dateTime[1]),
                    Integer.parseInt(dateTime[2]));
                Task task = new Deadline(description, by);

                //Adds task into the task list.
                result = tasks.add(task);

            } else if (fullInput.startsWith("event ")) {

                String descriptionDate = fullInput.substring(6);

                //Processes the input
                String[] temp = descriptionDate.split(" /at ");
                String description = temp[0];
                String atString = temp[1];
                String[] dateTime = atString.split("-");
                LocalDate at = LocalDate.of(Integer.parseInt(dateTime[0]), Integer.parseInt(dateTime[1]),
                    Integer.parseInt(dateTime[2]));
                Task task = new Events(description, at);

                //Adds task into task list.
                result = tasks.add(task);

            } else {
                //Checks for invalid input where command is unknown.
                throw new CommandException(fullInput);
            }
        } catch (DukeException ex) {
            result = ex.toString();
        }
        return result;
    }

    public boolean isExit() {
        return this.isExit;
    }
}
