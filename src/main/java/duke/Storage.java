package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Storage deals with loading tasks from the file
 * and saving tasks in the file
 */
public class Storage {
    private String path;
    private File file;

    Storage(String path) {
        File file = new File(path);
        if (file.exists()) {
            this.file = file;
            this.path = path;
        } else {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                this.file = file;
                this.path = path;
            } catch (IOException e) {
                System.out.println("Failed to create" + this.path);
            }
        }
    }

    /**
     * This method wites all of tasks onto the disk.
     *
     */
    public void write(ArrayList<? extends Task> tasks) {
        try {
            FileWriter writer = new FileWriter(path, false);
            for (Task task: tasks) {
                String taskInfo = task.getType() + "," + (task.isDone() ? 1 : 0)
                        + "," + task.getContent() + "," + task.getDate() + ","
                        + task.getTag();
                taskInfo = taskInfo + "\n";
                writer.write(taskInfo);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to write into " + this.path);
        }
    }

    /**
     * This method fetches all of tasks stored in disk.
     *
     * @return an ArrayList consisting of all of tasks stored in the duke.txt.
     */
    public ArrayList<Task> getTaskList() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                String taskContent = sc.nextLine();
                String[] taskInfo = taskContent.split(",");
                Task task;
                if (taskInfo[0].equals("T")) {
                    task = new ToDo(taskInfo[2]);
                } else if (taskInfo[0].equals("E")) {
                    task = new Event(taskInfo[2], taskInfo[3]);
                } else if (taskInfo[0].equals("D")) {
                    try {
                        LocalDate taskDeadline = LocalDate.parse(taskInfo[3]);
                        task = new Deadline(taskInfo[2], taskDeadline);
                    } catch (Exception e) {
                        task = new Deadline(taskInfo[2], taskInfo[3]);
                    }
                } else {
                    task = new Task(taskInfo[2]);
                }
                if (taskInfo[1].equals("1")) {
                    task.markAsDone();
                }
                if (!taskInfo[4].equals("null")) {
                    task.setTag(taskInfo[4]);
                }
                tasks.add(task);
            }
        } catch (Exception e) {
            System.out.println("Failed to load" + this.path);
        }
        return tasks;
    }
}
