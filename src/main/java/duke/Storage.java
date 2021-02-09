package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * A class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    protected String filePath;

    /**
     * Constructs a new Storage class initialized with the path of the target file.
     *
     * @param filePath the path of the file to load and store.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to the target file.
     *
     * @param taskList the list of tasks.
     * @throws DukeException thrown if the file or folder does not exist or other IOException happens.
     */
    public void store(List<Task> taskList) throws DukeException {
        File outFile;
        Writer out;

        try {
            outFile = new File(filePath);
            if (!outFile.getParentFile().exists()) {
                throw new DukeException(" OOPS!!! The folder does not exist.");
            } else if (!outFile.exists()) {
                throw new DukeException(" OOPS!!! The file does not exist.");
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);

            for (int i = 0; i < taskList.size(); ++i) {
                Task task = taskList.get(i);
                out.write(
                        task.toStore() + "\n"
                );
            }

            out.flush();
            out.close();
        } catch (IOException e) {
            throw new DukeException(" OOPS!!! There is an IOException: " + e.getMessage());
        }
    }

    /**
     * Loads the list of task from the target file.
     *
     * @return a List of Task loaded from the target file.
     * @throws DukeException thrown if the file or folder does not exist or other IOException happens.
     */
    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        FileReader fileReader;
        BufferedReader bufferedReader;

        try {
            File file = new File(filePath);
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                boolean isSuccess = fileParent.mkdirs();
                if(!isSuccess) throw new DukeException(" Oops! Fail to create the storage dictionary.");
            }
            if (!file.exists()) {
                boolean isSuccess = file.createNewFile();
                if(!isSuccess) throw new DukeException(" Oops! Fail to create the storage file.");
            }
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isBlank()) {
                    taskList.add(parseStorageToTask(line));
                }
            }
            return taskList;
        } catch (IOException e) {
            throw new DukeException(" OOPS!!! There is an IOException: " + e.getMessage());
        }
    }

    private Task parseStorageToTask(String line) throws DukeException {
        String[] commands = line.split(" \\| ");
        boolean isDone = commands[1].equals("1");
        switch (commands[0]) {
        case "T": {
            assert commands.length == 3 : "Wrong storage format for Todo Task.";
            return new Todo(isDone, commands[2]);
        }
        case "D": {
            assert commands.length == 4 : "Wrong storage format for Deadline Task.";
            return new Deadline(isDone, commands[2], commands[3]);
        }
        case "E": {
            assert commands.length == 4 : "Wrong storage format for Event Task.";
            return new Event(isDone, commands[2], commands[3]);
        }
        default: {
            throw new DukeException(" Ops, the storage format goes wrong somewhere.");
        }
        }
    }
}
