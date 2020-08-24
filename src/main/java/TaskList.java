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

    private List<Task> tasks;
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public String convertTaskListToString() {
        return convertTaskListToString(this.tasks);
    }

    private String convertTaskListToString(List<Task> tasks) {

        if (tasks.isEmpty()) {
            return "You have no tasks!";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i+1));
            sb.append(".");
            sb.append(tasks.get(i));
            sb.append('\n');
        }

        // remove last newline character
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

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

    public String addTaskToList(Task task) {
        tasks.add(task);
        return String.format("Got it. I've added this task: \n%s\nNow you have %d tasks in the list",
                task.toString(), tasks.size());
    }

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
