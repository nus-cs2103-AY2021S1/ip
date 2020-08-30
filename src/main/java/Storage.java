import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    //abstracts the file loading/checking process
    private File storeFile;

    Storage(String filePath) throws IOException {
        //check if file exists, else create file and directories
        Path testPath = Paths.get(filePath);
        File testFile = new File(filePath);
        if (testFile.exists()) {
            this.storeFile = testFile;
        } else {
            Files.createDirectories(testPath.getParent());
            Files.createFile(testPath);
            this.storeFile = new File(filePath);
        }
    }

    /**
     * Returns the loaded storage file
     * @return File object
     */

    File load() {
        return this.storeFile;
    }

    public void saveFile(TaskList tasks) throws IOException {
        FileOutputStream fos = new FileOutputStream(this.storeFile);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (Task t : tasks.getTasks()) {
            String line = t.getSaveRep();
            bw.write(line);
            bw.newLine();
        }
        bw.close();
    }
}


