package main.java.Storage;

import main.java.Parser.Parser;
import main.java.Task.DeadlineTask;
import main.java.Task.EventTask;
import main.java.Task.Task;
import main.java.Task.TaskList;
import main.java.Task.TodoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filepath;
    private File file;

    public Storage(){}

    public Storage(String filepath) throws IOException {
        this.filepath = filepath;

        String[] strings = Parser.fileParser(filepath);
        String parentPath = strings[0];
        String childPath = strings[1];
        this.file = new File(parentPath, childPath);


        if (!this.file.exists()) {
            this.file.mkdir();
            this.file.createNewFile();
        }

    }

    public List<Task> load() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();

        Scanner s = new Scanner(this.file);

        while (s.hasNext()) {
            tasks.add(Parser.readFileParser(s.nextLine()));
        }
        return tasks;
    }

    public void updateFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filepath);
        StringBuilder txtToAdd = new StringBuilder();

        for(Task task : tasks.getTasks()) {
            if (task instanceof TodoTask) {
                TodoTask todoTask = (TodoTask) task;
                if (todoTask.isDone()) {
                    txtToAdd.append("T").append(" | ").append("1").append(" | ");
                } else {
                    txtToAdd.append("T").append(" | ").append("0").append(" | ");
                }
                txtToAdd.append(todoTask.getDescription()).append("\n");
            } else if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                if (eventTask.isDone()) {
                    txtToAdd.append("E").append(" | ").append("1").append(" | ");
                } else {
                    txtToAdd.append("E").append(" | ").append("0").append(" | ");
                }
                txtToAdd.append(eventTask.getDescription()).append(" | ")
                        .append(eventTask.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).append("\n");
            } else if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                if (deadlineTask.isDone()) {
                    txtToAdd.append("D").append(" | ").append("1").append(" | ");
                } else {
                    txtToAdd.append("D").append(" | ").append("0").append(" | ");
                }
                txtToAdd.append(deadlineTask.getDescription()).append(" | ")
                        .append(deadlineTask.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).append("\n");
            }
        }
        fw.write(txtToAdd.toString());
        fw.close();
    }

}
