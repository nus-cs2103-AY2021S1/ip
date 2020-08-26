import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

    public boolean getExisted() {
        return this.isExisted;
    }

    void write(ArrayList<? extends Task> taskList) {
        try {
            FileWriter fw = new FileWriter(path, false);
            for (Task task : taskList) {
                String taskString = task.getType() + " | " + (task.completed ? 1 : 0) + " | " + task.getTask();
                if (task.getDate() != "") {
                    taskString = taskString + "| " + task.getDate();
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

    public ArrayList<Task> getTaskList() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String taskString = s.nextLine();
                String[] taskParts = taskString.split(" | ");
                if (taskParts[0].equals("T")) {
                    ToDo task = new ToDo(taskParts[4]);
                    if (taskParts[2].equals("1")) {
                        task.updateStatus(true);
                    }
                    taskList.add(task);
                } else if (taskParts[0].equals("E")) {
                    Event task = new Event(taskParts[4], taskParts[6]);
                    if (taskParts[2].equals("1")) {
                        task.updateStatus(true);
                    }
                    taskList.add(task);
                } else if (taskParts[0].equals("D")) {
                    Deadline task = new Deadline(taskParts[4], taskParts[6]);
                    if (taskParts[2].equals("1")) {
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
