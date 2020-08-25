import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    public final String dir;

    WriteFile(String string) {
        dir = string;
    }

    void reset() {
        try {
            FileWriter writer = new FileWriter(new File(dir), false);
            writer.write("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("No such file exists " + dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void write(String input) {
        try {
            FileWriter writer = new FileWriter(new File(dir), true);
            writer.write(input);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("No such file exists " + dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void lineBreak() {
        try {
            FileWriter writer = new FileWriter(new File(dir), true);
            writer.write("\n");
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("No such file exists " + dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
