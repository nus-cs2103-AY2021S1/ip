import Task.Task;
import Task.Deadline;
import Task.Todo;
import Task.Event;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveData {
    private String taskToCommand(ArrayList<Task> tasks) {
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
    public void saveData(ArrayList<Task> data) {
        //System.out.println("hello");
        try {
            File file = new File("data/save_file.txt");
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, false);
            String commands = taskToCommand(data);
            //System.out.println(commands);
            writer.write(commands);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
