package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

import duke.exception.DeadlineInvalidDate;
import duke.exception.DuplicateTaskException;
import duke.exception.EventInvalidDate;
import duke.exception.InvalidDateException;
import duke.exception.InvalidEndDate;
import duke.exception.InvalidIndexException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/** Contains the task list. */
public class TaskList {

    /** The task list. */
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList.
     *
     * @param taskList The task list containing saved tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /** Constructs a TaskList. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Compares two objects.
     *
     * @param o The object to compare.
     * @return True if the objects the same, in other words if the object is a TaskList
     * with a list containing the same tasks.
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
     * Processes the list command.
     *
     * @param fullCommand The full command given by the user.
     * @throws InvalidDateException If the command is of the format list [description]
     *                              but the [description] is in not in a valid date format.
     */
    public String processList(String fullCommand) throws InvalidDateException {
        if (fullCommand.trim().equalsIgnoreCase("list")) {
            return printList();
        } else {

            LocalDate date = Parser.getDateTime(fullCommand.substring(
                "list".length()).trim()).toLocalDate();

            return printList(date);

        }
    }

    /** Prints the tasks in the list. */
    private String printList() {

        if (tasks.size() == 0) {
            return "You have nothing on your list!";
        }

        StringBuilder str = new StringBuilder();

        int i = 1;
        for (Task todo : tasks) {
            str.append(String.format("%d. %s\n", i, todo));
            i++;
        }

        return str.toString();
    }

    /**
     * An overload of the list command.
     * Prints the tasks occurring on the same date in the list.
     *
     * @param date The date given by the user.
     */
    private String printList(LocalDate date) {

        StringBuilder str = new StringBuilder();

        int i = 0;
        for (Task task : tasks) {

            if (task.getDate().equals(date)) {
                if (i == 0) {
                    str.append("Here's your list on ");
                    str.append(date.format(DateTimeFormatter.ofPattern("dd MMM y")));
                    str.append(":\n");
                }

                str.append(String.format("%d. %s\n", (i + 1), task));
                i++;
            }
        }

        if (i == 0 || tasks.size() == 0) {
            return "You have nothing to do on "
                + date.format(DateTimeFormatter.ofPattern("dd MMM y."));
        }

        return str.toString();
    }

    /**
     * Adds a ToDo task to the task list.
     *
     * @param task The description of the ToDo task.
     * @throws DuplicateTaskException If an existing ToDo task is already on the list.
     */
    public void addToDo(String task) throws DuplicateTaskException {
        Task toDo = new ToDo(task.trim());
        if (tasks.contains(toDo)) {
            throw new DuplicateTaskException();
        }

        tasks.add(toDo);
    }

    /**
     * Adds an Event to the task list.
     *
     * @param input The description of the Event.
     * @throws DuplicateTaskException If an existing Event with the same description
     *                                and date is already on the list.
     * @throws EventInvalidDate       If the date of the event given is not in a valid date time format.
     */
    public void addEvent(String input) throws DuplicateTaskException, EventInvalidDate, InvalidEndDate {

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

                if (endDate.isBefore(date)) {
                    throw new InvalidEndDate();
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
     * Adds a Deadline to the task list.
     *
     * @param input The description of the deadline.
     * @throws DuplicateTaskException If an exsiting Deadline with the same description
     *                                and date is already on the list.
     * @throws DeadlineInvalidDate    If the date of the deadline given is not in a valid date time format.
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
     * @param taskNumbers The indexes of the tasks to be marked.
     * @throws InvalidIndexException If the taskNumbers < 0 or larger than the size of the taskList.
     */
    public String markDone(Integer... taskNumbers) throws InvalidIndexException {
        try {

            StringBuilder str = new StringBuilder();
            str.append("Nice! I've marked these tasks as done:\n");

            for (int taskNo: taskNumbers) {
                tasks.set(taskNo - 1, tasks.get(taskNo - 1).markDone());
                str.append(String.format("%s\n", tasks.get(taskNo - 1)));
            }

            return str.toString();

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(tasks.size());
        }
    }

    /**
     * Deletes the task with the given index.
     *
     * @param taskNumbers The numbers of the tasks to be deleted.
     * @throws InvalidIndexException If the taskNumbers < 1 or larger than the size of the taskList.
     */
    public String deleteTask(Integer... taskNumbers) throws InvalidIndexException {
        try {

            ArrayList<Task> deletedTasks = new ArrayList<>();

            for (Integer taskNo: taskNumbers) {
                deletedTasks.add(tasks.get(taskNo - 1));
                tasks.set(taskNo - 1, null);
            }

            tasks.removeIf(Objects::isNull);

            StringBuilder str = new StringBuilder();
            str.append("Noted. I've removed these tasks:\n");

            for (Task deleted: deletedTasks) {
                str.append(String.format("%s\n", deleted));
            }

            return str.toString();

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(tasks.size());
        }
    }

    /**
     * Finds tasks that contains any of the given keywords.
     *
     * @param keywords The keywords to search for in tasks.
     */
    public String findTasks(String... keywords) {

        StringBuilder str = new StringBuilder();

        int i = 0;
        boolean containsKeyword;

        for (Task task : tasks) {

            containsKeyword = false;

            for (String keyword: keywords) {
                if (task.getTask().contains(keyword.trim().toLowerCase())) {
                    containsKeyword = true;
                    break;
                }
            }

            if (containsKeyword) {
                if (i == 0) {
                    str.append("Here are the matching tasks on your list.\n");
                }

                str.append(String.format("%d. ", (i + 1)));
                str.append(String.format("%s\n", task));

                i++;
            }
        }

        if (i == 0) {
            System.out.println("OOPS. There are no tasks on your list with the following keyword.");
        }

        return str.toString();
    }

    /** Prints the recently added task. */
    public String printNewTask() {
        return String.format("Got it. I've added this task:\n %s\n"
            + "You have %d %s on your list.",
            tasks.get(tasks.size() - 1), tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
