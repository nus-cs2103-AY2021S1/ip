package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    public static void overwrite(String filepath, String input) {
        try {
            FileWriter writer = new FileWriter(new File(filepath), false);
            writer.write(input);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("No such file exists " + filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeOn(String filepath, String input) {
        try {
            FileWriter writer = new FileWriter(new File(filepath), true);
            writer.write(input);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("No such file exists " + filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
