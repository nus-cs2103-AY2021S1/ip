package duke.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * The class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data from the file to the task list.
     *
     * @return a list of tasks.
     * @throws IOException cannot find existing file.
     */
    public ArrayList<Task> loadData() throws IOException {
        DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        ArrayList<Task> orderList = new ArrayList<>();

        try {
            File dataStorage = new File(filePath);
            Scanner s = new Scanner(dataStorage);
            while (s.hasNext()) {
                String curr = s.nextLine();
                String[] currTask = curr.split(" \\| ");
                Boolean isDone = currTask[1].equals("1");
                switch (currTask[0]) {
                    case "T":
                        orderList.add(new Todo(currTask[2], isDone));
                        break;
                    case "D":
                        orderList.add(new Deadline(currTask[2],
                                LocalDateTime.parse(currTask[3], validFormat), isDone));
                        break;
                    case "E":
                        orderList.add(new Event(currTask[2],
                                LocalDateTime.parse(currTask[3], validFormat), isDone));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            if (new File("data").mkdir()) {
                System.out.println("folder data does not exist yet.");
            } else if (new File(filePath).createNewFile()) {
                System.out.println("File duke.txt does not exist yet.");
            }
        }
        return orderList;

    }

    /**
     * Write the changed message into the file.
     * @param orderList
     */
    public void writeData(ArrayList<Task> orderList) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            for (Task task : orderList) {
                fw.write(task.fileFormattedString() + "\n");
            }
            fw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
