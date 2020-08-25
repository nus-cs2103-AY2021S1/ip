package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * Represents an add command. An AddCommand object represents a command
 * to insert a task into a TaskList. This task can be either a todo, deadline or event.
 */
public class AddCommand extends Command {

    /** String array storing the user input. */
    private String[] stringArray;

    /** Done status of the task associated with the command. */
    private boolean isDone;

    /**
     * Creates a new AddCommand and initialises its done status to false.
     *
     * @param stringArray Tokenized array form of the input command string.
     */
    public AddCommand(String[] stringArray) {
        super(stringArray);
        this.isDone = false;
    }

    /**
     * Creates a new AddCommand and allows the initialisation of its done status.
     *
     * @param stringArray Tokenized array form of the input command string.
     * @param isDone The done status to set for the task represented by the command.
     */
    public AddCommand(String[] stringArray, boolean isDone) {
        super(stringArray);
        this.isDone = isDone;
    }

    /**
     * Processes the string array and returns the correct task to be added into the task list.
     * This can be a todo task with the task name, a deadline task with a date and/or time,
     * or an event task with a date and/or time.
     *
     * @param delimiter Represents the delimiter that is used to separate names and the date of the task.
     * @param taskType Represents the type of the task (todo, deadline, event).
     * @return The task to be added into the task list.
     * @throws DukeException If task string does not contain dates (for deadline and event tasks),
     * or has wrong date/time formatting.
     */
    public Task processTask(String delimiter, String taskType) throws DukeException {
        //Task Name Only
        String taskName = Arrays.stream(getArray()).takeWhile(e -> !e.equals(delimiter)).skip(1)
                .collect(Collectors.joining(" "));
        //Date + Time, each in a single array cell
        String[] dateTime = Arrays.stream(getArray()).dropWhile(e -> !e.equals(delimiter)).skip(1)
                .collect(Collectors.joining(" ")).split(" ");

        if (dateTime.length < 1 || dateTime[0].equals("")) {
            throw new DukeException("All deadline/event tasks must come with a date in yyyy-mm-dd format!");
        }

        //Array to store 3 fields - task name, date and time (if available)
        ArrayList<String> newArray = new ArrayList<>();
        newArray.add(taskName);

        //More than necessary words or date and time in wrong format
        if (dateTime.length > 2) {
            throw new DukeException("Please make sure date is imputed in yyyy-mm-dd format. Any optional time" +
                    " parameter should be in HHmm format. Don't add any more characters after the date and time!");
        }
        //Append date and time into newArray if they exist
        for (int i = 0; i < dateTime.length; i++) {
            if (!dateTime[i].equals("")) {
                newArray.add(dateTime[i]);
            }
        }

        try {
            LocalDate taskDate = LocalDate.parse(newArray.get(1), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //Make sure deadline set is in the future
            LocalDate todayDate = LocalDate.now();
            if (taskDate.isBefore(todayDate)) {
                throw new DukeException("Date for deadline/event tasks must be set in the future!");
            }
            //Time Exists
            if (newArray.size() > 2) {
                LocalTime taskTime = LocalTime.parse(newArray.get(2), DateTimeFormatter.ofPattern("HHmm"));
                //Make sure time has not passed
                LocalTime timeNow = LocalTime.now();
                if (todayDate.isEqual(taskDate) && taskTime.isBefore(timeNow)) {
                    throw new DukeException("The date/time combination you specified has already passed!");
                }
                return (taskType.equals("deadline")
                        ? new Deadline(newArray.get(0), taskDate, taskTime)
                        : new Event(newArray.get(0), taskDate, taskTime));
            } else {
                return (taskType.equals("deadline") ? new Deadline(newArray.get(0), taskDate)
                        : new Event(newArray.get(0), taskDate));
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Check your date/time! All deadline/event tasks' date must be in yyyy-mm-dd " +
                    "format" +
                    " and any time specified must be in HHmm format! (i.e. 2021-10-05 1800)");
        }
    }

    /**
     * Carries out the addition of a task to the task list specified.
     *
     * @param taskList The task list to add the new task into.
     * @return The same task that is added to the task list.
     * @throws DukeException If task string does not contain task name, is unrecognized,
     * or the delimiter used to process deadline/event tasks.
     */
    public Task addTask(TaskList taskList) throws DukeException {
        //Makes sure task name is available
        if (getArray().length < 2 || getFirstIndex().equals("")) {
            throw new DukeException("Your Task Name cannot be empty!");
        }
        switch (getArray()[0]) {
        case ("todo"):
            Task todo = new Todo(Arrays.stream(getArray()).skip(1).collect(Collectors.joining(" ")));
            taskList.addTask(todo);
            return todo;
        case ("deadline"):
            if (!containsString("/by")) {
                throw new DukeException("Your deadline task input must contain the delimiter /by to separate your " +
                        "task name and date!");
            }
            Task deadline = processTask("/by", "deadline");
            taskList.addTask(deadline);
            return deadline;
        case ("event"):
            if (!containsString("/at")) {
                throw new DukeException("Your event task input must contain the delimiter /at to separate your " +
                        "task name and date!");
            }
            Task event = processTask("/at", "event");
            taskList.addTask(event);
            return event;
        default:
            throw new DukeException("I don't understand what task you want to be added! Only deadline / todo / event!");
        }
    }

    /**
     * Carries out the addition of a task from a local file to the task list specified.
     *
     * @param taskList The task list to operate on.
     * @throws DukeException If the addition of the task fails.
     */
    public void executeFromFile(TaskList taskList) throws DukeException {
        Task task = addTask(taskList);
        if (this.isDone) {
            task.markDone();
        }
    }

    /**
     * Executes the addition of tasks and prints notifications to users once that is successful.
     * Also writes the task list to a user-specified file.
     *
     * @param tasks The task list to operate on.
     * @param ui The user-interaction object responsible for all system printing and user-interaction.
     * @param storage Represents the logic needed to write to an user-specified file.
     * @throws DukeException If the addition of the task fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        addTask(tasks);
        ui.showTaskAdded(tasks);
        storage.write(tasks);
    }

}
