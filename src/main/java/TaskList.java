import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Enumeration indicating the type of change to be acted on the TaskList.
 */
enum ListChange {
    ADD, DONE, DELETE
}

public class TaskList {
    /** The list of tasks present. */
    protected List<Task> tasks;

    /**
     * Constructs a new TaskList object.
     *
     * @param taskList List of Tasks.
     */
    public TaskList(List<Task> taskList) {
        tasks = taskList;
    }

    /**
     * Gets the list of tasks.
     *
     * @return List of Tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns substring of tasks depending on their amount.
     *
     * @return String of number of tasks.
     */
    public String getNumTasksStr() {
        int numTasks = tasks.size();
        return numTasks + (numTasks == 1 ? " task" : " tasks");
    }

    /**
     * Returns the correct message to signify a change in tasks.
     *
     * @param change ListChange enum depending on command.
     * @return Message string.
     */
    public String listChangePrint(ListChange change) {
        String keyphrase;
        switch (change) {
        case ADD:
            keyphrase = "added the new task";
            break;
        case DONE:
            keyphrase = "marked done the task(s)";
            break;
        case DELETE:
            keyphrase = "deleted the task(s)";
            break;
        default:
            keyphrase = "some issues but I'd die";
        }
        String firstLine = "Nice! I've " + keyphrase + " for you!";
        String secondLine = change == ListChange.DONE
                ? ""
                : "Now you have " + this.getNumTasksStr() + " in the list.";
        return String.join("\n", firstLine, secondLine);
    }

    /**
     * Compiles an ordered list (in String) of tasks.
     *
     * @return String of task list.
     */
    public String printList() {
        if (tasks.isEmpty()) {
            return new DukeException("emptyList").print();
        }
        return IntStream.range(0, tasks.size())
                        .mapToObj(i -> i + 1 + "." + tasks.get(i).toString())
                        .collect(Collectors.joining("\n"));
    }

    /**
     * Marks task(s) as done.
     *
     * @param command User command.
     * @return Duke response indicating successful action (or not).
     */
    public String markDone(String command) {
        try {
            Integer[] taskIds = command.contains("all")
                    ? IntStream.range(0, tasks.size())
                               .boxed()
                               .toArray(Integer[]::new)
                    : Parser.getTaskIds(command);
            for (int taskId : taskIds) {
                tasks.get(taskId).markAsDone();
            }
            return listChangePrint(ListChange.DONE);
        } catch (Exception e) {
            return new DukeException("invalidDone").print();
        }
    }

    /**
     * Creates new ToDo task.
     *
     * @param command User command.
     * @return Duke response indicating successful action (or not).
     */
    public String createToDo(String command) {
        try {
            String detail = Parser.getDetail(command);
            if (detail.isBlank()) {
                return new DukeException("invalidTodo").print();
            } else {
                Task newTask = new ToDo(detail);
                tasks.add(newTask);
                return listChangePrint(ListChange.ADD);
            }
        } catch (Exception e) {
            return new DukeException("invalidTodo").print();
        }
    }

    /**
     * Creates new Deadline task.
     *
     * @param command User command.
     * @return Duke response indicating successful action (or not).
     */
    public String createDeadline(String command) {
        try {
            String desc = Parser.getDeadlineDesc(command);
            String by = Parser.getBy(command);
            Task newTask = new Deadline(desc, by);
            tasks.add(newTask);
            return listChangePrint(ListChange.ADD);
        } catch (DukeException e) {
            return e.print();
        } catch (Exception e) {
            return new DukeException("invalidDeadlineTask").print();
        }
    }

    /**
     * Creates new Event task.
     *
     * @param command User command.
     * @return Duke response indicating successful action (or not).
     */
    public String createEvent(String command) {
        try {
            String desc = Parser.getEventDesc(command);
            String at = Parser.getAt(command);
            Task newTask = new Event(desc, at);
            tasks.add(newTask);
            return listChangePrint(ListChange.ADD);
        } catch (DukeException e) {
            return e.print();
        } catch (Exception e) {
            return new DukeException("invalidEvent").print();
        }
    }

    /**
     * Deletes task(s).
     *
     * @param command User command.
     * @return Duke response indicating successful action (or not).
     */
    public String delete(String command) {
        try {
            Integer[] taskIds = command.contains("all")
                    ? Arrays.stream(new int[tasks.size()])
                            .boxed()
                            .toArray(Integer[]::new)
                    : Parser.getTaskIds(command);
            for (int taskId : taskIds) {
                tasks.remove(taskId);
            }
            return listChangePrint(ListChange.DELETE);
        } catch (Exception e) {
            return new DukeException("invalidDelete").print();
        }
    }

    /**
     * Finds task(s) according to keyword.
     *
     * @param command User command.
     * @return String of task(s) matching the keyword.
     */
    public String find(String command) {
        try {
            String keyword = Parser.getDetail(command);
            if (keyword.isBlank()) {
                return new DukeException("invalidFind").print();
            } else {
                List<Task> tasksFound = new ArrayList<>();
                for (Task task : tasks) {
                    if (task.toString().contains(keyword)) {
                        tasksFound.add(task);
                    }
                }
                return tasksFound.isEmpty()
                    ? new DukeException("noMatchingTasks").print()
                    : new TaskList(tasksFound).printList();
            }
        } catch (Exception e) {
            return new DukeException("invalidFind").print();
        }
    }

    /**
     * Returns the default invalid command error.
     *
     * @return Duke response for default error.
     */
    public String getDefaultError() {
        return new DukeException("invalidCommand").print();
    }
}
