package duke.storage;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Todo;
import duke.task.Event;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveData {
    /**
     * Converts saved tasks to string commands
     * @param tasks
     * @return String
     */
    private static String taskToCommand(ArrayList<Task> tasks) {
        String commands = "";
        for(Task task : tasks) {
            String command = "";
            String status = task.getStatus() ? " 1" : " 0";
            if(task instanceof Deadline) {
                command = "deadline " + task.getContent() + " /by " + ((Deadline)task).getDeadlineStr();
            } else if(task instanceof Event) {
                command = "event " + task.getContent() + " /at " + ((Event)task).getDealineStr();
            } else if(task instanceof Todo) {
                command = "todo " + task.getContent();
            }
            commands += command + status + "\n";
        }
        return commands;
    }

    /**
     * Saves current tasks
     * @param path
     * @param data
     */
    public static void saveData(String path, ArrayList<Task> data) {
        //System.out.println("hello");
        try {
            File file = new File(path);
            if(!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, false);
            String commands = taskToCommand(data);
            //System.out.println(duke.commands);
            writer.write(commands);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
