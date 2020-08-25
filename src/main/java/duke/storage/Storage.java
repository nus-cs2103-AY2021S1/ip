package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File data;
    
    public Storage(String filePath) throws DukeException {
        try {
            File data = new File(filePath);
            if (!data.exists()) {
                data.createNewFile();
            }
            this.data = data;
        } catch (IOException error) {
            throw new DukeException(" I cannot find the directory :(");
        }
    }
    
    public ArrayList<Task> loadTasks() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner sc = new Scanner(this.data);
            while (sc.hasNextLine()) {
                String[] inputs = sc.nextLine().split(" \\| ");
                String type = inputs[0];
                boolean isDone = inputs[1].equals("1");
                String description = inputs[2];
                LocalDate time;
                if (type.equals("T")) {
                    tasks.add(new ToDo(description, isDone));
                } else if (type.equals(("D"))) {
                    time = LocalDate.parse(inputs[3]);
                    tasks.add(new Deadline(description, isDone, time));
                } else if (type.equals("E")) {
                    time = LocalDate.parse(inputs[3]);
                    tasks.add(new Event(description, isDone, time));
                }
            }
            return tasks;
        } catch (FileNotFoundException error) {
            throw new DukeException(" I cannot find the directory :(");
        }
    }
    
    public void saveTasks(ArrayList<Task> tasks) throws DukeException {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(this.data));
            for (int i = 0; i < tasks.size(); i++) {
                writer.println(tasks.get(i).saveToHardDisk());
            }
            writer.close();
        } catch (IOException error) {
            throw new DukeException(" I cannot save your tasks :(");
        }
    }
}
