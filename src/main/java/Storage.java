
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    File f;

    Storage(String filename)  {
        this.f = new File(filename);
    }

    public List<String> load() throws DukeException {
        try {
            Scanner s = new Scanner(f);
            List store = new ArrayList<>();
            while (s.hasNext()) {
                store.add(s.nextLine());
            }
            return store;

        } catch (FileNotFoundException e) {
            throw new DukeException("file not found");
        }
    }

    public static void updateDatabase(List<Task> store, String filename) {
        try {
            if (store.isEmpty()) {
                writeToFile(filename, "");
            } else {
                for (int i = 0; i < store.size(); i++) {
                    if (i == 0) {
                        writeToFile(filename, store.get(i).inputStyle());
                    } else {
                        appendToFile(filename, System.lineSeparator() + store.get(i).inputStyle());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(new DukeException("empty list"));
        }
    }

    static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

}
