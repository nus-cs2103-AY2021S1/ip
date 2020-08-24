package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import duke.task.*;
import duke.exception.*;

public class Storage {
    private static final String path = "data/duke.txt";

    /**
     * Read stored task list from the disk data file.
     * This method loads the stored task list from the disk data file. If the file cannot be found, <code>Storage</code>
     * will create a new file for the user and throws <code>NoDataFileException</code> to notify the chat bot. If there
     * is an error accessing the file and new file cannot be created, <code>IOException</code> will be thrown to notify
     * the chat bot.
     * @return a <code>LinkedList</code> of tasks read from the data file
     * @throws IOException throw if there is some error accessing the file and we cannot create a new one
     * @throws NoDataFileException throw if there is no file copy and Duke has successfully created a new one
     */
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

    /**
     * Write a list of tasks to the local data file.
     * Write a list of tasks to the local data file, the format of the data file can be understood by
     * <code>Storage</code>, so that it can read the file and convert back to a list of tasks.
     * @param list the list of tasks to be written into the data file
     */
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
