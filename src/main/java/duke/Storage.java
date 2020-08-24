package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void write(TaskList tasks) throws DukeException {
        try {
            File f = new File(filePath);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                System.out.println("File already exists");
            }
            FileWriter fw = new FileWriter(filePath);
            StringBuilder textToAdd = new StringBuilder();
            for (Task task : tasks.getTaskList()) {
                textToAdd.append(task.toFileString());
            }
            fw.write(textToAdd.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException();
        }
    }

    public ArrayList<Task> load(String filePath) throws DukeException {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String type = sc.nextLine();
                boolean done = Boolean.parseBoolean(sc.nextLine());
                String desc = sc.nextLine();
                Task task;

                switch (type) {
                case "T":
                    task = new Todo(desc);
                    if (done) {
                        task.markAsDone();
                    }
                    if (sc.hasNext()) {
                        sc.nextLine();
                    }
                    break;
                case "D":
                    task = new Deadline(desc, sc.nextLine());
                    if (done) {
                        task.markAsDone();
                    }
                    if (sc.hasNext()) {
                        sc.nextLine();
                    }
                    break;
                case "E":
                    task = new Event(desc, sc.nextLine());
                    if (done) {
                        task.markAsDone();
                    }
                    if (sc.hasNext()) {
                        sc.nextLine();
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
                }
                taskList.add(task);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException();
        }
    }
}