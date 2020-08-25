package duke;

import duke.exception.DeadlineInvalidDate;
import duke.exception.DuplicateTaskException;
import duke.exception.EventInvalidDate;
import duke.exception.InvalidDateException;
import duke.exception.InvalidIndexException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/** Contains the task list. */
public class TaskList {

    /** The task list. */
    private ArrayList<Task> tasks;

    /**
     * Constructs a @TaskList.
     *
     * @param taskList The task list containing saved tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /** Constructs a @TaskList. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Compares two objects.
     *
     * @param o The object to compare.
     * @return True if the objects the same, in other words if the object is a @TaskList
     *         with a list containing the same tasks.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof TaskList) {
            TaskList t = (TaskList) o;
            return tasks.equals(t.tasks);
        } else {
            return false;
        }
    }

    /**
     * Processes the @list command.
     *
     * @param fullCommand The full command given by the user.
     * @throws InvalidDateException If the command is of the format @list @[description]
     *         but the @[description] is in not in a valid date format.
     */
    public void processList(String fullCommand) throws InvalidDateException {
        if (fullCommand.trim().equalsIgnoreCase("list")) {
            printList();
        } else {

            LocalDate date = Parser.getDateTime(fullCommand.substring(
                    "list".length()).trim()).toLocalDate();

            printList(date);

        }
    }

    /** Prints the tasks in the list. */
    private void printList() {

        if (tasks.size() == 0) {
            System.out.println("You have nothing on your list!");
        }

        int i = 1;
        for (Task todo : tasks) {
            System.out.println(i + ". " + todo);
            i++;
        }

    }

    /**
     * An overload of the @list command.
     * Prints the tasks occurring on the same @date in the list.
     *
     * @param date The date given by the user.
     */
    private void printList(LocalDate date) {

        int i = 0;
        for (Task task : tasks) {
            if (task.getDate().equals(date)) {
                if (i == 0) {
                    System.out.println("Here's your list on " +
                            date.format(DateTimeFormatter.ofPattern("dd MMM y:")));
                }

                System.out.println((i + 1) + ". " + task);
                i++;
            }
        }

        if (i == 0 || tasks.size() == 0) {
            System.out.println("You have nothing to do on " +
                    date.format(DateTimeFormatter.ofPattern("dd MMM y.")));
        }
    }

    /**
     * Adds a @ToDo task to the task list.
     *
     * @param task The description of the @ToDo task.
     * @throws DuplicateTaskException If an existing @ToDo task is already on the list.
     */
    public void addToDo(String task) throws DuplicateTaskException {
        Task toDo = new ToDo(task.trim());
        if (tasks.contains(toDo)) {
            throw new DuplicateTaskException();
        }

        tasks.add(toDo);
    }

    /**
     * Adds an @Event to the task list.
     *
     * @param input The description of the @Event.
     * @throws DuplicateTaskException If an existing @Event with the same description
     *         and date is already on the list.
     * @throws EventInvalidDate If the date of the event given is not in a valid date time format.
     */
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

            if (tasks.contains(event)) {
                throw new DuplicateTaskException();
            } else {
                tasks.add(event);
            }

        } catch (StringIndexOutOfBoundsException | InvalidDateException e) {
            throw new EventInvalidDate();
        }

    }

    /**
     * Adds a @Deadline to the task list.
     *
     * @param input The description of the deadline.
     * @throws DuplicateTaskException If an exsiting @Deadline with the same description
     *         and date is already on the list.
     * @throws DeadlineInvalidDate If the date of the deadline given is not in a valid date time format.
     */
    public void addDeadline(String input) throws DuplicateTaskException, DeadlineInvalidDate {

        try {

            String task = input.substring(0, input.indexOf('/')).trim();

            LocalDateTime date = Parser.getDateTime(input.substring(input.indexOf("/by") + 4));

            Deadline deadline = new Deadline(task, date);

            if (tasks.contains(deadline)) {
                throw new DuplicateTaskException();
            } else {
                tasks.add(deadline);
            }

        } catch (StringIndexOutOfBoundsException | InvalidDateException e) {
            throw new DeadlineInvalidDate();
        }
    }

    /**
     * Marks the task with the given index as done.
     *
     * @param taskIndex The index of the task to be marked.
     * @throws InvalidIndexException If the @taskIndex < 0 or larger than the size of the @taskList.
     */
    public void markDone(int taskIndex) throws InvalidIndexException {
        try {
            tasks.set(taskIndex, tasks.get(taskIndex).markDone());

            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskIndex));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(tasks.size());
        }
    }

    /**
     * Deletes the task with the given index.
     *
     * @param taskIndex The index of the task to be deleted.
     * @throws InvalidIndexException If the @taskIndex < 0 or larger than the size of the @taskList.
     */
    public void deleteTask(int taskIndex) throws InvalidIndexException {
        try {
            Task deleted = tasks.remove(taskIndex);

            System.out.println("Noted. I've removed this task:");
            System.out.println(deleted);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(tasks.size());
        }
    }

    /** Prints the size of the @taskList. */
    public void printListSize() {
        String taskText = tasks.size() == 1 ? " task " : " tasks ";
        System.out.println("You have " + tasks.size() + taskText + "on your list.");
    }

    /** Prints the recently added task. */
    public void printNewTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());

        printListSize();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
