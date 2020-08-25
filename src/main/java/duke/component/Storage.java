package duke.component;

import java.util.LinkedList;
import java.util.Scanner;

import java.io.*;

import duke.task.*;

public class Storage {
    private final String path;
    public Storage(String path) {
        this.path = path;
    }
    public LinkedList<Task> readList() throws DukeException {
        LinkedList<Task> taskList = new LinkedList<>();
        try {
            File data = new File(path);
            Scanner sc = new Scanner(data);

            while (sc.hasNextLine()) {

                String nextLine = sc.nextLine();
                Task task = new Task("");

                String[] components = nextLine.split(" \\| ");
                try {
                    switch (components[0]) {
                        case "T":
                            task = new Todo(components[2]);
                            break;
                        case "D":
                            task = new Deadline(components[2], components[3]);
                            break;
                        case "E":
                            task = new Event(components[2], components[3]);
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

                if (components[1].equals("1")) task.markAsDone();
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            if (!new File(path).exists()) {
                new File("data").mkdir();
                try {
                    new File(path).createNewFile();
                } catch (IOException ioException) {
                    throw new DukeException("I cannot create the data file!");
                }
                if (!new File(path).exists()) {
                    throw new DukeException("Failed to access the file!");
                } else {
                    throw new DukeException("No data file found, a new data file created!");
                }
            }
        }
        return taskList;
    }

    public void saveList(TaskList list) throws DukeException {
        try {
            FileWriter writer = new FileWriter(path);
            for (Task task : list.getList()) {
                writer.write(task.toStorageString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("I cannot write the data!");
        }

    }
}
