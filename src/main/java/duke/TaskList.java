package duke;

import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> taskList) {
        this.list = taskList;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void processList(String fullCommand) throws InvalidDateException {
        if (fullCommand.trim().equalsIgnoreCase("list")) {
            printList();
        } else {

            LocalDate date = getDateTime(fullCommand.substring(
                    "list".length()).trim()).toLocalDate();

            printList(date);

        }
    }

    private void printList() {

        if (list.size() == 0) {
            System.out.println("You have nothing on your list!");
        }

        int i = 1;
        for (Task todo : list) {
            System.out.println(i + ". " + todo);
            i++;
        }

    }

    private void printList(LocalDate date) {

        int i = 0;
        for (Task task : list) {
            if (task.getDate().equals(date)) {
                if (i == 0) {
                    System.out.println("Here's your list on " +
                            date.format(DateTimeFormatter.ofPattern("dd MMM y:")));
                }

                System.out.println((i + 1) + ". " + task);
                i++;
            }
        }

        if (i == 0 || list.size() == 0) {
            System.out.println("You have nothing to do on " +
                    date.format(DateTimeFormatter.ofPattern("dd MMM y.")));
        }
    }

    public void addToDo(String input) throws DuplicateTaskException, EmptyTaskException {
        try {
            String task = input.substring("todo".length() + 1);

            Task toDo = new ToDo(task);
            if (list.contains(toDo)) {
                throw new DuplicateTaskException();
            }

            list.add(toDo);
        } catch (StringIndexOutOfBoundsException indexError) {
            throw new EmptyTaskException();
        }
    }

    public void addEvent(String input) throws DuplicateTaskException, EmptyTaskException, EventInvalidDate {
        String task;

        try {
            task = input.substring("event".length() + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyTaskException();
        }

        try {
            task = task.substring(0, task.indexOf('/'));
            LocalDateTime date = input.contains("to ")
                    ? getDateTime(input.substring(input.indexOf("/at") + 4, input.indexOf("to ")))
                    : getDateTime(input.substring(input.indexOf("/at") + 4));

            LocalDateTime endDate = null;
            if (input.contains("to ")) {
                String endDateString = input.substring(input.indexOf("to ") + 3);

                if (endDateString.length() <= 8) {
                    endDate = LocalDateTime.of(date.toLocalDate(), LocalTime.parse(endDateString));
                } else {
                    endDate = getDateTime(endDateString);
                }

            }

            Event event = endDate != null
                    ? new Event(task, date, endDate)
                    : new Event(task, date);

            if (list.contains(event)) {
                throw new DuplicateTaskException();
            } else {
                list.add(event);
            }

        } catch (StringIndexOutOfBoundsException | InvalidDateException e) {
            throw new EventInvalidDate();
        }

    }

    public void addDeadline(String input) throws DuplicateTaskException, EmptyTaskException, DeadlineInvalidDate {

        String task;

        try {
            task = input.substring("deadline".length() + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyTaskException();
        }

        try {

            task = task.substring(0, task.indexOf('/'));

            LocalDateTime date = getDateTime(input.substring(input.indexOf("/by") + 4));

            Deadline deadline = new Deadline(task, date);

            if (list.contains(deadline)) {
                throw new DuplicateTaskException();
            } else {
                list.add(deadline);
            }

        } catch (StringIndexOutOfBoundsException | InvalidDateException e) {
            throw new DeadlineInvalidDate();
        }
    }

    public void markDone(int taskNo) throws InvalidIndexException {
        try {
            list.set(taskNo, list.get(taskNo).markDone());

            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(taskNo));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(list.size());
        }
    }

    public void deleteTask(int taskNo) throws InvalidIndexException {
        try {
            Task deleted = list.get(taskNo);
            list.remove(taskNo);

            System.out.println("Noted. I've removed this task:");
            System.out.println(deleted);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(list.size());
        }
    }

    private LocalDateTime getDateTime(String dateString) throws InvalidDateException {

        dateString = dateString.trim();

        try {

            if (dateString.length() == 19 || dateString.length() == 16) {
                return LocalDateTime.parse(dateString);
            } else if (dateString.contains("-")) {
                return LocalDateTime.of(LocalDate.parse(dateString), LocalTime.MAX);
            } else if (dateString.contains(":")) {
                return LocalDateTime.of(LocalDate.now(), LocalTime.parse(dateString));
            } else {
                throw new InvalidDateException();
            }

        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    public void printListSize() {
        String taskText = list.size() == 1 ? " task " : " tasks ";
        System.out.println("You have " + list.size() + taskText + "on your list.");
    }

    public void printNewTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println(list.get(list.size() - 1).toString());

        printListSize();
    }

    public ArrayList<Task> getList() {
        return list;
    }
}
