package duke;

import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidTodoException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

class TaskList {

    public void addDeadline(Ui ui, ArrayList<Task> list, String str, boolean isNew, boolean isDone)
            throws InvalidDeadlineException {
        String[] deadline;
        if (isNew) {
            deadline = str.split("/by");
        } else {
            deadline = str.split("on");
        }
        if (deadline.length > 2) {
            throw new InvalidDeadlineException("☹ Task deadline must be written after `/by`.");
        }
        if (deadline[0].equals("")) {
            throw new InvalidDeadlineException("☹ Task description must be specified.");
        }
        if (deadline.length == 1) {
            throw new InvalidDeadlineException("☹ Task deadline must be specified.");
        }
        String description = deadline[0].trim();
        try {
            Deadline curr = new Deadline(description, isDone, LocalDate.parse(deadline[1].trim()));
            list.add(curr);
            if (isNew) {
                ui.describeTask(list, curr);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date format! Must be (yyyy-mm-dd).");
        }
    }

    public void addEvent(Ui ui, ArrayList<Task> list, String str, boolean isNew, boolean isDone)
            throws InvalidEventException {
        String[] event;
        if (isNew) {
            event = str.split("/at");
        } else {
            event = str.split("on");
        }
        if (event.length > 2) {
            throw new InvalidEventException("☹ Event time must be written after `/at`.");
        }
        if (event[0].equals("")) {
            throw new InvalidEventException("☹ Event description must be specified.");
        }
        if (event.length == 1) {
            throw new InvalidEventException("☹ Event time must be specified.");
        }
        String description = event[0].trim();
        try {
            Event curr = new Event(description, isDone, LocalDate.parse(event[1].trim()));
            list.add(curr);
            if (isNew) {
                ui.describeTask(list, curr);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date format! Must be (yyyy-mm-dd).");
        }
    }

    public void addTodo(Ui ui, ArrayList<Task> list, String str, boolean isNew, boolean isDone)
            throws InvalidTodoException {
        String description = str.trim();
        if (description.equals("")) {
            throw new InvalidTodoException("☹ Todo description must be specified.");
        }
        Todo curr = new Todo(description, isDone);
        list.add(curr);
        if (isNew) {
            ui.describeTask(list, curr);
        }
    }
}
