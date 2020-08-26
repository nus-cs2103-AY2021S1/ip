import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
                writer.write(taskInfo);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to write into " + this.path);
        }
    }
}