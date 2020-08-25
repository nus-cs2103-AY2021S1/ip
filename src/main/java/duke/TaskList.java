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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof TaskList) {
            TaskList t = (TaskList) o;
            return list.equals(t.list);
        } else {
            return false;
        }
    }

    public void processList(String fullCommand) throws InvalidDateException {
        if (fullCommand.trim().equalsIgnoreCase("list")) {
            printList();
        } else {

            LocalDate date = Parser.getDateTime(fullCommand.substring(
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

    public void addToDo(String task) throws DuplicateTaskException {
        Task toDo = new ToDo(task.trim());
        if (list.contains(toDo)) {
            throw new DuplicateTaskException();
        }

        list.add(toDo);
    }

    public void addEvent(String input) throws DuplicateTaskException, EventInvalidDate {

        try {
            String task = input.substring(0, input.indexOf('/')).trim();
            LocalDateTime date = input.contains("to ")
                    ? Parser.getDateTime(input.substring(input.indexOf("/at") + 4, input.indexOf("to ")))
                    : Parser.getDateTime(input.substring(input.indexOf("/at") + 4));

            LocalDateTime endDate = null;
            if (input.contains("to ")) {
                String endDateString = input.substring(input.indexOf("to ") + 3);

                if (endDateString.length() <= 8) {
                    endDate = LocalDateTime.of(date.toLocalDate(), LocalTime.parse(endDateString));
                } else {
                    endDate = Parser.getDateTime(endDateString);
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

    public void addDeadline(String input) throws DuplicateTaskException, DeadlineInvalidDate {

        try {

            String task = input.substring(0, input.indexOf('/')).trim();

            LocalDateTime date = Parser.getDateTime(input.substring(input.indexOf("/by") + 4));

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

    public void markDone(int taskIndex) throws InvalidIndexException {
        try {
            list.set(taskIndex, list.get(taskIndex).markDone());

            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(taskIndex));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(list.size());
        }
    }

    public void deleteTask(int taskIndex) throws InvalidIndexException {
        try {
            Task deleted = list.get(taskIndex);
            list.remove(taskIndex);

            System.out.println("Noted. I've removed this task:");
            System.out.println(deleted);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(list.size());
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
