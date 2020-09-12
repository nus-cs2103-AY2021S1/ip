import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    //abstracts the file loading/checking process
    private File storeFile;
    private File pointerFile;
    static final String noPath = "nopath";

    Storage(String filePath) throws IOException {
        //read data file path from filePath file
        Path testPath = Paths.get(filePath);
        File testFile = new File(filePath);
        if (testFile.exists()) {
            this.pointerFile  = testFile;
        } else {
            Files.createDirectories(testPath.getParent());
            Files.createFile(testPath);
            this.pointerFile  = new File(filePath);
        }
        BufferedReader br = new BufferedReader(new FileReader(this.pointerFile));
        String dataFilePath = br.readLine();
        loadFile(dataFilePath);
    }

    /**
     * Returns the loaded storage file
     * @return File object
     */
    File load() {
        return this.storeFile;
    }

    public void loadFile(String filePath) throws IOException {
        if (filePath == null) {
            filePath = "data/data.txt";
        }
        Path testPath = Paths.get(filePath);
        File testFile = new File(filePath);
        if (testFile.exists()) {
            this.storeFile = testFile;
        } else {
            Files.createDirectories(testPath.getParent());
            Files.createFile(testPath);
            this.storeFile = new File(filePath);
        }
        this.updatePointerFile(filePath);
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

    public static String promptForFile() {
        JFileChooser fc = new JFileChooser("./data");
        fc.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
        int rtnValue = fc.showOpenDialog(null);
        if (rtnValue == JFileChooser.APPROVE_OPTION) {
            String filePath = fc.getSelectedFile().getAbsolutePath();
            return filePath;
        } else {
            return noPath;
        }
    }

    private void updatePointerFile(String path) throws IOException {
        this.pointerFile = new File(this.pointerFile.getAbsolutePath());
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.pointerFile));
        bw.write(path);
        bw.close();
    }

}


