package duke.storage;

import duke.task.Task;
import duke.task.Event;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.TaskList;

import duke.dukeException.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" \\| ");
                Task task;

                switch (line[0]) {
                case "T":
                    task = new Todo(line[2]);
                    break;
                case "D":
                    task = new Deadline(line[2], line[3]);
                    break;
                case "E":
                    task = new Event(line[2], line[3]);
                    break;
                default:
                    throw new DukeException("Failed to load tasks, check file for syntax errors");
                }

                if (line[1].equals("1")) {
                    task.setDone();
                }
                tasks.add(task);
            }
            scanner.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File Not Found.");
        }
    }

    public void saveFile(TaskList list) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task t : list.getList()) {
                fileWriter.write(t.toFile());
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Save to File Failed.");
        }
    }
}
