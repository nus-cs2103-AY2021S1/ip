package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Deals with the manipulation of loading and saving data.
 */
public class Storage {

    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    static void reset () {
        try {
            FileWriter writer = new FileWriter(new File("text-ui-test/ACTUAL.TXT"), false);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void writeInitial (String input) {
        try {
            FileWriter writer = new FileWriter(new File("text-ui-test/ACTUAL.TXT"), true);
            writer.write(input);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void writeFinally (String input) {
        try {
            FileWriter writer = new FileWriter(new File("text-ui-test/ACTUAL.TXT"), true);
            writer.write("\n" + input);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
