package pandabot.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import pandabot.exceptions.PandaBotException;
import pandabot.exceptions.PandaBotLoadingTasksErrorException;
import pandabot.tasks.Deadline;
import pandabot.tasks.Event;
import pandabot.tasks.Task;
import pandabot.tasks.ToDo;


/**
 * Represents the storage class, which is responsible for
 * loading and saving tasks into the save file.
 */
public class Storage {
    private final Path saveFilePath;

    /**
     * Creates a Storage object, which is used to load and save tasks
     * into a save file.
     *
     * @param fileName the name of the save file
     */
    public Storage(String fileName) {
        String home = System.getProperty("user.home");

        Path dirPath = Paths.get(home, "PandaBot");
        boolean directoryExists = Files.exists(dirPath);

        // create a dir for save files to be saved in
        if (!directoryExists) {
            try {
                java.nio.file.Files.createDirectory(dirPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.saveFilePath = Paths.get(home, "PandaBot", fileName);
        boolean saveFileExists = Files.exists(saveFilePath);

        // create a save file where the data will be written to
        if (!saveFileExists) {
            try {
                Files.createFile(saveFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        assert Files.exists(saveFilePath) : "Save file should be successfully created.";
    }

    /**
     * Loads contents from the save file into an ArrayList of Task.
     *
     * @return a list of tasks as an ArrayList
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            BufferedReader reader = Files.newBufferedReader(saveFilePath);
            loadTasks(tasks, reader);
            reader.close();
        } catch (IOException e) {
            System.out.println("OOPS! :c There was an error in reading save file: " + e.getMessage() + "\n"
                                + "I'll get you a new save file!");
            tasks = new ArrayList<>();
        }

        return tasks;
    }

    private void loadTasks(ArrayList<Task> tasks, BufferedReader reader) throws IOException {
        String task;
        while ((task = reader.readLine()) != null) {
            // convert the line read into a task
            try {
                tasks.add(convertToTask(task));
            } catch (PandaBotException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Task convertToTask(String input) throws PandaBotException {
        String[] taskDetails = input.split(" \\| ");
        Task task;
        switch (taskDetails[0]) {
        case "T":
            task = new ToDo(taskDetails[2]);
            break;
        case "D":
            task = new Deadline(taskDetails[2], taskDetails[3]);
            break;
        case "E":
            task = new Event(taskDetails[2], taskDetails[3]);
            break;
        default:
            throw new PandaBotLoadingTasksErrorException(input);
        }

        String isDone = taskDetails[1];
        if (isDone.equals("1")) {
            task.markTaskDone();
        }

        return task;
    }

    /**
     * Writes each task in the list of tasks into the save file.
     *
     * @param tasks the ArrayList of tasks to be written and saved
     */
    public void write(ArrayList<Task> tasks) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(saveFilePath);
            for (Task t : tasks) {
                writer.write(t.saveAsText());
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("OOPS! :c There is an error in trying to write to the save file."
                                + "I couldn't save the entire list of tasks here.");
        }
    }

}
