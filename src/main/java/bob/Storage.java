package bob;

import bob.exception.BobDateTimeParseException;
import bob.exception.BobFileNotFoundException;
import bob.exception.BobIOException;
import bob.exception.BobIndexOutOfBoundsException;
import bob.exception.BobStorageInitialisationException;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class consists of methods pertaining to the management of data storage for Bob.
 */
public class Storage {

    /** The File where data will be stored. */
    private File save;

    /** The FileWriter which will write data to storage. */
    private FileWriter writer;


    /**
     * Constructs a text file for data storage at the provided file path.
     * @param filePath the designated file path where data will be stored.
     */
    public Storage(String filePath) {
        this.save = new File(filePath);
    }

    /**
     * Initialises storage for Bob.
     * The method first checks if the provided file and its
     * directories exist, and creates them if they do not.
     * The method also creates a writer for the file so that data may be written or appended to it.
     *
     * @throws BobStorageInitialisationException
     */
    public void initialiseStorage() throws BobStorageInitialisationException {
        File directory = new File(this.save.getParent());
        directory.mkdirs();
        try {
            save.createNewFile();
        } catch (IOException e) {
            throw new BobStorageInitialisationException();
        }

        try {
            writer = new FileWriter(save, true);
        } catch (IOException e) {
            throw new BobStorageInitialisationException();
        }
    }

    /**
     * Loads existing data that may be accessed by Bob.
     * A scanner scans a text file consisting of saved data, and
     * creates tasks accordingly, which are added to the provided TaskList
     * used by Bob.
     *
     * @param tasks the TaskList used by Bob. Created tasks from the text file
     *              will be added accordingly to this.
     * @throws BobFileNotFoundException if the file to load from does not exist.
     */

    public void loadSave(TaskList tasks) throws BobIOException, BobFileNotFoundException, BobDateTimeParseException {
        Scanner sc = null;
        try {
            sc = new Scanner(save);
        } catch (FileNotFoundException e) {
            throw new BobFileNotFoundException();
        }
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (!str.isEmpty()) {
                Task task = loadTask(str);
                tasks.add(task);
            }
        }
    }

    /**Updates the Storage's text file according to the tasks in a provided TaskList.
     * The method clears all data on the Storage's text file. It then
     * iterates through all tasks on the TaskList and adds their data to the Storage's text file.
     *
     * @param tasks the TaskList consisting of all tasks tracked by Bob.
     * @throws BobIOException if the Storage's text file does not exist.
     * @throws BobIndexOutOfBoundsException if the method tries to get a task with an index that is not on the TaskList.
     */
    public void updateSave(TaskList tasks) throws BobIOException, BobIndexOutOfBoundsException {
        FileWriter deleter = null;

        try {
            deleter = new FileWriter(save);
        } catch (IOException e) {
            throw new BobIOException();
        }

        for (int i = 1; i < tasks.size()+1; i++) {
            Task task = tasks.get(i);
            try {
                deleter.append(task.saveFormat() + System.lineSeparator());
            } catch (IOException e) {
                throw new BobIOException();
            }
        }

        try {
            deleter.close();
        } catch (IOException e) {
            throw new BobIOException();
        }
    }

    /**
     * Appends provided data to the Storage's text file.
     *
     * @param data to be appended to the Storage's text file.
     * @throws BobIOException if the Storage's text file does not exist.
     */
    public void appendToStorage(String data) throws BobIOException {
        try {
            writer.append(data);
        } catch (IOException e) {
            throw new BobIOException();
        }
    }

    /**
     * Flushes the Storage's writer.
     *
     * @throws BobIOException if the Storage's text file does not exist.
     */

    public void flushWriter() throws BobIOException {
        try {
            writer.flush();
        } catch (IOException e) {
            throw new BobIOException();
        }
    }

    /**
     * Closes the Storage's writer.
     * @throws BobIOException if the Storage's text file does not exist.
     */
    public void closeWriter() throws BobIOException {
        try {
            writer.close();
        } catch (IOException e) {
            throw new BobIOException();
        }
    }

    /**
     * Returns a task after loading String data.
     *
     * @param data the data of a task.
     * @return a task which matches the provided data.
     * @throws BobDateTimeParseException if deadlines or events have dates which do not comply with the correct format.
     */
    public Task loadTask(String data) throws BobDateTimeParseException {
        String[] taskDataArr = data.split("\\|");
        char firstChar = taskDataArr[0].charAt(0);
        boolean isDone = taskDataArr[1].substring(1, 2).equals("1");
        if (firstChar == 'T') {
            String description = taskDataArr[2].substring(1);
            return new Todo(isDone, description);
        } else if (firstChar == 'D') {
            String description = taskDataArr[2].substring(1, taskDataArr[2].length() - 1);
            String deadline = taskDataArr[3].substring(1);
            return new Deadline(isDone, description, deadline);
        } else if (firstChar == 'E') {
            String description = taskDataArr[2].substring(1, taskDataArr[2].length() - 1);
            String period = taskDataArr[3].substring(1);
            return new Event(isDone, description, period);
        } else {
            return null;
        }
    }
}
