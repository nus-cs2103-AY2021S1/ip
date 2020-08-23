package storage;

import ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    public String filePath;

    public final static String FILEPATH = System.getProperty("user.dir") + "/duke.txt";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void load() throws IOException {
        boolean directoryExists = new File(filePath).exists();

        if (!directoryExists) {
            FileWriter fw = new FileWriter(filePath, true);
        }
    }

    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        Ui.printCommand(textToAppend);
        fw.close();
    }


}