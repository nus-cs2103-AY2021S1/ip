package duke.support;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * Storage deals with loading tasks from the file
 * and saving tasks in the file
 */
public class Storage {
    private File file;
    private Path path;

    /**
     * Creates a {@code Storage} with given file name.
     *
     * @param fileName A String of file name.
     */
    public Storage(String fileName) {
        String dir = System.getProperty("user.dir");
        Path path = Paths.get(dir, "duke", fileName);
        this.path = path;
        File file = path.toFile();
        if (file.exists()) {
            this.file = file;
        } else {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                this.file = file;
            } catch (IOException e) {
                System.out.println("Failed to create" + fileName);
            }
        }
    }

    /**
     * This method writes all of tasks onto the disk.
     */
    public void write(ArrayList<? extends Task> tasks) {
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task: tasks) {
                String taskInfo = task.getType() + "," + (task.isDone() ? 1 : 0)
                        + "," + task.getContent() + "," + task.getDate() + ","
                        + task.getTag();
                taskInfo = taskInfo + "\n";
                writer.write(taskInfo);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to write into " + this.path.getFileName());
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
            System.out.println("Failed to load" + this.path.getFileName());
        }
        return tasks;
    }


}
