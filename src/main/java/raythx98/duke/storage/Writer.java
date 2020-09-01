package raythx98.duke.storage;

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
            File file = new File("data");
            //Creating the directory
            boolean bool = file.mkdir();
            if (bool) {
                System.out.println("Directory created successfully");
            } else {
                System.out.println("Sorry couldn’t create specified directory");
            }
            overwrite(filepath, input);
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
            File file = new File("data");
            //Creating the directory
            boolean bool = file.mkdir();
            if (bool) {
                System.out.println("Directory created successfully");
            } else {
                System.out.println("Sorry couldn’t create specified directory");
            }
            overwrite(filepath, input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
