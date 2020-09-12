package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Contains task list, and has operations like list and delete.
 */
class TaskList {

    /** List of tasks. */
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructs list of tasks.
     */
    TaskList() {
    }

    /**
     * Constructs list of tasks.
     *
     * @param taskStrings List of tasks in string format.
     * @throws DukeException  When date is in wrong format.
     */
    TaskList(ArrayList<ArrayList<String>> taskStrings) throws DukeException {

        for (ArrayList<String> taskString : taskStrings) {

            String description = taskString.get(2);
            int isDoneInt = Integer.parseInt(taskString.get(1));
            boolean isDone = isDoneInt > 0;

            String taskNameString = taskString.get(0);
            TaskNameInStorage taskNameInStorage = TaskNameInStorage.valueOf(taskNameString);
            switch (taskNameInStorage) {
            case D:
                String dateString = taskString.get(3);
                handleDeadline(description, dateString, isDone);
                break;
            case E:
                dateString = taskString.get(3);
                handleEvent(description, dateString, isDone);
                break;
            case T:
                handleTodo(description, isDone);
                break;
            default:
                throw new DukeException("Invalid character in storage file :-(");
            }
        }
    }

    /**
     * Gets list of tasks.
     *
     * @return List of tasks.
     */
    ArrayList<Task> getTasks() {
        return taskList;
    }

    /**
     * Handles adding of tasks.
     *
     * @param commandName Name of task command.
     * @param description Description of task.
     * @param date Date of task.
     * @return Output for ui for adding tasks.
     * @throws DukeException
     */
    String[] addTask(CommandName commandName, String description, String date) throws DukeException {
        Task task;

        switch (commandName) {
        case TODO:
            task = handleTodo(description);
            break;
        case DEADLINE:
            task = handleDeadline(description, date);
            break;
        case EVENT:
            task = handleEvent(description, date);
            break;
        default:
            assert false : "There should only be 3 types of tasks, should not reach here";
            task = new Task("");
        }

        return getTaskInfoAndTaskListInfoForUi(task);
    }

    private String[] getTaskInfoAndTaskListInfoForUi(Task task) {
        int len = taskList.size();

        String[] taskAndTaskListInfo = new String[2];
        taskAndTaskListInfo[0] = task.toString();
        taskAndTaskListInfo[1] = String.valueOf(len);
        return taskAndTaskListInfo;
    }

    private Task handleTodo(String todoTask) {
        Task todo = new Todo(todoTask);
        taskList.add(todo);
        return todo;
    }

    private void handleTodo(String todoTask, boolean isDone) {
        Task todo = new Todo(todoTask, isDone);
        taskList.add(todo);
    }

    private Task handleDeadline(String deadlineTask, String deadlineBy) throws DukeException {
        try {
            LocalDate deadlineByLocalDate = LocalDate.parse(deadlineBy);
            Task deadline = new Deadline(deadlineTask, deadlineByLocalDate);
            taskList.add(deadline);
            return deadline;
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Pass in a date in yyyy-mm-dd :-(");
        }
    }

    private void handleDeadline(String deadlineTask, String deadlineBy, boolean isDone) throws DukeException {
        try {
            LocalDate deadlineByLocalDate = LocalDate.parse(deadlineBy);
            Task deadline = new Deadline(deadlineTask, deadlineByLocalDate, isDone);
            taskList.add(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Pass in a date in yyyy-mm-dd :-(");
        }
    }

    private Task handleEvent(String eventTask, String eventAt) throws DukeException {
        try {
            LocalDate eventAtLocalDate = LocalDate.parse(eventAt);
            Task event = new Event(eventTask, eventAtLocalDate);
            taskList.add(event);
            return event;
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Pass in a date in yyyy-mm-dd :-(");
        }
    }

    private void handleEvent(String eventTask, String eventAt, boolean isDone) throws DukeException {
        try {
            LocalDate eventAtLocalDate = LocalDate.parse(eventAt);
            Task event = new Event(eventTask, eventAtLocalDate, isDone);
            taskList.add(event);
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Pass in a date in yyyy-mm-dd :-(");
        }
    }

    /**
     * Handles listing of tasks.
     *
     * @return Output for ui for listing tasks.
     */
    ArrayList<String> listTasks() {
        ArrayList<String> taskListString = new ArrayList<>();
        for (Task task : taskList) {
            taskListString.add(task.toString());
        }
        return taskListString;
    }

    /**
     * Handles doing of tasks.
     *
     * @param index Index of task to do.
     * @return Output for ui for doing tasks.
     */
    String doTask(int index) {
        Task task = taskList.get(index - 1);
        task.setDone(true);
        return task.toString();
    }

    /**
     * Handles deleting of tasks.
     *
     * @param index Index of task to delete.
     * @return Output for ui for deleting tasks.
     */
    String[] deleteTask(int index) {
        Task task = taskList.get(index - 1);
        taskList.remove(index - 1);

        return getTaskInfoAndTaskListInfoForUi(task);
    }

    /**
     * Handles finding of tasks.
     *
     * @param search String to search for.
     * @return Output for ui for finding tasks.
     */
    ArrayList<String> findTasks(String search) {
        ArrayList<String> foundTaskListString = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(search)) {
                foundTaskListString.add(task.toString());
            }
        }
        return foundTaskListString;
    }

    /**
     * Handles sorting of tasks.
     *
     * @param sortOrderOptionEnum Option to order ascending or descending.
     * @return Output for ui for sorting tasks.
     */
    ArrayList<String> sortTasks(SortOrderOption sortOrderOptionEnum) {
        sortTasksInPlace(sortOrderOptionEnum);
        return listTasks();
    }

    private void sortTasksInPlace(SortOrderOption sortOrderOptionEnum) {
        switch (sortOrderOptionEnum) {
        case ASC:
            taskList.sort(Comparator.comparing(Task::getDescription));
            break;
        case DESC:
            taskList.sort(Collections.reverseOrder(Comparator.comparing(Task::getDescription)));
            break;
        default:
            assert false : "There should only be 2 sort options, should not reach here.";
        }
    }

}
