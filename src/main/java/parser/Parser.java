package parser;

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

import java.time.LocalDate;

public class Parser {
    private TaskList tasks;
    private Storage storage;
    private boolean isExit = false;
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;

    }

    public void parse(String fullInput) {
        try {
            if (fullInput.equals("list")) {
                //Lists the tasks in the task list.
                this.tasks.list();
            } else if (fullInput.startsWith("delete ")) {
                //Deletes a task in the task list.
                int num = Integer.parseInt(fullInput.substring(7));
                this.tasks.delete(num);
            } else if (fullInput.startsWith("done ")) {
                //Marks a task as done in the task list.
                int num = Integer.parseInt(fullInput.substring(5));
                this.tasks.doTask(num);
            } else if (fullInput.equals("bye")) {
                //Saves the task list into the hard drive and terminates the program.
                this.storage.save(this.tasks);
                this.isExit = true;
            } else if (fullInput.startsWith("todo ")) {
                //Checks for validity of input
                if (fullInput.length() <= 5) {
                    throw new DescriptionException("todo");
                }

                String description = fullInput.substring(5);
                Task newTask = new ToDo(description);
                //Adds task into the task list.
                this.tasks.add(newTask);

            } else if (fullInput.startsWith("deadline ")) {

                //Checks for validity of input
                if (fullInput.length() <= 9) {
                    throw new DescriptionException("deadline");
                }

                String descriptionDate = fullInput.substring(9);
                if (!descriptionDate.contains(" /by ")) {
                    throw new TrackingException("deadline");
                }

                //Processes the input
                String[] temp = descriptionDate.split(" /by ");
                String description = temp[0];
                String deadlineString = temp[1];

                //Checks for validity of the processed input.
                if (description.length() == 0) {
                    throw new DescriptionException("deadline");
                }
                if (deadlineString.length() == 0) {
                    throw new TrackingException("deadline");
                }
                String[] dateTime = deadlineString.split("-");
                // Checks if the date and time are in the correct format.
                if (dateTime.length != 3 ||
                    dateTime[0].length() != 4 && dateTime[1].length() != 2 && dateTime[2].length() != 2) {
                    throw new DateTimeException();
                }
                LocalDate by = LocalDate.of(Integer.parseInt(dateTime[0]), Integer.parseInt(dateTime[1]),
                    Integer.parseInt(dateTime[2]));
                Task task = new Deadline(description, by);

                //Adds task into the task list.
                this.tasks.add(task);

            } else if (fullInput.startsWith("event ")) {
                //Check for validity of input.
                if (fullInput.length() <= 6) {
                    throw new DescriptionException("event");
                }
                String descriptionDate = fullInput.substring(6);
                if (!fullInput.contains(" /at ")) {
                    throw new TrackingException("event");
                }
                //Processes the input
                String[] temp = descriptionDate.split(" /at ");
                String description = temp[0];
                String atString = temp[1];

                //Checks for validity of processed input.
                if (description.length() == 0) {
                    throw new DescriptionException("event");
                }
                if (atString.length() == 0) {
                    throw new TrackingException("event");
                }

                String[] dateTime = atString.split("-");

                // Check if the date and time are in the correct format.
                if (dateTime.length != 3 ||
                    dateTime[0].length() != 4 && dateTime[1].length() != 2 && dateTime[2].length() != 2 ) {
                    throw new DateTimeException();
                }
                LocalDate at = LocalDate.of(Integer.parseInt(dateTime[0]), Integer.parseInt(dateTime[1]),
                    Integer.parseInt(dateTime[2]));
                Task task = new Events(description, at);

                //Adds task into task list.
                this.tasks.add(task);

            } else if (fullInput.equals("event") ||(fullInput).equals("deadline") ||(fullInput).equals("todo") ||
                (fullInput).equals("done")) {
                //Checks for invalid input where description is blank.
                throw new DescriptionException(fullInput);
            } else {
                //Checks for invalid input where command is unknown.
                throw new CommandException(fullInput);
            }
        } catch (DukeException ex) {
            System.out.println(ex);
        }

    }

    public boolean isExit() {
        return this.isExit;
    }
}
