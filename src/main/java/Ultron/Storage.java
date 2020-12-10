package ultron;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import ultron.exceptions.ExceptionType;
import ultron.exceptions.UltronException;
import ultron.tasks.Deadline;
import ultron.tasks.Event;
import ultron.tasks.Task;
import ultron.tasks.Todo;

/**
 * The main Storage class for Ultron.
 */
public final class Storage {

    /** To store the datafile location. */
    private final File f;
    /** For serialization and deserialization*/
    private final Gson gson = new Gson();

    /**
     * The Storage class.
     * This writes the data to a file and retrieves it when needed.
     *
     * @param path Path to the storage file.
     */
    public Storage(final String path) {
        f = new File(path);
    }

    /**
     * Encodes a Task to a string.
     *
     * @param task A Task to be encoded.
     * @return String The encoded String of the task.
     */
    private String encode(final Task task) {
        return task.getType() + ":" + gson.toJson(task);
    }

    /**
     * Decodes a string to a Task.
     *
     * @param string String to be decoded to a Task.
     * @return task A task based on the string.
     * @throws UltronException If the command or line is invalid.
     **/
    private Task decode(final String string) throws UltronException {
        String[] data = string.split(":", 2);
        switch(data[0]) {
        case "DEADLINE":
            return gson.fromJson(data[1], Deadline.class);
        case "TODO":
            return gson.fromJson(data[1], Todo.class);
        case "EVENT":
            return gson.fromJson(data[1], Event.class);
        default:
            break;
        }
        throw new UltronException(string, ExceptionType.IO_EXCEPTION);
    }

    /**
     * Fetches all of the data in the storage file to an arraylist of task.
     *
     * @return taskArrayList An Arraylist containing the tasks stored.
     * @throws UltronException If there is an error decoding the file.
     */
    public ArrayList<Task> load() throws UltronException {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(f);
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                taskArrayList.add(decode(nextLine));
            }
            scanner.close();
            return taskArrayList;
        } catch (FileNotFoundException e) {
            return taskArrayList;
        }

    }

    /**
     * Writes all the task in the tasklist to a folder.
     *
     * @param taskArrayList Tasklist containing all the tasks.
     * @throws UltronException If there are any IO errors.
     */
    public void writeAll(final ArrayList<Task> taskArrayList)
            throws UltronException {
        if (!f.exists()) {
            try {
                Files.createDirectory(Path.of(f.getParent()));
            } catch (IOException e) {
                throw new UltronException(f.getPath(),
                    ExceptionType.DIRECTORY_NOT_CREATED);
            }
        }
        try {
            FileWriter fw = new FileWriter(f.getPath());
            for (Task task : taskArrayList) {
                fw.write(encode(task) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new UltronException(f.getPath(), ExceptionType.IO_EXCEPTION);
        }
    }
}
