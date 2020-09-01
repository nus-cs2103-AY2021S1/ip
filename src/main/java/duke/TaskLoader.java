package duke;

import java.io.File;
import java.util.Scanner;

public class TaskLoader {
    /**
     * loadTasks is used to populate TaskList object, with tasks from a txt
     * file
     * @param prev_tasks File object that has all previous tasks
     * @param tasks TaskList object to be populated
     */
    public static void loadTasks(File prev_tasks, TaskList tasks) {
        try {
            Scanner sc_file = new Scanner(prev_tasks);
            while (sc_file.hasNextLine()) {
                String[] saved_task = sc_file.nextLine().split("~");
                
                Task new_task = Parser.parseTask(saved_task[1]);
                if (saved_task[0].compareTo("T") == 0) {
                    new_task.done();
                }
                tasks.addTask(new_task);
            }
            sc_file.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
}
