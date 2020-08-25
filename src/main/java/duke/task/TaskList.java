package duke.task;

import duke.DukeException;
import duke.Storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * @throws IOException If there are issues with updating the changes to storage.
     */
    public String markTaskAsDone(int taskID, Storage storage) throws DukeException, IOException {
        if (taskID < 1 || taskID > tasks.size()) {
            throw new DukeException("Task ID is invalid!");
        }

        Task task = tasks.get(taskID - 1);
        String oldTaskString = task.generateStorageString();
        task.competeTask();
        String newTaskString = task.generateStorageString();
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
     * @throws IOException If there are issues with updating the changes to storage.
     */
    public String deleteTask(int taskID, Storage storage) throws DukeException, IOException {
        if (taskID < 1 || taskID > tasks.size()) {
            throw new DukeException("Task ID is invalid!");
        }

        Task task = tasks.get(taskID - 1);
        storage.deleteLineFromStorage(task.generateStorageString());
        tasks.remove(taskID - 1);

        return String.format("Nice! I've marked this task as done.\n%s\nNow you have %d tasks in the list",
                task.toString(), tasks.size());
    }

    /**
     * Adds a task to the task list.
     * 
     * @param task Task to be added.
     * @return String to be displayed in the user interface that confirms the action.
     */
    public String addTaskToList(Task task) {
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
     * Populates the task list based on a text file found in a given filepath.
     * 
     * @param filePath Filepath that the text file with the data is found.
     * @throws DukeException If the tasks in the text file are stored incorrectly.
     * @throws IOException If there are issues with reading from the file.
     */
    public void loadDataFromStorage(Path filePath) throws DukeException, IOException {
        
        FileReader reader = new FileReader(filePath.toString());
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;

        while ((line = bufferedReader.readLine()) != null) {

            String[] entry = line.split(" \\| ");

            if (entry.length == 3) {
                String taskType = entry[0];
                if (!entry[1].toUpperCase().equals("TRUE") && !entry[1].toUpperCase().equals("FALSE")) {
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

            } else {
                throw new DukeException("One or more entries have an invalid number of arguments");
            }
        }

        reader.close();

    }
}
