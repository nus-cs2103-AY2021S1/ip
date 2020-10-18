package emily.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import emily.exception.DukeException;
import emily.task.Deadline;
import emily.task.Event;
import emily.task.Task;
import emily.task.ToDos;

/**
 * Saves and loads data
 */
public class Storage {
    private static String filepath;

    public Storage(String filepath) {
        Storage.filepath = filepath;
    }

    /**
     * Reads the information from the txt file and process into a list of task
     * Creates new txt file if file does not exist
     *
     * @return an Arraylist of Task
     * @throws DukeException invalid file
     */
    public ArrayList<Task> loadData() throws DukeException {
        ArrayList<Task> store = new ArrayList<>();

        File f = new File(filepath);
        f.getParentFile().mkdirs();
        try {
            if (f.exists()) {
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String line = sc.nextLine();
                    char type = line.charAt(0);
                    boolean isCompleted = line.charAt(1) == '1';
                    String[] temp;

                    switch (type) {
                    case 'T':
                        temp = line.split(",", 2);
                        Task t = new ToDos(temp[1]);
                        t.setHasFinished(isCompleted);
                        store.add(t);
                        break;
                    case 'D':
                        temp = line.split(",", 3);
                        Task d = new Deadline(temp[1], temp[2]);
                        d.setHasFinished(isCompleted);
                        store.add(d);
                        break;
                    case 'E':
                        temp = line.split(",", 3);
                        Task e = new Event(temp[1], temp[2]);
                        e.setHasFinished(isCompleted);
                        store.add(e);
                        break;
                    default:
                        assert false : "txt file contains invalid terms, please rewrite txt file";
                    }
                }

            } else {
                f.createNewFile();
            }
            return store;
        } catch (FileNotFoundException e) {
            throw new DukeException("file is not found");
        } catch (IOException e) {
            throw new DukeException("file already existed");
        }
    }


    /**
     * Updates the txt file when there are modifications to the task list
     *
     * @param store contains the updated list of task
     * @throws DukeException for invalid file
     */
    public void saveData(ArrayList<Task> store) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filepath);
            String txt = "";

            for (Task task : store) {
                String temp = "";
                if (task instanceof ToDos) {
                    temp = "T" + (task.isHasFinished() ? "1" : "0")
                            + "," + task.getDescription();
                } else if (task instanceof Deadline) {
                    temp = "D" + (task.isHasFinished() ? "1" : "0")
                            + "," + task.getDescription() + "," + ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    temp = "E" + (task.isHasFinished() ? "1" : "0")
                            + "," + task.getDescription() + "," + ((Event) task).getAt();
                }
                txt = txt + temp + System.lineSeparator();
            }
            fw.write(txt);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("invalid file");
        }
    }
}
