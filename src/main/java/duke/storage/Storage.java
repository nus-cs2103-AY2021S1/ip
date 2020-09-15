package duke.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import duke.exceptions.PathNoFoundException;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.PeriodTask;
import duke.tasks.Task;
import duke.tasks.Todo;


public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Adds a to-do, deadline or event to tasklist.
     * @param line User input
     * @param tasks ArrayList of Tasks
     */
    private void addTask(String line, ArrayList<Task> tasks) {
        String[] info = line.split(" \\| ");
        String type = info[0];
        int complete = Integer.parseInt(info[1]);
        String title = info[2];
        switch(type) {
        case "T":
            Todo newTodo = new Todo(title, complete);
            tasks.add(newTodo);
            break;
        case "D":
            String deadline = info[3];
            LocalDate deadlineInLocalDate = LocalDate.parse(deadline);
            Deadline newDeadline = new Deadline(title, complete, deadlineInLocalDate);
            tasks.add(newDeadline);
            break;
        case "E":
            String time = info[3];
            LocalDate timeInLocalDate = LocalDate.parse(time);
            Event newEvent = new Event(title, complete, timeInLocalDate);
            tasks.add(newEvent);
            break;
        case "P":
            String start = info[3];
            String end = info[4];
            LocalDate startInLocalDate = LocalDate.parse(start);
            LocalDate endLocalDate = LocalDate.parse(end);
            PeriodTask periodTask = new PeriodTask(title, complete, startInLocalDate, endLocalDate);
            tasks.add(periodTask);
            break;
        default:
            throw new AssertionError(type);
        }
    }

    /**
     * Reads .txt file.
     *
     * @return ArrayList of tasks.
     * @throws PathNoFoundException If there is no data file found.
     */
    public ArrayList<Task> readFile() throws PathNoFoundException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            String cwd = System.getProperty("user.dir");
            if (Files.exists(Paths.get(cwd + filePath))) {
                List<String> allLines = Files.readAllLines(Paths.get(cwd + filePath));
                for (String line : allLines) {
                    addTask(line, tasks);
                }
            }
            return tasks;
        } catch (IOException ex) {
            throw new PathNoFoundException("No database found... A new database will be initialized!");
        }
    }

    /**
     * Saves the modified TaskArray in the hard disc.
     * @param tasklist TaskList object.
     */
    public void saveFile(TaskList tasklist) {
        try {
            String cwd = System.getProperty("user.dir");
            FileWriter fw = new FileWriter(cwd + filePath);
            for (Task task : tasklist.getTasks()) {
                String data = task.getData();
                fw.write(data + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }


}
