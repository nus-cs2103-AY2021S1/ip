import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class TaskStorage {
    public static void save_task(File prev_tasks, TaskList tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(prev_tasks));
            for (int i = 0; i < tasks.getSize(); i++) {
                writer.write(tasks.getTask(i).getFileString());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}