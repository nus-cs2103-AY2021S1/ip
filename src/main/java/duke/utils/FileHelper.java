package duke.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A helper class that contains method dealing with file operation.
 * such as reading from a file, and write to a file.
 */
public class FileHelper {

    /**
     * Reads task list from a txt file in certain format.
     * and return the data as a list of String.
     *
     * @param path the path to the folder that the file you wants to read resides in.
     * @param fileName the name of the file that you wants to read.
     * @return a list of String which every string represents a task in a format.
     */
    public static List<String> ReadFromFile(String path, String fileName) {

        List<String> data = new ArrayList<>();

        File file = new File(path + "/" + fileName);
        try {
            if (file.exists()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    data.add(line);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Saves the data in list of string to the path and file provided.
     * if there is no such directory exist, it will make the directory.
     *
     * @param path the path to the folder that the file you wants to save resides in.
     * @param fileName the name of the file that you wants to save the data to.
     * @param data the data you wish to save in the format of list of string.
     */
    public static void save(String path, String fileName, List<String> data) {

        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            FileWriter writer = new FileWriter(path + "/" + fileName);

            for (int i = 0; i < data.size(); i++) {
                writer.write(data.get(i) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
