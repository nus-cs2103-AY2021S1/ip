package duke.util;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.FixedDurationTask;
import duke.task.FixedDurationTaskWithDateTime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * The storage handles all the reading and writing between
 * the program and the txt file which stores all the tasks.
 * This enables the saving and loading feature of the application.
 */
public class Storage {

    private final File file;

    /**
     * Constructs the storage with a given file path. If the path
     * does not exist, the directories and file will be created.
     * @param filePath the path of the txt file.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }
        }
    }

    /**
     * Loads the task list from the txt file. A new task list
     * is created and populated with the corresponding tasks as
     * parsed in the txt file.
     * @return the list of tasks.
     * @throws DukeException when task creation fails or txt file can't be read.
     */
    public List<Task> load() throws DukeException {
        List<Task> list = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] t = sc.nextLine().split("\\|");
                assert List.of("T", "E", "D", "F").contains(t[0]) : "Subtype should be T, E, F, or D only";
                switch (t[0]) {
                case "T":
                    list.add(new Todo(t[2], t[1].equals("1")));
                    break;
                case "D":
                    list.add(new Deadline(t[2], t[3], t[1].equals("1")));
                    break;
                case "E":
                    list.add(new Event(t[2], t[3], t[1].equals("1")));
                    break;
                case "F":
                    FixedDurationTask fdt;
                    if (t.length > 4) {
                        fdt = new FixedDurationTaskWithDateTime(t[2], Integer.parseInt(t[3]), t[4]);
                    } else {
                        fdt = new FixedDurationTask(t[2], Integer.parseInt(t[3]));
                    }
                    if (t[1].equals("1")) {
                        fdt.markAsDone();
                    }
                    list.add(fdt);
                    break;
                default:
                    throw new DukeException("Corrupted file, previous data will not be loaded!");
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return list;
    }

    /**
     * Updates the task list txt file using the given task list.
     * Tasks are generally written to the txt file in the following format:
     *     type|isDone|description|others
     * where 'others' is only relevant to certain types of tasks.
     * @param list the list of task to be written into txt file.
     */
    public void update(List<Task> list) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task t: list) {
                StringBuilder toWrite = new StringBuilder(
                        String.format("%s|%s", t.getType(), (t.getDone() ? "1" : "0")));
                String desc = t.getDescription();
                if (List.of("D", "E", "F").contains(t.getType())) {
                    toWrite.append("|");
                    String[] descSplit = desc.split(" / ");
                    toWrite.append(String.join("|", descSplit));
                } else {
                    toWrite.append("|").append(desc);
                }
                fw.write(toWrite + "\n");
            }
            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }
}
