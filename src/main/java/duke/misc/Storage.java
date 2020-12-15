package duke.misc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import duke.task.Task;

public class Storage {
    /**
     * Reads the storage.txt created in ./tmp/data, or create a new one if the file cannot be found.
     *
     * @return List of task in the file. Each task is represented with a list of tokens.
     */
    public static List<List<String>> readFile() {
        List<List<String>> out = new ArrayList<>();
        File dir = new File("./tmp/data");
        dir.mkdirs();
        File f = new File(dir, "storage.txt");
        try {
            f.createNewFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] tokens = s.nextLine().split("%%%");
                List<String> tokenss = new ArrayList<>(Arrays.asList(tokens));
                out.add(tokenss);
            }
            return out;
        } catch (IOException e) {
            throw new Error("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Overwrites the file ./tmp/data/storage.txt with information from the current list of tasks.
     *
     * @param database the current list of tasks
     */
    public static void writeFile(List<Task> database) {
        assert database != null: "Storage.writeFile(): database cannot be null";
        File dir = new File("./tmp/data");
        try {
            FileWriter fw = new FileWriter(new File(dir, "storage.txt"));
            for (Task task : database) {
                assert task != null: "Storage.writeFile(): task to be added cannot be null";
                fw.write(task.serialize() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new Error("Something went wrong: " + e.getMessage());
        }
    }
}
