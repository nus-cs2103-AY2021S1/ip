package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;

import java.nio.file.Path;


public class Storage {
    private final Path pathToStorage;
    private List<String> allTasks;

    public Storage(Path pathToStorage) {
        this.pathToStorage = pathToStorage;
        try {
            // Create directory if needed
            Path parentPath = pathToStorage.getParent();
            Files.createDirectories(parentPath);

            if (!Files.exists(pathToStorage)) {
                Files.createFile(pathToStorage);
            }
            // Get stored info if any
            this.allTasks= Files.readAllLines(pathToStorage);
        } catch (IOException e) {
            System.out.println("Can't read file " + e.getMessage());
        }
    }

    /**
     * Converts string from task storage file to a task list for duke.Duke to use.
     * @return a list of Tasks readable by duke.Duke.
     */
    public ArrayList<Task> getAllTasks() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        // Convert string to Tasks
        for (String taskString : this.allTasks) {
            char taskType = taskString.charAt(0);
            String taskDetails = taskString.substring(taskString.lastIndexOf("|") + 2);
            boolean isDone = false;

            if (taskString.charAt(4) == '1') {
                isDone = true;
            }

            switch (taskType) {
                case 'T':
                    taskList.add(new ToDo(taskDetails, isDone));
                    break;
                case 'E':
                    String eventDescription = taskString.substring(8, taskString.lastIndexOf("|") - 1);
                    taskList.add(new Event(eventDescription, isDone, formatTaskDateTime(taskDetails)));
                    break;
                case 'D':
                    String deadlineDescription = taskString.substring(8, taskString.lastIndexOf("|") - 1);
                    taskList.add(new Deadline(deadlineDescription, isDone, formatTaskDateTime(taskDetails)));
                    break;
                default:
                    System.out.println("Unable to determine type of task");
                    break;
            }
        }

        return taskList;
    }

    private String formatTaskDateTime(String dateTime) {
        String[] date_time = dateTime.split(",");
        String date = date_time[0]; // MMM DD YYYY
        String time = date_time[1]; // HH:MM:SS

        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate newDate = LocalDate.parse(date, format);
        int monthInt = newDate.getMonthValue();
        String year = String.valueOf(newDate.getYear());
        int dayInt = newDate.getDayOfMonth();

        String month = monthInt >= 10
                ? String.valueOf(monthInt)
                : "0".concat(String.valueOf(monthInt));

        String day = dayInt >= 10
                ? String.valueOf(dayInt)
                : "0".concat(String.valueOf(dayInt));

        String hourAndMinutes = time.substring(0, time.lastIndexOf(":"));

        // Return YYYY/MM/DD HH:MM
        String ans = year + "/" + month + "/" + day + hourAndMinutes;
        return ans;
    }

    public void writeToStorage() {
        try {
            File storageFile = new File(String.valueOf(pathToStorage));
            FileWriter fw = new FileWriter(storageFile);
            for (String task : this.allTasks) {
                fw.write(task + '\n');
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTask(Task task) {
        this.allTasks.add(task.toString());
        writeToStorage();
    }

    public void updateTask(Task task, int taskIndex) {
        this.allTasks.set(taskIndex, task.toString());
        writeToStorage();
    }

    public void deleteTask(int taskIndex) {
        this.allTasks.remove(taskIndex);
        writeToStorage();
    }
}
