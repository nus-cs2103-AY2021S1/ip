import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    //abstracts the file loading/checking process
    private File storeFile;
    Storage(String filePath) throws IOException {
        //check if file exists, else create file and directories
        Path testPath = Paths.get(filePath);
        File testFile = new File(filePath);
        if(testFile.exists()) {
            this.storeFile = testFile;
        } else {
            Files.createDirectories(testPath.getParent());
            Files.createFile(testPath);
            this.storeFile = new File(filePath);
        }
    }

    File load() {
        return this.storeFile;
    }

    public void saveFile(TaskList tasks) throws IOException {
        FileOutputStream fos = new FileOutputStream(this.storeFile);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (Task t : tasks.taskList) {
            String line = t.saveRep;
            bw.write(line);
            bw.newLine();
        }
        bw.close();
    }
}


