import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileManager{

    public static void add(String location, String text) throws IOException {
        FileWriter f = new FileWriter(location,true);
        f.write(text);
        f.write(System.lineSeparator());
        f.close();
    }
    public static void edit(String location, ArrayList<task> store) throws IOException {
        FileWriter fw = new FileWriter(location);
        for(task i : store){
            fw.append(i.read());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
