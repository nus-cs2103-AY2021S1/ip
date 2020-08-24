package task;

import java.util.ArrayList;
import java.util.List;

public class DataTranslator {
    public static TaskManager decode(List<String> lines){
        TaskManager taskManager = new TaskManager();
        for(String line : lines){
            Task task = null;
            String[] parsedLine = line.split(" \\| ");
            switch(parsedLine[0]){
                case "T":
                    task = new Todo(parsedLine[2]);
                    break;
                case "D":
                    task = new Deadline(parsedLine[2], parsedLine[3]);
                    break;
                case "E":
                    task = new Event(parsedLine[2], parsedLine[3]);
                default:
                    break;
            }
            if(parsedLine[1].equals("1")){
                System.out.println(parsedLine[2]);
                assert task != null;
                task.markTaskAsDone();
            }
            taskManager.addTask(task);
        }
        return taskManager;
    }

    public static List<String> encode(TaskManager taskManager){
        List<String> data = new ArrayList<>();
        List<Task> tasks = taskManager.getTasks();
        for(Task task : tasks){
            data.add(task.toDataFileFormat());
        }
        return data;
    }
}
