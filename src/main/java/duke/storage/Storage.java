package duke.storage;

import duke.task.deadline.Deadline;
import duke.task.Task;
import duke.task.eventtask.EventTask;
import duke.task.todo.ToDo;
import duke.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private final File f;
    private Scanner lineReader;

    public Storage(File f) {
        this.f = f;
        try {
            f.createNewFile();
            this.lineReader = new Scanner(f);

        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> loadFile() {
        ArrayList<Task> shelf = new ArrayList<>();
        while (lineReader.hasNextLine()) {
            String data = lineReader.nextLine();
            shelf.add(taskCreator(data));
        }
        return shelf;
    }

    public void updateFile(TaskList shelf) throws IOException {
        FileWriter fw = new FileWriter(f.getAbsolutePath());
        for (int i = 0; i < shelf.getSize(); i++) {
            fw.write(shelf.getTask(i).toString());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    private Task taskCreator(String task) {
        switch (task.charAt(1)) {
        case 'T':
            return new ToDo(task.toString(), true);
        case 'D':
            return new Deadline(task.toString(), true);
        default:
            return new EventTask(task.toString(), true);
        }
    }


}
