package botbot;

import botbot.tasks.Deadline;
import botbot.tasks.Event;
import botbot.tasks.Task;
import botbot.tasks.Todo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the storage of data from the chatbot.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a storage at the specified filepath.
     * 
     * @param filePath Filepath where storage is created.
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads any existing data at the filepath.
     * If there is no existing data, a new file is created at the filepath.
     * 
     * @return List of existing tasks.
     */
    List<Task> load() {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        if (!file.isFile()) {
            try {
                file.createNewFile();
                return new LinkedList<>();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return read();
        }
    }

    /**
     * Reads the existing data at the filepath.
     * 
     * @return List of existing tasks.
     */
    List<Task> read() {
        List<Task> list = new LinkedList<>();
        try {
            FileInputStream file = new FileInputStream(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataArr = data.split("\\|");
                char taskType = dataArr[0].charAt(0);
                boolean isDone = dataArr[1].equals("1");
                String description = dataArr[2];
                if (taskType == Todo.TYPE_CODE) {
                    list.add(new Todo(description, isDone));
                } else if (taskType == Deadline.TYPE_CODE) {
                    String by = dataArr[3];
                    list.add(new Deadline(description, isDone, by));
                } else if (taskType == Event.TYPE_CODE) {
                    String at = dataArr[3];
                    list.add(new Event(description, isDone, at));
                }
            }
            sc.close();
            return list;
        } catch (FileNotFoundException e) {
            System.out.println("    oops! your data file is missing!");
            return null;
        }
    }

    /**
     * Saves the current task list in the storage.
     * 
     * @param tasks Task list to be saved.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                List<String> temp = new LinkedList<>();
                temp.add(String.valueOf(task.getType()));
                temp.add(task.getStatus());
                temp.add(task.getDescription());
                if (task instanceof Deadline) {
                    temp.add(task.getBy());
                } else if (task instanceof Event) {
                    temp.add(task.getAt());
                }
                String data = String.join("|", temp);
                fw.write(data + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
