import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Storage {
    //abstracts the file loading/checking process
    static final String NOPATH = "nopath";
    private File storeFile;
    private File pointerFile;

    Storage(String filePath) throws IOException {
        //read data file path from filePath file
        Path testPath = Paths.get(filePath);
        File testFile = new File(filePath);
        if (testFile.exists()) {
            this.pointerFile = testFile;
        } else {
            Files.createDirectories(testPath.getParent());
            Files.createFile(testPath);
            this.pointerFile = new File(filePath);
        }
        BufferedReader br = new BufferedReader(new FileReader(this.pointerFile));
        String dataFilePath = br.readLine();
        loadFile(dataFilePath);
    }

    /**
     * Returns the loaded storage File
     * @return File of storage
     */
    File load() {
        return this.storeFile;
    }

    /**
     * Loads data file
     * @param filePath path of file to be loaded
     * @throws IOException exception thrown when loading a file
     */
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

    /**
     * Saves data file
     * @param tasks Task list to be saveed to file
     * @throws IOException exception thrown when saving a file
     */
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

    /**
     * Prompts the user to choose a storage file
     * @return String path of the file chosen
     */
    public static String promptForFile() {
        JFileChooser fc = new JFileChooser("./data");
        fc.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
        int rtnValue = fc.showOpenDialog(null);
        if (rtnValue == JFileChooser.APPROVE_OPTION) {
            String filePath = fc.getSelectedFile().getAbsolutePath();
            return filePath;
        } else {
            return NOPATH;
        }
    }

    private void updatePointerFile(String path) throws IOException {
        this.pointerFile = new File(this.pointerFile.getAbsolutePath());
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.pointerFile));
        bw.write(path);
        bw.close();
    }

}


