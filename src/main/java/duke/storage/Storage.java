package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents a storage for the data (TaskList) of Duke program.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void readFileAndUpdateList(Scanner sc, List<Task> list) {
        while (sc.hasNext()) {
            String[] data = sc.nextLine().split("/");
            String taskType = data[0];
            boolean status = data[1].equals("1");
            String description = data[2];
            String additional;

            switch (taskType) {
            case "t":
                list.add(new ToDo(description, status));
                break;
            case "d":
                additional = data[3];
                list.add(new Deadline(description, additional, status));
                break;
            case "e":
                additional = data[3];
                list.add(new Event(description, additional, status));
                break;
            default:
                break;
            }
        }
    }

    /**
     * Return the list of tasks previously saved in the filepath specified.
     *
     * @return list of tasks
     */
    public List<Task> load() {
        List<Task> list = new ArrayList<>();

        try {
            File file = new File(this.filePath);

            if (file.exists()) {
                Scanner sc = new Scanner(file);

                readFileAndUpdateList(sc, list);
            } else {
                String[] path = filePath.split("/");
                String[] dirPath = new String[path.length - 1];

                for (int i = 0; i < path.length - 1; i++) {
                    dirPath[i] = path[i];
                }

                File dir = new File(String.join("/", dirPath));
                dir.mkdir();
                file.createNewFile();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not exists" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }

        return list;
    }

    /**
     * Update the storage with the given list of tasks.
     *
     * @param list list of tasks
     */
    public void update(List<Task> list) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            StringBuilder textToAdd = new StringBuilder();

            for (Task t : list) {
                String data = t.getData();
                textToAdd.append(data).append("\n");
            }

            fw.write(textToAdd.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }
}
