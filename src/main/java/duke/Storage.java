package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.DukeStorageException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

/**
 * Represents the file used to store task data.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new instance of a Storage object.
     * @param filePath Location of file used to store task data.
     */
    Storage(String filePath) {
        this.filePath = filePath;
        try {
            Path directoryPath = Paths.get("data/");

            if (!Files.exists(directoryPath)) {
                Files.createDirectory(directoryPath);
            }

            Path file = Paths.get(this.filePath);

            if (!Files.exists(file)) {
                Files.createFile(file);
            }

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    String convertTaskToText (Task task) {
        if (task instanceof TodoTask) {
            return "T" + " | " + (task.getCompletionStatus() ? "1" : "0") + " | " + task.getDescription()
                   + " | " + task.getPriority();
        } else if (task instanceof DeadlineTask) {
            return "D" + " | " + (task.getCompletionStatus() ? "1" : "0") + " | " + task.getDescription() + " | "
                    + ((DeadlineTask) task).getDeadline().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
                    + " | " + task.getPriority();
        } else {
            return "E" + " | " + (task.getCompletionStatus() ? "1" : "0") + " | " + task.getDescription() + " | "
                    + ((EventTask) task).getTiming().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
                    + " | " + task.getPriority();
        }
    }

    /**
     * Saves the tasks data to storage file.
     * @param tasks List of tasks to be saved.
     * @throws DukeStorageException If there is error saving tasks to file.
     */
    public void writeToFile(TaskList tasks) throws DukeException {
        FileWriter fw;
        try {
            fw = new FileWriter(filePath);
            List<String> fileContent = new ArrayList<>();
            for (Task task : tasks.getTasks()) {
                fileContent.add(convertTaskToText(task));
            }
            Files.write(Paths.get(filePath), fileContent);
            fw.close();
        } catch (IOException exception) {
            throw new DukeStorageException("Error saving task to file");
        }
    }

    /**
     * Loads the local tasks data in storage file, then returns it.
     * @return Returns local tasks data in storage file as TaskList.
     * @throws DukeStorageException If storage file is not found.
     */
    List<Task> load() throws DukeException {
        File localTasks = new File(this.filePath);
        try {
            List<Task> tasks = new ArrayList<>();
            // Create Scanner using file as source
            Scanner sc = new Scanner(localTasks);
            while (sc.hasNext()) {
                String[] details = sc.nextLine().split(" \\| ");

                switch (details[0]) {
                case "T":
                    TodoTask todoTask = new TodoTask(details[2]);
                    if (details[1].equals("1")) {
                        todoTask.markAsDone();
                    }
                    if (details.length == 4) {
                        todoTask.addPriority(Priority.valueOf(details[3]));
                    }
                    tasks.add(todoTask);
                    break;
                case "D":
                    DeadlineTask deadlineTask = new DeadlineTask(details[2], details[3]);
                    if (details[1].equals("1")) {
                        deadlineTask.markAsDone();
                    }
                    if (details.length == 5) {
                        deadlineTask.addPriority(Priority.valueOf(details[4]));
                    }
                    tasks.add(deadlineTask);
                    break;
                case "E":
                    EventTask eventTask = new EventTask(details[2], details[3]);
                    if (details[1].equals("1")) {
                        eventTask.markAsDone();
                    }
                    if (details.length == 5) {
                        eventTask.addPriority(Priority.valueOf(details[4]));
                    }
                    tasks.add(eventTask);
                    break;
                default:
                    throw new DukeException("Invalid Task Type");
                }
            }
            sc.close();
            return tasks;
        } catch (FileNotFoundException exception) {
            throw new DukeStorageException("File not found");
        }
    }
}
