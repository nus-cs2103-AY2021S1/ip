package raythx.grandma.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    /**
     * Overwrites the current filepath.
     *
     * @param filepath path of file.
     * @param input the content to be overwritten with.
     */
    public static void overwrite(String filepath, String input) {
        try {
            FileWriter writer = new FileWriter(new File(filepath), false);
            writer.write(input);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("directory not found");
            File file = new File("data");
            boolean fileCreated = file.mkdir();
            if (fileCreated) {
                overwrite(filepath, input);
            }
        } catch (IOException e) {
            System.out.println("directory not found");
            e.printStackTrace();
        }
    }

    /**
     * Appends on the current filepath.
     *
     * @param filepath path of file.
     * @param input the content to append.
     */
    public static void writeOn(String filepath, String input) {
        try {
            FileWriter writer = new FileWriter(new File(filepath), true);
            writer.write(input);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("directory not found");
            File file = new File("data");
            boolean fileCreated = file.mkdir();
            if (fileCreated) {
                overwrite(filepath, input);
            }
        } catch (IOException e) {
            System.out.println("directory not found");
            e.printStackTrace();
        }
    }
}
