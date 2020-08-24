import java.io.File;
import java.io.IOException;

public class Storage {
    final String storageFolder;
    final String storageFile;
    File file;

    public Storage() {
        storageFolder = "data";
        storageFile = "duke.txt";
        file = new File(storageFolder);
    }

    void loadFile() {
        // check if folder exists
        if (!file.exists()) {
            System.out.println("Storage folder does not exist yet, creating Storage folder");
            file.mkdir(); // create the data directory
        }

        file = new File(file, storageFile); // update file path to include duke.txt
        // check if file exists
        if (!file.exists()) {
            try {
                System.out.println("Creating storage file");
                file.createNewFile(); // create file
            } catch (IOException e) {
                System.out.println();
            }
        }
    }
}
