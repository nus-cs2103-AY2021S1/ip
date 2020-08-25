import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Storage {

    public static final String FILENAME = "./data/duke.txt";
    public static final String DIRECTORY_NAME = "./data/";


    public Storage() {
    }

    public static void save(ArrayList<Task> list) {
        try {
            FileWriter writer = new FileWriter(FILENAME);
            for(Task str: list) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();
        } catch(IOException e) {
            System.out.println(e);
        }

    }

}