package duke.storage;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File FILE;

    public Storage(String filePath) {
        this.FILE = new File(filePath);
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> lst = new ArrayList<>();

        try {
            Scanner fileReader = new Scanner(FILE);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] command = line.split(" \\| ");
                switch (command[0]) {
                    case "T":
                        Task todo = new Task("todo", command[2]);
                        if (command[1].equals("1")) {
                            todo.setDone();
                        }
                        lst.add(todo);
                        break;
                    case "D":
                        Task deadline = new Task("deadline", command[2], LocalDate.parse(command[3]));
                        if (command[1].equals("1")) {
                            deadline.setDone();
                        }
                        lst.add(deadline);
                        break;
                    case "E":
                        Task event = new Task("event", command[2], LocalDate.parse(command[3]));
                        if (command[1].equals("1")) {
                            event.setDone();
                        }
                        lst.add(event);
                        break;
                }
            }
            fileReader.close();
        } catch (IOException e) {
            throw new DukeException("\tFile not Found.");
        } catch (Exception e) {
            throw new DukeException("\tError loading history. " + e.getMessage());
        }

        return lst;
    }

    //Write to file
    public void write(ArrayList<Task> lst) throws DukeException {
        try {
            FILE.getParentFile().mkdir();
            FileWriter fileWriter = new FileWriter(FILE);
            String listToString = "";
            for (Task t : lst) {
                if (t.getType() == TaskType.TODO) {
                    listToString += "T | " + (t.getStatus() ? 1 : 0) + " | " + t.getDescription() + "\n";
                } else if (t.getType() == TaskType.DEADLINE) {
                    listToString += "D | " + (t.getStatus() ? 1 : 0) + " | " + t.getDescription() + " | " +
                            t.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n";
                } else {
                    listToString += "E | " + (t.getStatus() ? 1 : 0) + " | " + t.getDescription() + " | " +
                            t.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n";
                }
            }
            fileWriter.write(listToString);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error during writing. " + e.getMessage());
        }

    }
}
