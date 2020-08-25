package duke.storage;

import duke.exceptions.DukeException;

import duke.task.DateTimeParser;
import duke.task.*;
import duke.utils.Colour;
import duke.utils.Ui;

import java.util.ArrayList;
import java.util.List;

public class DataTranslator {
    private static final Ui formatter = new Ui();
    public static TaskManager decode(List<String> lines){
        TaskManager taskManager = new TaskManager();
        for(String line : lines){
            Task task = null;
            String[] parsedLine = line.split(" \\| ");
            DukeDateTime dukeDateTime;
            try {
                switch (parsedLine[0]) {
                    case "T":
                        task = new Todo(parsedLine[2]);
                        break;
                    case "D":
                        dukeDateTime = DateTimeParser.parseDateTime(parsedLine[3]);
                        task = new Deadline(parsedLine[2], dukeDateTime);
                        break;
                    case "E":
                        dukeDateTime = DateTimeParser.parseDateTime(parsedLine[3]);
                        task = new Event(parsedLine[2], dukeDateTime);
                    default:
                        break;
                }
            } catch(DukeException e){
                formatter.print(Colour.Red(e.getMessage()));
            }
            if(parsedLine[1].equals("1")){
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
