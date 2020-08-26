package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {

    public static final String savedFile = "data/storage.txt";
    public static final String savedFolder = "data";
    public Task task;

    public Storage() {}
    public Storage(Task task) {
        this.task = task;
    }

    public static List<Task> readFile() throws IOException {
        List<Task> todoList = new ArrayList<>();
        Path path = Paths.get(savedFile);
        List<String> list = new ArrayList<>(Files.readAllLines(path));
        for (String task : list) {
            Task current = null;
            String[] split = task.split(" \\| ");
            if (split[0].equals("T")) {
                current = new Todo(split[2]);
            } else if (split[0].equals("E")) {
                current = new Event(split[2], split[3]);
            } else if (split[0].equals("D")) {
                current = new Deadline(split[2], split[3]);
            }
            if (split[1].equals("1")) {
                current.markAsDone();
            }
            todoList.add(current);
        }
        return todoList;
    }

    public static void addToFile(String task) throws IOException {
        File file = new File(savedFile);
        File fold = new File(savedFolder);
        if (!fold.isDirectory()) {
            fold.mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, true);
        fw.write(task + "\n");
        fw.close();
    }

    public static void editFile(int number) throws IOException {
        Path path = Paths.get(savedFile);
        List<String> list = new ArrayList<>(Files.readAllLines(path));
        String doneTask = list.get(number).replace("0","1");
        list.set(number, doneTask);
        Files.write(path,list);
    }

    public static void deleteTask(int number) throws IOException {
        Path path = Paths.get(savedFile);
        List<String> list = new ArrayList<>(Files.readAllLines(path));
        list.remove(number - 1);
        Files.write(path,list);
    }

}
