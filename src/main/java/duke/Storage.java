package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;

import java.util.ArrayList;

public class Storage {

    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public void write(Task task) throws IOException {
        FileWriter todoWriter = new FileWriter(this.filepath, true);
        todoWriter.write(task.splitToString());
        todoWriter.close();
    }

    public TaskList load() throws IOException {
        ArrayList<Task> todoList = new ArrayList<Task>();
        BufferedReader reader = new BufferedReader(new FileReader(this.filepath));
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] taskArr = line.split("/");
            if (taskArr[0].equals("T")) {
                todoList.add(new Todo(taskArr[2], taskArr[1]));
            } else if (taskArr[0].equals("E")) {
                todoList.add(new Event(taskArr[1], taskArr[2], LocalDate.parse(taskArr[3])));
            } else if (taskArr[0].equals("D")) {
                todoList.add(new Deadline(taskArr[1], taskArr[2], LocalDate.parse(taskArr[3])));
            }
        }
        return new TaskList(todoList);
    }

    public void overwrite(TaskList tl) throws IOException {
        FileWriter todoWriter = new FileWriter(this.filepath, false);
        for (Task task: tl.todoList
        ) {
            todoWriter.write(task.splitToString());
        }
        todoWriter.close();
    }

}
