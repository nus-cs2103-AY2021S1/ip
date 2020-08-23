import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String filePath;
    File file;

    Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    List<String> readFile() {
        List<String> items = new ArrayList<>();
        if (!this.file.exists()) {
            return items; //empty list
        }
        try {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                String task = s.nextLine();
                items.add(task);
            }
            return items;
        } catch (FileNotFoundException e) {
            return items; //empty list
        }
    }

    boolean updateFile(List<String> sentences) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            boolean hasError = false;
            for (String sentence : sentences) {
                try {
                    fw.write(sentence + System.lineSeparator());
                } catch (IOException e) {
                    System.out.println(e);
                    hasError = true;
                }
            }
            fw.close();
            return !hasError; //return true if operation succeeded
        } catch (IOException e) {
            return false;
        }
    }
}
