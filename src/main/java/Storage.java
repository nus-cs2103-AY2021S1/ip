import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.LinkedList;
import java.util.Scanner;

public class Storage {
    private static final String path = "data/tasks.txt";

    public static LinkedList<Task> readList() throws IOException, NoDataException {
        LinkedList<Task> taskList = new LinkedList<>();
        try {
            File data = new File(path);
            Scanner sc = new Scanner(data);

            while (sc.hasNextLine()) {

                String nextLine = sc.nextLine();
                Task task = new Task("");

                String[] components = nextLine.split(" \\| ");
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

                if (components[1].equals("1")) task.markAsDone();
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            if (!new File(path).exists()) {
                new File("data").mkdir();
                new File(path).createNewFile();
                if (!new File(path).exists()) {
                    throw new IOException("Failed to access the file.");
                } else {
                    throw new NoDataException();
                }
            }
        }
        return taskList;
    }

    public static void saveList(LinkedList<Task> list) throws DukeException {
        try {
            FileWriter writer = new FileWriter(path);
            for (Task task : list) {
                writer.write(task.toStorageString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("I cannot write the data!");
        }

    }
}
