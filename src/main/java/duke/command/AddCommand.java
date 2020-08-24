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

public class AddCommand extends Command {

    protected String[] stringArray;
    protected boolean isDone;

    public AddCommand(String[] stringArray) {
        super(stringArray);
        this.isDone = false;
    }

    public AddCommand(String[] stringArray, boolean isDone) {
        super(stringArray);
        this.isDone = isDone;
    }

    public boolean containsString(String delimeter) {
        return Arrays.stream(getArray()).anyMatch(delimeter::equals);
    }

    public Task processTask(String delimeter, String taskType) throws DukeException {
        //Task Name Only
        String taskName = Arrays.stream(getArray()).takeWhile(e -> !e.equals(delimeter)).skip(1)
                .collect(Collectors.joining(" "));
        //Date + Time, each in a single array cell
        String[] dateTime = Arrays.stream(getArray()).dropWhile(e -> !e.equals(delimeter)).skip(1)
                .collect(Collectors.joining(" ")).split(" ");

        if (dateTime.length < 1 || dateTime[0].equals("")) {
            throw new DukeException("All deadline/event tasks must come with a date in yyyy-mm-dd format!");
        }

        //Array to store 3 fields - task name, date and time (if available)
        ArrayList<String> newArray = new ArrayList<>();
        //TaskName
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
                throw new DukeException("Your deadline task input must contain the delimeter /by to separate your " +
                        "task name and date!");
            }
            Task deadline = processTask("/by", "deadline");
            taskList.addTask(deadline);
            return deadline;
        case ("event"):
            if (!containsString("/at")) {
                throw new DukeException("Your event task input must contain the delimeter /at to separate your " +
                        "task name and date!");
            }
            Task event = processTask("/at", "event");
            taskList.addTask(event);
            return event;
        default:
            throw new DukeException("I don't understand what task you want to be added! Only deadline / todo / event!");
        }
    }

    public void executeFromFile(TaskList taskList) throws DukeException {
        Task task = addTask(taskList);
        if (this.isDone) {
            task.markDone();
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        addTask(tasks);
        ui.showTaskAdded(tasks);
        storage.write(tasks);
    }

}
