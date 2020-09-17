package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.DukeFileNotFoundException;
import duke.exceptions.DukeLoadingErrorException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskType;
import duke.tasks.ToDo;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private Path path;

    /**
     * Class constructor.
     *
     * @param filePath A string representing the file path.
     * @throws DukeLoadingErrorException If a loading error occurs.
     */
    public Storage(String filePath) throws DukeLoadingErrorException {
        initialiseStorage(filePath);
    }

    /**
     * Saves the task list in a dedicated destination file.
     *
     * @param tasks The task list to be saved.
     * @throws DukeLoadingErrorException If a loading error occurs.
     */
    public void save(List<Task> tasks) throws DukeLoadingErrorException {
        try {
            FileWriter fw = new FileWriter(String.valueOf(path));
            for (Task task : tasks) {
                String toSave = "";

                String description = task.getDescription();
                int isDone = task.isDone() ? 1 : 0;
                TaskType type = task.getTaskType();

                switch (type) {
                case TODO:
                    toSave += String.format("%s\t%d\t%s",
                            type, isDone, description);
                    break;
                case EVENT:
                case DEADLINE:
                    toSave += String.format("%s\t%d\t%s\t%s",
                            type, isDone, description, task.getTimeString());
                    break;
                default:
                }

                fw.write(String.format("%s\n", toSave));
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeLoadingErrorException();
        }
    }

    /**
     * Loads the task list saved in the file.
     *
     * @return The task list that has been loaded.
     * @throws DukeFileNotFoundException If the file does not exist.
     */
    public List<Task> load() throws DukeFileNotFoundException {
        Scanner sc;

        try {
            sc = new Scanner(path.toFile());
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException();
        }

        List<Task> tasks = new ArrayList<>();

        Task task = null;
        boolean isDone;
        String description;
        String time = null;

        while (sc.hasNextLine()) {
            String[] parsed = sc.nextLine().split("\t");

            description = parsed[2];
            isDone = Integer.parseInt(parsed[1]) == 1;
            if (parsed.length == 4) {
                time = parsed[3];
            }

            switch (parsed[0]) {
            case "T":
                task = new ToDo(description, isDone);
                break;
            case "E":
                task = new Event(description, isDone, time);
                break;
            case "D":
                task = new Deadline(description, isDone, time);
                break;
            default:
            }

            assert task != null : "Task cannot be null!";
            tasks.add(task);
        }

        return tasks;
    }

    private void initialiseStorage(String filePath) throws DukeLoadingErrorException {
        try {
            String home = System.getProperty("user.home");
            path = Paths.get(home, filePath);

            // If path doesn't exist, create one
            if (Files.notExists(path)) {
                File newDir = new File(String.valueOf(path));
                String[] toCreate = filePath.split("/");

                // Create folders for each directory specified in the filePath
                for (int i = 0; i < toCreate.length - 1; i++) {
                    String dir = toCreate[i];
                    Path miniPath = Paths.get(home, dir);
                    File miniDir = new File(String.valueOf(miniPath));
                    miniDir.mkdir();
                }

                newDir.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeLoadingErrorException();
        }
    }

}
