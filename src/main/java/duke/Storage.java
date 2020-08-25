package duke;

import duke.exception.DukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

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
            Path file = Paths.get(this.filePath);

            if (!Files.exists(file)) {
                // Check if directory exists
                Path directoryPath = Paths.get("data/");
                if (!Files.exists(directoryPath)) {
                    Files.createDirectory(directoryPath);
                }
                Files.createFile(file);
            }

        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    String convertTaskToText (Task task) {
        if (task instanceof TodoTask) {
            return "T" + " | " + (task.isDone ? "1" : "0") + " | " + task.description;
        } else if (task instanceof DeadlineTask) {
            return "D" + " | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " +
                    ((DeadlineTask) task).deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        } else {
            return "E" + " | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " +
                    ((EventTask) task).timing.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        }
    }

    /**
     * Saves the tasks data to storage file.
     * @param tasks List of tasks to be saved.
     * @throws DukeException If there is error saving tasks to file.
     */
    public void writeToFile(TaskList tasks) throws DukeException {
        FileWriter fw;
        try {
            fw = new FileWriter(filePath);
            List<String> fileContent = new ArrayList<>();
            for (Task task : tasks.getTasks()) {
                fileContent.add(convertTaskToText(task));
            }
            Files.write(Paths.get(filePath),fileContent);
            fw.close();
        } catch (IOException e) {
           throw new DukeException("Write To File error");
        }
    }

    /**
     * Loads the local tasks data in storage file, then returns it.
     * @return Returns local tasks data in storage file as TaskList.
     * @throws DukeException If storage file is not found.
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
                    case "T": {
                        TodoTask todoTask = new TodoTask(details[2]);
                        if (details[1].equals("1")) {
                            todoTask.markAsDone();
                        }
                        tasks.add(todoTask);
                        break;
                    }
                    case "D": {
                        DeadlineTask deadlineTask = new DeadlineTask(details[2], details[3]);
                        if (details[1].equals("1")) {
                            deadlineTask.markAsDone();
                        }
                        tasks.add(deadlineTask);
                        break;
                    }
                    case "E": {
                        EventTask eventTask = new EventTask(details[2], details[3]);
                        if (details[1].equals("1")) {
                            eventTask.markAsDone();
                        }
                        tasks.add(eventTask);
                        break;
                    }
                }
            }
            sc.close();
            return tasks;
        } catch (FileNotFoundException exception) {
            throw new DukeException("File not found");
        }
    }
}
