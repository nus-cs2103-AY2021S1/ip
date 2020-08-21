package duke;

import duke.exception.*;
import duke.task.*;

import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getTasksSize() {
        return tasks.size();
    }

    public Task completeTask(String input) throws DukeInvalidListNumberInputException {
        // Obtain index within list of tasks
        int index = Integer.parseInt(input.substring(5)) - 1;

        try {
            Task task = tasks.get(index);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidListNumberInputException();
        }
    }

    public Task deleteTask(String input) throws DukeInvalidListNumberInputException {
        // Obtain index within list of tasks
        int index = Integer.parseInt(input.substring(7)) - 1;

        try {
            Task task = tasks.get(index);
            tasks.remove(index);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidListNumberInputException();
        }
    }

    public Task addTask(String tag, String input)
            throws DukeInvalidTaskDescriptionException, DukeInvalidTaskTimeException {
        Task toAdd = null;
        try {
            switch (tag) {
                case "todo":
                    toAdd = addToDo(input);
                    break;
                case "event":
                    toAdd = addEvent(input);
                    break;
                case "deadline":
                    toAdd = addDeadline(input);
                    break;
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeInvalidTaskDescriptionException();
        } catch (DateTimeParseException e) {
            throw new DukeInvalidTaskTimeException();
        }
        return toAdd;
    }

    private Task addToDo(String input) {
        String toDoText = input.substring(5);
        ToDo toDo = new ToDo(toDoText);
        tasks.add(toDo);
        return toDo;
    }

    private Task addEvent(String input) throws DukeInvalidEventTimeException {
        String[] eventText = input.substring(6).split(" /at ");
        String eventDescription = eventText[0];

        if (eventText.length == 1) {
            throw new DukeInvalidEventTimeException();
        }

        String eventAt = eventText[1];
        Event event = new Event(eventDescription, eventAt);
        tasks.add(event);
        return event;
    }

    private Task addDeadline(String input) throws DukeInvalidDeadlineTimeException {
        String[] deadlineText = input.substring(9).split(" /by ");
        String deadlineDescription = deadlineText[0];

        if (deadlineText.length == 1) {
            throw new DukeInvalidDeadlineTimeException();
        }

        String deadlineBy = deadlineText[1];
        Deadline deadline = new Deadline(deadlineDescription, deadlineBy);
        tasks.add(deadline);
        return deadline;
    }

}
