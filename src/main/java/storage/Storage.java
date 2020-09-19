package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import parser.Parser;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TaskList;
import task.TodoTask;

/**
 * This Storage class is used to handle creating, reading, and writing
 * a local file.
 */
public class Storage {
    private String filepath;
    private File file;

    /**
     * Constructs a new Storage object.
     */
    public Storage() {}

    /**
     * Constructs a new Storage object with file path as its arguments.
     * It would instantly create the directory and the file if
     * the filepath specified was found in user's local.
     *
     * @param filepath String The path of the file.
     * @throws IOException This is exception would be thrown if the system
     * could detect any file or directory with the specified filepath.
     */
    public Storage(String filepath) throws IOException {
        this.filepath = filepath;

        String[] strings = Parser.fileParser(filepath);
        String parentPath = strings[0];
        String childPath = strings[1];

        File file = new File(parentPath);
        if (!file.exists()) {
            file.mkdir();
        }

        this.file = new File(parentPath, childPath);

        if (!this.file.exists()) {
            this.file.createNewFile();
        }

    }

    /**
     * Loads the data from the given file path, turn into
     * various type of Task object (based on the data), and return
     * a list of task.
     *
     * @return List(Task) Returns a list of type Task.
     * @throws FileNotFoundException This exception would be thrown if
     * the specified file was not found in user's local.
     */
    public List<Task> load() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();

        Scanner s = new Scanner(this.file);

        while (s.hasNext()) {
            tasks.add(Parser.readFileParser(s.nextLine()));
        }
        return tasks;
    }

    /**
     * Updates the external file of the given
     * file path, by taking a list of Task as it's argument.
     *
     * @param tasks TaskList A list of task.
     * @throws IOException This exception would be thrown if
     * the system was not able to write to the specified file path.
     */
    public void updateFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filepath);
        StringBuilder txtToAdd = new StringBuilder();

        for (Task task : tasks.getTasks()) {
            if (task instanceof TodoTask) {
                TodoTask todoTask = (TodoTask) task;
                if (todoTask.isDone()) {
                    txtToAdd.append("T").append(" | ").append("1").append(" | ");
                } else {
                    txtToAdd.append("T").append(" | ").append("0").append(" | ");
                }
                txtToAdd.append(todoTask.getDescription()).append("\n");
            } else if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                if (eventTask.isDone()) {
                    txtToAdd.append("E").append(" | ").append("1").append(" | ");
                } else {
                    txtToAdd.append("E").append(" | ").append("0").append(" | ");
                }
                txtToAdd.append(eventTask.getDescription()).append(" | ")
                        .append(eventTask.getDateTime().format(DateTimeFormatter
                                .ofPattern("yyyy-MM-dd HH:mm"))).append("\n");
            } else if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                if (deadlineTask.isDone()) {
                    txtToAdd.append("D").append(" | ").append("1").append(" | ");
                } else {
                    txtToAdd.append("D").append(" | ").append("0").append(" | ");
                }
                txtToAdd.append(deadlineTask.getDescription()).append(" | ")
                        .append(deadlineTask.getDateTime().format(DateTimeFormatter
                                .ofPattern("yyyy-MM-dd HH:mm"))).append("\n");
            }
        }
        fw.write(txtToAdd.toString());
        fw.close();
    }

}
