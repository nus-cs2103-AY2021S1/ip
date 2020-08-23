package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import duke.task.*;
import duke.exception.*;
import duke.command.*;
import java.io.IOException;

public class Storage {
    private static final String path = "data/duke.txt";

    public static LinkedList<Task> readList() throws IOException, NoDataFileException {
        LinkedList<Task> list = new LinkedList<>();

        try {
            File data = new File(path);
            Scanner sc = new Scanner(data);

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                Task currentTask = new Task("placeholder");

                String[] parts = currentLine.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        currentTask = new Todo(parts[2]);
                        break;
                    case "D":
                        currentTask = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        currentTask = new Event(parts[2], parts[3]);
                        break;
                }
                if (parts[1].equals("1")) currentTask.markAsDone();
                list.add(currentTask);
            }
        } catch (FileNotFoundException e) {
            if (!new File(path).exists()) {
                new File("data").mkdir();
                new File(path).createNewFile();
                if (!new File(path).exists()) {
                    throw new IOException("Cannot access the file.");
                } else {
                    throw new NoDataFileException();
                }
            }
        }
        return list;
    }

    public static void save(LinkedList<Task> list) {
        try {
          FileWriter writer = new FileWriter(path);
          for (Task task : list) {
              writer.write(task.toDataString() + "\n");
          }
          writer.close();
        } catch (IOException e) {
            System.out.println("     Oops! Something wrong happened when writing the data!");
        }
    }
}
