import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {

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

    public static void main(String[] args) {
    }
}
