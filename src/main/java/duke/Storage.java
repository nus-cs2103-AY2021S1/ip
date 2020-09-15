package duke;

import duke.command.FileException;
import duke.financial.FinanceList;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Scanner;

/**
 * A file-reader and writer responsible for retrieving a list of entries from an existing
 * text file in the specified filepath, or otherwise creates that file. It can also edit
 * the text file by adding, deleting or replacing a line.
 */
public class Storage {

    /**
     * Absolute or relative filepath of the source of the file to be retrieved or the
     * destination of the file to be written.
     */
    private String filepath;
    /** Absolute or relative filepath of the parent of the source file. */
    private String parentFilepath;
    /** List of tasks being managed. */
    private TaskList tasks;
    /** List of financial records being managed. */
    private FinanceList finances;
    /** Boolean indicator of whether there is a file currently loaded. */
    private boolean isLoaded;

    /**
     * Constructs a new Storage object.
     */
    public Storage() {
        isLoaded = false;
    }

    /**
     * Creates and returns a File at the specified filepath, and initializes the relevant fields of the storage.
     *
     * @param filepath The destination filepath for creating the file.
     * @throws DukeException If the file could not be created due to an IO error.
     */
    public void makeNewFile(String filepath, String fileType) throws DukeException {
        if (isLoaded) {
            throw FileException.FILE_ALREADY_LOADED;
        }
        try {
            this.filepath = filepath;
            parentFilepath = Path.of(filepath).getParent().toString();
            File parentFolder = new File(parentFilepath);
            if (!parentFolder.exists()) {
                parentFolder.mkdir();
            }
            File taskFile = new File(filepath);
            boolean isNewFile = taskFile.createNewFile();
            if (!isNewFile) {
                throw FileException.FILE_ALREADY_EXISTS;
            }
            if (fileType.equals("task")) {
                tasks = new TaskList();
            } else if (fileType.equals("finance")) {
                finances = new FinanceList();
            } else {
                throw FileException.INVALID_FILE_SPECIFICATION;
            }
            isLoaded = true;
        } catch (IOException e) {
            throw FileException.FILE_NO_ACCESS;
        }
    }

    /**
     * Loads a list of entries from the source file, if the file exists and there is no file currently loaded.
     *
     * @throws DukeException If there is already a file currently loaded, or if an error occurred when trying
     * to read the file.
     */
    public void loadFile(String filepath, String fileType) throws DukeException {
        if (isLoaded) {
            throw FileException.FILE_ALREADY_LOADED;
        }
        try {
            File file = initialize(filepath);
            load(file, fileType);
            isLoaded = true;
        } catch (IOException e) {
            throw FileException.FILE_NOT_FOUND;
        }
    }

    /**
     * Initializes the basic fields of the storage (the filepath and its parent directory).
     *
     * @param filepath The filepath of the source file.
     * @return The file at the filepath.
     */
    private File initialize(String filepath) {
        this.filepath = filepath;
        parentFilepath = Path.of(filepath).getParent().toString();
        return new File(filepath);
    }

    /**
     * Loads either the task-list or the finance-list, depending on the type of the file (task or finance).
     *
     * @param file The source file.
     * @param fileType The type of the file (task or finance).
     * @throws IOException If an IO error occurred while trying to read the file.
     * @throws DukeException If the specified file type is not recognized.
     */
    private void load(File file, String fileType) throws IOException, DukeException {
        Scanner scanner = new Scanner(file);
        switch (fileType) {
        case "task":
            loadTasks(scanner);
            break;
        case "finance":
            loadFinances(scanner);
            break;
        default:
            throw FileException.INVALID_FILE_SPECIFICATION;
        }
    }

    /**
     * Initializes the task-list and loads it based on input from scanning and parsing a file.
     *
     * @param scanner The file scanner.
     * @throws DukeException If the input from the scanner cannot be parsed properly.
     */
    private void loadTasks(Scanner scanner) throws DukeException {
        tasks = new TaskList();
        while (scanner.hasNext()) {
            tasks.addEntry(Parser.parseTaskFromFile(scanner.nextLine()));
        }
    }

    /**
     * Initializes the finance-list and loads it based on input from scanning and parsing a file.
     *
     * @param scanner The file scanner.
     * @throws DukeException If the input from the scanner cannot be parsed properly.
     */
    private void loadFinances(Scanner scanner) throws DukeException {
        finances = new FinanceList();
        while (scanner.hasNext()) {
            finances.addEntry(Parser.parseCategoryFromFile(scanner.nextLine().trim()));
        }
    }

    /**
     * Gets the task-list.
     *
     * @return The task-list.
     * @throws FileException If there is no file currently loaded, or if the file currently loaded is of the
     * wrong type.
     */
    public TaskList getTasks() throws FileException {
        if (!isLoaded) {
            throw FileException.FILE_NOT_LOADED;
        }
        if (tasks == null) {
            throw FileException.WRONG_FILE_TYPE;
        }
        return tasks;
    }

    /**
     * Gets the finance-list.
     *
     * @return The finance-list.
     * @throws FileException If there is no file currently loaded, or if the file currently loaded is of the
     * wrong type.
     */
    public FinanceList getFinances() throws FileException {
        if (!isLoaded) {
            throw FileException.FILE_NOT_LOADED;
        }
        if (finances == null) {
            throw FileException.WRONG_FILE_TYPE;
        }
        return finances;
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
            throw FileException.FILE_NOT_FOUND;
        } catch (IOException e) {
            throw FileException.FILE_NO_ACCESS;
        }
    }

    /**
     * Deletes a line of text at the specified index from the destination file specified by
     * the filepath.
     *
     * <p>If the index falls outside the range of the number of lines in the file, no lines are
     * deleted, and no error is raised.</p>
     *
     * @param index The line number to be deleted.
     * @throws FileException If an IO error occurred while accessing, reading or writing to the file.
     */
    public void deleteLine(int index) throws FileException {
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
     * @param index The line number to be replaced.
     * @param line The line of text to replace the previous one.
     * @throws FileException If an IO error occurred while accessing, reading or writing to the file.
     */
    public void replaceLine(int index, String line) throws FileException {
        try {
            String tempFilepath = parentFilepath + "/temp.txt";
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
            throw FileException.FILE_NO_ACCESS;
        }
    }

    /**
     * Resets the storage so that it is ready to work with a new file.
     *
     * <p>After resetting, no other commands from the user will work until they create
     * or load another file.</p>
     */
    public void reset() {
        this.filepath = null;
        this.parentFilepath = null;
        this.tasks = null;
        this.finances = null;
        this.isLoaded = false;
    }
}
