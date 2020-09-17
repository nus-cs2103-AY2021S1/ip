package duke.storage;

import static duke.util.Keyword.KEYWORD_DEADLINE;
import static duke.util.Keyword.KEYWORD_EVENT;
import static duke.util.Keyword.KEYWORD_STRING_ZERO;
import static duke.util.Keyword.KEYWORD_TODO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.tasklist.TaskList;

/**
 * Class that simulates the storing and retrieving of information into the hard-disk
 */
public class Storage {

    /**
     * Check if a particular task is completed.
     *
     * @param completionStatus A string encoding whether a task is completed.
     * @return Returns true if the task is completed, false otherwise.
     */
    private boolean isTaskDone(String completionStatus) {
        return !completionStatus.equals(KEYWORD_STRING_ZERO);
    }

    /**
     * Check if a particular task has reminder on.
     *
     * @param reminderStatus A string encoding whether a task has reminder on.
     * @return Returns true if the task has reminder on, false otherwise.
     */
    private boolean isReminderOn(String reminderStatus) {
        return !reminderStatus.equals(KEYWORD_STRING_ZERO);
    }

    /**
     * Create a file path.
     *
     * @param path The directory that to be created.
     * @throws IOException
     */
    private void createFilePath(Path path) throws IOException {
        assert path != null;
        Files.createDirectories(path);
    }

    /**
     * Create a CSV file for the user.
     *
     * @param file The CSV file to be created.
     * @throws IOException
     */
    private void createCsv(File file) throws IOException {
        assert file != null;
        file.createNewFile();
    }

    /**
     * Record down the list of tasks that the user have during this session.
     *
     * @param file The CSV file to record down the information.
     * @param tasks Object contains the task list.
     * @throws IOException
     */
    private void savingFileInfo(File file, TaskList tasks) throws IOException {
        assert file != null;
        assert tasks != null;
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < tasks.size(); i++) {
            bufferedWriter.write(tasks.get(i).formatStyling());
        }
        bufferedWriter.close();
    }

    /**
     * Record the data back into the user's file when user exits the program/
     *
     * @param tasks Object contains the task list.
     */
    public void record(TaskList tasks) {
        assert tasks != null;
        String dir = System.getProperty("user.dir");
        Path path = Paths.get(dir, "duke.Duke");
        try {
            if (!Files.exists(path)) {
                createFilePath(path);
            }
            Path filePath = Paths.get(dir, "duke.Duke", "todoList.csv");
            File file = filePath.toFile();
            if (!file.exists()) {
                createCsv(file);
            }
            savingFileInfo(file, tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve the user's data and load into the system.
     *
     * @param tasks Object contains the task list.
     */
    public void retrieve(TaskList tasks) {
        String dir = System.getProperty("user.dir");
        Path path = Paths.get(dir, "duke.Duke", "todoList.csv");
        if (path.toFile().exists()) {
            getData(path, tasks);
        }
    }

    /**
     * Read the data that is stored inside the file
     *
     * @param path File path.
     * @param tasks Object contains the task list.
     */
    private void getData(Path path, TaskList tasks) {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(path);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] info = line.split(",", 5);
                switch (info[0]) {
                case KEYWORD_TODO:
                    tasks.add(new ToDo(info[1], isTaskDone(info[2]), isReminderOn(info[3])));
                    break;
                case KEYWORD_EVENT:
                    tasks.add(new Event(info[2], info[1], isTaskDone(info[3]), isReminderOn(info[4])));
                    break;
                case KEYWORD_DEADLINE:
                    tasks.add(new Deadline(info[2], info[1], isTaskDone(info[3]), isReminderOn(info[4])));
                    break;
                default:
                    throw new UnknownCommandException();
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException | UnknownCommandException e) {
            e.printStackTrace();
        }
    }
}
