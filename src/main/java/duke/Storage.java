package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A file-reader and writer responsible for retrieving a list of tasks from an existing
 * text file in the specified filepath, or otherwise creates that file. It can also edit
 * the text file by adding, deleting or replacing a line.
 */
public class Storage {

    /**
     * Absolute or relative filepath of the source of the file to be retrieved or the
     * destination of the file to be written.
     */
    private String filepath;

    /**
     * Constructs a new Storage that reads/writes the file in the specified filepath.
     * @param filepath The source/destination filepath
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads a list of tasks from the source file, if it exists. Otherwise, creates a file
     * at the filepath and returns an empty list.
     * @return A list of tasks parsed from the source file, if any; otherwise, an empty list
     * @throws DukeException If an I/O error occurs when trying to create the file and parent
     * folders, if any
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            String parentFilepath = Path.of(filepath).getParent().toString();
            File parentFolder = new File(parentFilepath);
            if (!parentFolder.exists()) {
                parentFolder.mkdir();
            }
            File taskFile = new File(filepath);
            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }
            Scanner scanner = new Scanner(taskFile);
            ArrayList<Task> tasks = new ArrayList<>();
            while (scanner.hasNext()) {
                tasks.add(Parser.parseTaskFromFile(scanner.nextLine()));
            }
            return tasks;
        } catch (IOException e) {
            throw DukeException.FILE_LOADING_EXCEPTION;
        }
    }

    /**
     * Appends a line of text to the destination file specified by the filepath.
     * <p>
     * If an I/O error occurs, shows an error message.
     * @param line A line of text to be appended to the destination file
     */
    public void addLine(String line) {
        try {
            FileWriter fileWriter = new FileWriter(filepath, true);
            fileWriter.write(line + System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Encountered an unexpected error with the file :(");
        }
    }

    /**
     * Deletes a line of text at the specified index from the destination file specified by
     * the filepath.
     * <p>
     * If the index falls outside the range of the number of lines in the file, no lines are
     * deleted, and no error is raised.
     * <p>
     * If an I/O error occurs, shows an error message.
     * @param index The line number to be deleted
     */
    public void deleteLine(int index) {
        try {
            String tempFilepath = Path.of(filepath).getParent().toString() + "/temp.txt";
            Files.copy(Path.of(filepath), Path.of((tempFilepath)));
            FileWriter fileWriter = new FileWriter(filepath);
            File copy = new File(tempFilepath);
            Scanner scanner = new Scanner(copy);
            int lineNumber = 1;
            while (scanner.hasNext()) {
                if (lineNumber != index) {
                    fileWriter.write(scanner.nextLine() + System.lineSeparator());
                } else {
                    scanner.nextLine();
                }
                lineNumber++;
            }
            fileWriter.close();
            scanner.close();
            Files.delete(Path.of(tempFilepath));
        } catch (IOException e) {
            System.out.println("Encountered an unexpected error with the file :(");
        }
    }

    /**
     * Replaces a line of text at the specified index with a specified line of text in the
     * destination file specified by the filepath.
     * <p>
     * If an I/O error occurs, shows an error message.
     * @param index The line number to be replaced
     * @param line The line of text to replace the previous one
     */
    public void replaceLine(int index, String line) {
        try {
            String tempFilepath = Path.of(filepath).getParent().toString() + "/temp.txt";
            Files.copy(Path.of(filepath), Path.of((tempFilepath)));
            FileWriter fileWriter = new FileWriter(filepath);
            File copy = new File(tempFilepath);
            Scanner scanner = new Scanner(copy);
            int lineNumber = 1;
            while (scanner.hasNext()) {
                if (lineNumber != index) {
                    fileWriter.write(scanner.nextLine() + System.lineSeparator());
                } else {
                    fileWriter.write(line + System.lineSeparator());
                    scanner.nextLine();
                }
                lineNumber++;
            }
            fileWriter.close();
            scanner.close();
            Files.delete(Path.of(tempFilepath));
        } catch (IOException e) {
            System.out.println("Encountered an unexpected error with the file :(");
        }
    }
}
