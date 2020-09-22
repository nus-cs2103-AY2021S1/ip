package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * A class that defines the file path where tasks will be read from and saved.
 */
public class Storage {

    private String filePath;
    private int maxUndoFile = 4;
    private int pointer;
    private String fileName = "/Duke.txt";
    private String fileNameFront;
    private String fileNameBack;

    /**
     * Constructs a Storage object.
     *
     * @param filePath  A String of the file path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        int spiltIndex = this.fileName.indexOf('.');
        this.fileNameFront = this.filePath + this.fileName.substring(0, spiltIndex);
        this.fileNameBack = this.fileName.substring(spiltIndex);
        this.pointer = -1;
    }

    /**
     * Loads existing task list from file path, if it exists.
     *
     * @return  An Optional containing a stream of Strings of the lines in the file path, if it exists.
     * @throws FileNotFoundException  If erroneous file path is given.
     */
    public Optional<Stream<String>> load() throws IOException {
        //check Duke(maxUndoFile).txt until Duke0.txt if they exist, load the first
        for (int i = maxUndoFile; i >= 0; i--) {
            File inputFile = new File(this.fileNameFront + Integer.toString(i) + this.fileNameBack);
            if (inputFile.exists()) {
                this.pointer = i;
                FileReader fileReader = new FileReader(inputFile);
                BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
                Stream<String> saveContent = bufferedReader.lines().onClose(() -> {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        System.out.println("failed to close bufferedReader");
                        e.printStackTrace();
                    }
                });
                fileReader.close();
                return Optional.of(saveContent);
            }
        }
        //if no save file founds, returns empty
        return Optional.empty();
    }

    /**
     * Saves the given TaskList onto the file path.
     *
     * @param taskList  the TaskList to be saved.
     * @throws IOException  If the file path is erroneous.
     */
    public void save(TaskList taskList) throws IOException {
        //can make more efficient by playing with pointers by im damn lazy
        if (this.pointer == this.maxUndoFile) {
            //if max undo capacity
            //delete Duke0.txt
            File name0 = new File(this.fileNameFront + '0' + this.fileNameBack);
            if (name0.exists()) {
                name0.delete();
            }
            //rename Duke(n).txt to Duke(n-1).txt from the bottom
            for (int i = 1; i <= this.maxUndoFile; i++) {
                File oldFile = new File(this.fileNameFront + Integer.toString(i - 1) + this.fileNameBack);
                File newFile = new File(this.fileNameFront + Integer.toString(i) + this.fileNameBack);
                if (newFile.exists()) {
                    newFile.renameTo(oldFile);
                }
            }
            //save as Duke(maxUndoFile).txt
            File outputFile = new File(this.fileNameFront + Integer.toString(maxUndoFile) + this.fileNameBack);
            //pointer doesn't change
            outputFile.createNewFile();

            FileWriter writer = new FileWriter(outputFile);
            writer.write(taskList.fileText());
            writer.close();
        } else {
            //got space to store more undos
            File directory = new File(this.filePath);
            //pointer increments
            File outputFile = new File(this.fileNameFront + Integer.toString(++this.pointer) + this.fileNameBack);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            //if the file exists, delete
            if (outputFile.exists()) {
                outputFile.delete();
            }
            //make the file
            outputFile.createNewFile();

            FileWriter writer = new FileWriter(outputFile);
            writer.write(taskList.fileText());
            writer.close();
        }
    }

    /**
     * Undoes the last change to storage.
     *
     * @return boolean value if it was successful.
     */
    public boolean undo() {
        if (this.pointer <= 0) {
            //cannot undo
            return false;
        } else {
            //can undo
            //delete all save files before and incl curr pointer
            boolean successfulDeletes = true;
            for (int i = this.pointer; i <= maxUndoFile; i++) {
                File toDelete = new File(this.fileNameFront + Integer.toString(i) + this.fileNameBack);
                if (toDelete.exists()) {
                    successfulDeletes = successfulDeletes && toDelete.delete();
                }
            }
            if (successfulDeletes) {
                this.pointer--;
                return true;
            } else {
                return false;
            }
        }
    }
}
