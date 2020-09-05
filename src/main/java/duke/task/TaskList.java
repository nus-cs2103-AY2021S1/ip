package duke.task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.DukeException;
import duke.Storage;

/**
 * Encapsulates behavior and data for the TaskList.
 */
public class TaskList {

    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public String convertTaskListToString() {
        return convertTaskListToString(this.tasks);
    }

    /**
     * Converts a task list to a numbered list.
     *
     * @param tasks Task list to be converted to string.
     * @return Numbered string representation of task list.
     */
    private String convertTaskListToString(List<Task> tasks) {

        assert tasks != null;

        if (tasks.isEmpty()) {
            return "You have no tasks!";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1));
            sb.append(".");
            sb.append(tasks.get(i));
            sb.append('\n');
        }

        // remove last newline character
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    /**
     * Marks a task in the list as done.
     *
     * @param taskID ID of the task that needs to be marked as done.
     * @param storage Storage object that needs to be updated once the task is marked as done.
     * @return String to be displayed in user interface that confirms the action.
     * @throws DukeException If the task ID supplied is invalid.
     */
    public String markTaskAsDone(int taskID, Storage storage) throws DukeException {
        if (taskID < 1 || taskID > tasks.size()) {
            throw new DukeException("Task ID is invalid!");
        }

        Task task = tasks.get(taskID - 1);
        String oldTaskString = task.generateStorageString();
        task.competeTask();
        String newTaskString = task.generateStorageString();

        assert storage != null : "Storage object cannot be null";

        storage.editLineInStorage(oldTaskString, newTaskString);

        return String.format("Nice! I've marked this task as done.\n%s", task.toString());
    }

    /**
     * Deletes a task in the list.
     *
     * @param taskID ID of the task that needs to be deleted.
     * @param storage Storage object that needs to be updated once the task is deleted.
     * @return String to be displayed in user interface that confirms the action.
     * @throws DukeException If the task ID supplied is invalid.
     */
    public String deleteTask(int taskID, Storage storage) throws DukeException {
        if (taskID < 1 || taskID > tasks.size()) {
            throw new DukeException("Task ID is invalid!");
        }

        assert storage != null : "Storage object cannot be null";
        Task task = tasks.get(taskID - 1);
        storage.deleteLineFromStorage(task.generateStorageString());
        tasks.remove(taskID - 1);

        return String.format("Nice! I've deleted this task.\n%s\nNow you have %d tasks in the list",
                task.toString(), tasks.size());
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     * @return String to be displayed in the user interface that confirms the action.
     */
    public String addTaskToList(Task task) {
        assert task != null : "Task cannot be null";
        tasks.add(task);
        return String.format("Got it. I've added this task: \n%s\nNow you have %d tasks in the list",
                task.toString(), tasks.size());
    }

    /**
     * Converts a task list to a numbered list filtered by date.
     *
     * @param dateString String keyed in by the user as an argument.
     * @return String with numbered list representation of the filtered task list.
     * @throws DukeException if the DateTime format is invalid.
     */
    public String taskListToDateFilteredString(String dateString) throws DukeException {
        LocalDate date;
        assert dateString != null : "Date string cannot be null";
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new DukeException("DateTime format is invalid.");
        }

        List<Task> temp = tasks.stream()
                .filter(task -> task.isOnSameDay(date))
                .collect(Collectors.toList());

        return convertTaskListToString(temp);
    }

    /**
     * Converts a task list to a numbered list filtered by a keyword.
     *
     * @param keyword String keyed in by the user as an argument.
     * @return String with numbered list representation of the filtered task list.
     */
    public String taskListToKeywordFilteredString(String keyword) {
        assert keyword != null : "Keyword cannot be null";

        Predicate<? super Task> filterFunction;

        switch (keyword.toUpperCase()) {
        case "TYPE=TODO":
            filterFunction = task -> task.toString().contains("T");
            break;
        case "TYPE=EVENT":
            filterFunction = task -> task.toString().contains("E");
            break;
        case "TYPE=DEADLINE":
            filterFunction = task -> task.toString().contains("D");
            break;
        case "STATUS=DONE":
            filterFunction = task -> task.isDone();
            break;
        case "STATUS=UNDONE":
            filterFunction = task -> !task.isDone();
            break;
        default:
            filterFunction = task -> task.getTaskName().contains(keyword);
            break;
        }

        List<Task> temp = tasks.stream()
                .filter(filterFunction)
                .collect(Collectors.toList());

        return convertTaskListToString(temp);
    }

    /**
     * Populates the task list based on a text file found in a given filepath.
     *
     * @param filePath Filepath that the text file with the data is found.
     * @throws DukeException If the tasks in the text file are stored incorrectly or
     * if there are errors reading from the file.
     */
    public void loadDataFromStorage(Path filePath) throws DukeException {
        assert filePath != null : "Filepath object cannot be null";
        try {
            FileReader reader = new FileReader(filePath.toString());
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {

                String[] entry = line.split(" \\| ");

                if (entry.length != 3) {
                    throw new DukeException("One or more entries in storage have an invalid number of arguments");
                }

                String taskType = entry[0];
                String status = entry[1].toUpperCase();

                if (!status.equals("TRUE") && !status.equals("FALSE")) {
                    throw new DukeException("One or more task statuses are not stored correctly");
                }
                boolean taskIsDone = Boolean.parseBoolean(entry[1]);
                String taskArgument = entry[2];

                Task newTask;

                switch (taskType) {
                case "TODO":
                    newTask = ToDo.createNewToDo(taskArgument);
                    break;
                case "EVENT":
                    newTask = Event.createNewEvent(taskArgument);
                    break;
                case "DEADLINE":
                    newTask = Deadline.createNewDeadline(taskArgument);
                    break;
                default:
                    throw new DukeException("One or more task types are not stored correctly");
                }

                if (taskIsDone) {
                    newTask.competeTask();
                }

                tasks.add(newTask);
            }

            reader.close();
        } catch (IOException e) {
            throw new DukeException("Could not read from storage");
        }

    }
}
