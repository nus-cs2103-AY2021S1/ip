import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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

    void write(ArrayList<? extends Task> tasks) {
        try {
            FileWriter writer = new FileWriter(path, false);
            for (Task task: tasks) {
                String taskInfo = task.getType() + " | " + (task.isDone() ? 1 : 0) 
                        + " | " + task.getContent();
                if (task.getDate() != "") {
                    taskInfo = taskInfo + " | " + task.getDate();
                }
                taskInfo = taskInfo + "\n";
                writer.write(taskInfo);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to write into " + this.path);
        }
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                String taskContent = sc.nextLine();
                String[] taskInfo = taskContent.split(" | ");
                if (taskInfo[0].equals("T")) {
                    ToDo task = new ToDo(taskInfo[2]);
                    if (taskInfo[2].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                } else if (taskInfo[0].equals("E")) {
                    Event task = new Event(taskInfo[2], taskInfo[3]);
                    if (taskInfo[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                } else if (taskInfo[0].equals("D")) {
                    Deadline task;
                    try {
                        LocalDate taskDeadline = LocalDate.parse(taskInfo[3]);
                        task = new Deadline(taskInfo[2], taskDeadline);
                    } catch (Exception e) {
                        task = new Deadline(taskInfo[2], taskInfo[3]);
                    }
                    if (taskInfo[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to load " + this.path);
        }
        return tasks;
    }
}