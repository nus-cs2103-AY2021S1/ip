package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * Data reading and storage manager.
 */
public class Storage {

    private String filePath;

    /**
     * Creates new storage object.
     *
     * @param filePath Location of file for data reading and writing.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the data file for stored tasks.
     *
     * @return List of tasks stored in data file.
     */
    public List<Task> readFile() {

        File file = new File(filePath);
        List<Task> output = new ArrayList<>();

        try {

            if (!file.exists()) {
                return output;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {

                String[] lineArr = line.split("\\s(\\|)\\s");

                switch (lineArr[0]) {
                case "T":
                    output.add(new ToDo(lineArr[2]));
                    break;
                case "D":
                    output.add(new Deadline(lineArr[2], lineArr[3], lineArr[4]));
                    break;
                case "E":
                    output.add(new Event(lineArr[2], lineArr[3], lineArr[4]));
                    break;
                default:
                }

                if (lineArr[1].equals("1")) {
                    output.get(output.size() - 1).completeTask();
                }
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error occurred while reading data");
        }

        return output;
    }

    /**
     * Writes tasks to the data file for storage.
     *
     * @param tasks List of tasks to be stored in data file.
     */
    public void saveFile(List<Task> tasks) {

        File file = new File(filePath);

        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            tasks.forEach(task -> {
                try {
                    bw.write(task.getDataString());
                } catch (IOException e) {
                    System.out.println("Error occurred while saving data");
                }
            });

            bw.close();

        } catch (IOException e) {
            System.out.println("Error occurred while saving data");
        }
    }
}
