import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    protected Storage(String filePath) {
        this.filePath = filePath;
    }

    protected Scanner load() throws FileNotFoundException {
        File file = new File(filePath);
        return new Scanner(file);
    }

    protected void saveTasks(String tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(tasks);
        fw.close();
    }

}
