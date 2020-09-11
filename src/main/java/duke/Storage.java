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
    private boolean isLoaded;

    /**
     * Constructs a new Storage object.
     */
    public Storage() {
        this.isLoaded = false;
    }

    /**
     * Creates and returns a File at the specified filepath.
     *
     * @param filepath The destination filepath for creating the file.
     * @throws DukeException If the file could not be created due to an IO error.
     */
    public void makeFile(String filepath) throws DukeException {
        if (isLoaded) {
            throw DukeException.LOADING_CAPACITY_EXCEPTION;
        }
        try {
            this.filepath = filepath;
            String parentFilepath = Path.of(filepath).getParent().toString();
            File parentFolder = new File(parentFilepath);
            if (!parentFolder.exists()) {
                parentFolder.mkdir();
            }
            File taskFile = new File(filepath);
            boolean isNewFile = taskFile.createNewFile();
            if (!isNewFile) {
                throw DukeException.FILE_OVERWRITE_EXCEPTION;
            }
            isLoaded = true;
        } catch (IOException e) {
            throw DukeException.FILE_LOADING_EXCEPTION;
        }
    }

    /**
     * Loads a list of tasks from the source file, if it exists. Otherwise, creates a file
     * at the filepath and returns an empty list.
     *
     * @return A list of tasks parsed from the source file, if any; otherwise, an empty list.
     * @throws DukeException If an I/O error occurs when trying to create the file and parent
     * folders, if any.
     */
    public ArrayList<Task> loadFromFilepath(String filepath) throws DukeException {
        if (isLoaded) {
            throw DukeException.LOADING_CAPACITY_EXCEPTION;
        }
        try {
            this.filepath = filepath;
            File taskFile = new File(filepath);
            Scanner scanner = new Scanner(taskFile);
            ArrayList<Task> tasks = new ArrayList<>();
            while (scanner.hasNext()) {
                tasks.add(Parser.parseTaskFromFile(scanner.nextLine()));
            }
            isLoaded = true;
            return tasks;
        } catch (IOException e) {
            throw DukeException.FILE_LOADING_EXCEPTION;
        }
    }

    /**
     * Appends a line of text to the destination file specified by the filepath.
     * <p>
     * If an I/O error occurs, shows an error message.
     * @param line A line of text to be appended to the destination file.
     */
    public void addLine(String line) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filepath, true);
            fileWriter.write(line + System.lineSeparator());
            fileWriter.close();
        } catch (NullPointerException e) {
            throw DukeException.FILE_LOADING_EXCEPTION;
        } catch (IOException e) {
            throw DukeException.FILE_NO_ACCESS_EXCEPTION;
        }
    }

    /**
     * Deletes a line of text at the specified index from the destination file specified by
     * the filepath.
     *
     * <p>If the index falls outside the range of the number of lines in the file, no lines are
     * deleted, and no error is raised.</p>
     *
     * If an I/O error occurs, shows an error message.
     *
     * @param index The line number to be deleted.
     */
    public void deleteLine(int index) {
        replaceLine(index, "");
    }

    /**
     * Replaces a line of text at the specified index with a specified line of text in the
     * destination file specified by the filepath.
     *
     * <p>If the index falls outside the range of the number of lines in the file, no lines are
     * replaced, and no error is raised.</p>
     *
     * <p>If the replacing text is empty, deletes the line at the index instead of replacing it.</p>
     *
     * If an I/O error occurs, shows an error message.
     *
     * @param index The line number to be replaced.
     * @param line The line of text to replace the previous one.
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
                    lineNumber++;
                    continue;
                }
                if (!line.isEmpty()) {
                    fileWriter.write(line + System.lineSeparator());
                }
                scanner.nextLine();
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
