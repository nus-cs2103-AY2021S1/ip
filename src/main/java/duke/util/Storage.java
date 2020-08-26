package duke.util;

import duke.command.CommandExecutor;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

// Class that handles the loading and saving of the save file.
public class Storage {
    private final Path FILE_PATH;

    private boolean isActive = true;

    public Storage(Path filePath) throws IOException {
        this.FILE_PATH = filePath;
        try {
            createIfNotExist();
        } catch (IOException e) {
            throw new IOException("An error has occurred when trying to create the save file!");
        }
    }

    private void createIfNotExist() throws IOException {
        int len = FILE_PATH.getNameCount();
        Path directoriesToCreate = FILE_PATH.subpath(0, len - 1);
        Files.createDirectories(directoriesToCreate);
        if (!java.nio.file.Files.exists(FILE_PATH)) {
            new File(FILE_PATH.toString()).createNewFile();
        }
    }

    /**
     * Iterates through the taskList and writes the task information into the save file.
     *
     * @param taskList TaskList list that contains tasks added by the user
     */
    public void updateSaveFile(TaskList taskList) {
        if (!isActive) {
            return;
        }

        try {
            FileWriter myWriter = new FileWriter(FILE_PATH.toString());
            taskList.forEach((Task task) -> {
                try {
                    myWriter.write(task.toSaveString() + "\n");
                } catch (IOException e) {
                    System.out.println("An error has occurred when updating the save file.");
                }
            });
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error has occurred when updating the save file.");
        }
    }

    /**
     * Reads the the lines in the save file and adds the saved tasks into the taskList.
     *
     * @param taskList TaskList list that contains tasks added by the user
     * @param exe CommandExecutor object that handles the parsing of the task info
     */
    public void loadSaveFile(TaskList taskList, CommandExecutor exe) {
        // Prevent saving of duke.task.TaskList while loading it
        isActive = false;

        try {
            BufferedReader in = new BufferedReader(new FileReader(FILE_PATH.toString()));
            in.lines().forEach((String s) -> {
                try {
                    exe.execute(s.substring(1), taskList, this);
                    if (s.charAt(0) == '1') {
                        taskList.get(taskList.size() - 1).markAsDone();
                    }
                } catch (DukeException e) {
                    String msg = "A line in your save file seems to be formatted incorrectly!";
                    System.out.println(msg);
                    e.printStackTrace();
                }
            });
            in.close();
        } catch(IOException e) {
            System.out.println("An error has occurred when reading the save file.");
        }

        isActive = true;
    }
}
