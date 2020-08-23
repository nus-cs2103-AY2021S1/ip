package duke;

import duke.exception.DukeFileException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Storage {
    private static SimpleDateFormat writeformatter = new SimpleDateFormat ("MMM dd yyyy hh:mm a");
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public TaskList writeData(TaskList taskList) throws DukeFileException {
        if (taskList != null) {
            try{
                FileWriter writer = new FileWriter(this.filePath);
                StringBuilder str = new StringBuilder();
                for(Task task : taskList.getTasks()) {
                    str.append(task.toFileString());
                    str.append("\n");
                }
                    writer.write(str.toString());
                    writer.flush();
                    writer.close();
            } catch (IOException e) {
                throw new DukeFileException();
            }
        }
        return taskList;
    }

    public TaskList loadData() throws DukeFileException {
        TaskList taskList = new TaskList();
        try {
            File file = new File(this.filePath);
            if (file.exists()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNext()) {
                    String type = sc.nextLine();
                    String done = sc.nextLine();
                    String desc = sc.nextLine();
                    Task t;
                    if (type.equals("T")) {
                        t = new ToDo(desc);
                    } else if (type.equals("E")) {
                        t = new Event(desc, writeformatter.parse(sc.nextLine()));
                    } else {
                        t = new Deadline(desc, writeformatter.parse(sc.nextLine()));
                    }
                    if (done.equals("T")) {
                        t.completeTask();
                    }
                    sc.nextLine();
                    taskList.addATask(t);
                }
            }
            return taskList;
        } catch (IOException | ParseException e) {
            throw new DukeFileException();
        }
    }
}

