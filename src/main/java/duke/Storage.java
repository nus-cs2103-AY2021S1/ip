package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Deals with loading and saving tasks from file.
 */
class Storage {

    /** Variable to store file path for I/O. */
    private String filePath;

    /**
     * Constructor used to create file storage.
     *
     * @param filePath Filepath of input and output file.
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads existing file.
     *
     * @return A list of list of strings, outer list for many lines in file;
     * inner list is one line in file, split by delimiter
     * @throws DukeException When IO error occurs.
     */
    ArrayList<ArrayList<String>> load() throws DukeException {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();
        if (file.exists()) {
            return readTasksFromFile();
        } else {
            return createFile(file);
        }
    }

    private ArrayList<ArrayList<String>> createFile(File file) throws DukeException {
        try {
            file.createNewFile();
            return new ArrayList<>();
        } catch (IOException e) {
            throw new DukeException("A file error has occurred!");
        }
    }

    private ArrayList<ArrayList<String>> readTasksFromFile() throws DukeException {
        // File structure
        // D | isDoneInt | description | by
        // E | isDoneInt | description | at
        // T | isDoneInt | description

        File file = new File(this.filePath);
        ArrayList<ArrayList<String>> formattedOutput = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] lineParts = line.split(" \\| ");
                ArrayList<String> linePartArray = new ArrayList<>(Arrays.asList(lineParts));
                formattedOutput.add(linePartArray);
            }
            return formattedOutput;
        } catch (java.io.FileNotFoundException e) {
            throw new DukeException("A file error has occurred!");
        }
    }

    void save(ArrayList<Task> tasks) throws DukeException {
        String output = tasksToStorageStringOutput(tasks);
        appendToFile(output);
    }

    private String tasksToStorageStringOutput(ArrayList<Task> tasks) {
        StringBuilder output = new StringBuilder();
        for (Task task : tasks) {
            output.append(task.toStringForStorage()).append(System.lineSeparator());
        }
        return output.toString();
    }

    private void appendToFile(String textToAppend) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath); // create a FileWriter in append mode
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("A file error has occurred!");
        }
    }
}


