import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents a Storage.
 * Able to retrieve and write data to a storage txt file stored locally.
 */
public class Storage {
    private String path;
    private File file;
    private boolean isExisted;

    Storage(String path) {
        File f = new File(path);
        if (f.exists()) {
            this.file = f;
            this.path = path;
            this.isExisted = true;
        } else {
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
                this.file = f;
                this.path = path;
                this.isExisted = false;
            } catch (IOException e) {
                System.out.println("An error occured");
                e.printStackTrace();
            }
        }
    }

    /**
     * Return boolean if the storage txt file already exists.
     *
     * @return Boolean value of if the storage txt file already exists.
     */
    public boolean getExisted() {
        return this.isExisted;
    }

    /**
     * Writes to storage txt file the tasks in taskList.
     *
     * @param taskList TaskList of tasks to be written in storage txt file.
     */
    void write(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(path, false);
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String taskString = task.getType() + " | " + (task.isCompleted ? 1 : 0) + " | " + task.getTask();
                if (task.getDate() != "") {
                    taskString = taskString + " | " + task.getDate();
                }
                fw.write(taskString);
                fw.write(System.getProperty( "line.separator" ));
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

    /**
     * Retrieve a list of Tasks from the storage txt file.
     *
     * @return An ArrayList of Tasks retrieved from the storage txt file.
     */
    public ArrayList<Task> getTaskList() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String taskString = s.nextLine();
                String[] taskParts = taskString.split(" \\| ");
                if (taskParts[0].equals("T")) {
                    ToDo task = new ToDo(taskParts[2]);
                    if (taskParts[1].equals("1")) {
                        task.updateStatus(true);
                    }
                    taskList.add(task);
                } else if (taskParts[0].equals("E")) {
                    Event task = new Event(taskParts[2], taskParts[3]);
                    if (taskParts[1].equals("1")) {
                        task.updateStatus(true);
                    }
                    taskList.add(task);
                } else if (taskParts[0].equals("D")) {
                    Deadline task = new Deadline(taskParts[2], taskParts[3]);
                    if (taskParts[1].equals("1")) {
                        task.updateStatus(true);
                    }
                    taskList.add(task);
                } else {
                    System.out.println(Arrays.toString(taskParts));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    @Override
    public String toString() {
        return file.getAbsolutePath();
    }
}
