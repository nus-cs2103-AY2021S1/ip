package duke;

import java.time.LocalDate;
import java.util.Objects;

public class Command {
    protected TaskType taskType;
    protected Integer index;
    protected String description;
    protected LocalDate date;

    public Command() {
        taskType = null;
    }

    public Command(TaskType taskType) {
        switch (taskType) {
        case LIST:
        case BYE:
            break;
        default:
            throw new IllegalArgumentException("You need to provide an index or description.");
        }
        this.taskType = taskType;
        this.index = null;
        this.description = null;
        this.date = null;
    }

    public Command(TaskType taskType, Integer index) {
        switch (taskType) {
        case DELETE:
        case DONE:
            break;
        default:
            throw new IllegalArgumentException("A description parameter is required for this kind of TaskType");
        }
        this.taskType = taskType;
        this.index = index;
        this.description = null;
        this.date = null;
    }

    public Command(TaskType taskType, String description) {
        if (taskType != TaskType.TODO) {
            throw new IllegalArgumentException("A date parameter is required for this kind of TaskType");
        }
        this.taskType = taskType;
        this.index = null;
        this.description = description;
        this.date = null;
    }

    public Command(TaskType taskType, String description, LocalDate date) {
        switch(taskType) {
        case DEADLINE:
        case EVENT:
            break;
        default:
            throw new IllegalArgumentException("A date parameter is irrelevant to the TaskType.");
        }
        this.taskType = taskType;
        this.index = null;
        this.description = description;
        this.date = date;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isValid() {
        return taskType != null;
    }

    private static String addToList(TaskList taskList, Task task) {
        taskList.add(task);
        return "Sure, I've added this task to your list:\n " + task
            + "\nYou now have " + taskList.size() + " task(s) in the list!";
    }

    public void execute(TaskList list) {
        if (!isValid()) {
            return;
        }

        String print = "";
        try {
            switch (taskType) {
            case LIST:
                print = "Here are the tasks in your list:";
                if (list.isEmpty()) {
                    print += "\nYour list is empty. How about adding some tasks?";
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        print += "\n " + (i + 1) + ": " + list.get(i);
                    }
                }
                break;
            case DELETE:
                Task deleted = list.remove(index - 1);
                print = "Sure, I've deleted this task from your list:\n " + deleted
                    + "\nYou now have " + list.size() + " task(s) in the list.";
                break;
            case DONE:
                Task doneTask = list.markTaskAsDone(index - 1);
                print = "Great! I've marked this task as done:\n " + doneTask;
                break;
            case TODO:
                print = addToList(list, new Todo(description));
                break;
            case DEADLINE:
                print = addToList(list, new Deadline(description, date));
                break;
            case EVENT:
                print = addToList(list, new Event(description, date));
                break;
            case BYE:
                break;
            default:
                throw new IllegalArgumentException(taskType + " is not supported as a TaskType.\n"
                    + "Contact the developer if you see this message.");
            }
        } catch (IndexOutOfBoundsException e) {
            print = "The index you provided was out of bounds.\nRun list to see your list of tasks.";
        } finally {
            Ui.print(print);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Command command = (Command) o;
        return taskType == command.taskType &&
            Objects.equals(index, command.index) &&
            Objects.equals(description, command.description) &&
            Objects.equals(date, command.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskType, index, description, date);
    }
}
