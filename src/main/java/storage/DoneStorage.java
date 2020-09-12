package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import mugexception.MugException;

/**
 * Marks the task Done
 */
public class DoneStorage extends Storage {

    /**
     * Constructs a DoneStorage object with given path.
     *
     * @param filepath File's path
     */
    public DoneStorage(String filepath) {
        super(filepath);
    }

    /**
     * Marks Task done in local file.
     *
     * @param taskId Task index.
     * @throws MugException When MugException cause by other method.
     */
    public void doneTask(int taskId) throws MugException {
        String tempFile = "doneTemp.txt";
        File oldFile = new File(this.filepath);
        File newFile = new File(tempFile);
        int taskTrack = 0;

        try {
            // writer
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            // reader
            FileReader fr = new FileReader(this.filepath);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            while (Optional.ofNullable(line).isPresent()) {
                taskTrack++;
                if (taskTrack != taskId) {
                    pw.println(line);
                } else {
                    writeUndoRecord(Action.DONE, line, taskId);
                    String[] newLine = line.split("[|]", 3);
                    pw.println(newLine[0] + "|" + 1 + "|" + newLine[2]);
                }
                line = br.readLine();
            }

            br.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File renameFile = new File(this.filepath);
            newFile.renameTo(renameFile);
        } catch (FileNotFoundException e) {
            throw new MugException("File not found");
        } catch (IOException e) {
            throw new MugException("Something went wrong");
        }
    }
}
