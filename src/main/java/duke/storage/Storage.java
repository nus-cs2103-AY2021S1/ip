package duke.storage;

import duke.exception.*;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    String filePath;

    /**
     * class constructor
     *
     * @param filePath the file path of the file to load data from and write data to
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * creates a new file if the it does not exist in the specified file path.
     * afterwards, the text in the file is decoded and converted to a list of tasks stored in an array list
     *
     * @return the array list of tasks
     * @throws DukeException if there are issues finding the file or decoding the file
     */
    public ArrayList<Task> load() throws DukeException {
        createDirectoryIfNotExist();
        createFileIfNotExist();
        return decodeTxtFile();
    }

    /**
     * encodes the tasks in the given task list to a more appropriate format for storage
     * updates the changes in the task list using the encoded versions of the task
     *
     * @param tasks the task list to reference when updating the file
     */
    public void save(ArrayList<Task> tasks) throws DukeFileLoadingErrorException {
        try {
            FileWriter fw = new FileWriter(this.filePath);

            for (Task task : tasks) {
                String encodedTask = task.encode();
                fw.write(encodedTask + System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            throw new DukeFileLoadingErrorException("there was an error loading the file");
        }
    }

    private void createDirectoryIfNotExist() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    private void createFileIfNotExist() throws DukeFileLoadingErrorException {
        File file = new File(this.filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeFileLoadingErrorException("there was an error loading the file");
            }
        }

        assert new File(this.filePath).exists() : "the file in the file path: " + this.filePath + " should exist";
    }

    private ArrayList<Task> decodeTxtFile() throws DukeException {
        File f = new File(this.filePath);
        Scanner s;
        ArrayList<Task> decodedTasks = new ArrayList<>();

        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException("the file could not be found");
        }

        String string;
        Task task = null;

        while (s.hasNext()) {
            string = s.nextLine();

            switch (string.charAt(0)) {
            case 'D':
                task = Deadline.decode(string);
                break;
            case 'E':
                task = Event.decode(string);
                break;
            case 'T':
                task = ToDo.decode(string);
                break;
            }
            assert task != null : "task should not be null";
            decodedTasks.add(task);
        }
        return decodedTasks;
    }
}
