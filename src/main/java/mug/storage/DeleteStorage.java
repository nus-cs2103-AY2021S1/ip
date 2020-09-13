package mug.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import mug.mugexception.MugException;

/**
 * Delete task from the local storage
 */
public class DeleteStorage extends Storage {

    /**
     * Constructs a DeleteStorage object with given path.
     *
     * @param filepath File's path
     */
    public DeleteStorage(String filepath) {
        super(filepath);
    }

    /**
     * Deletes Task from local file.
     *
     * @param taskId Task index
     * @throws MugException When MugException cause by other method.
     */
    public void deleteTask(int taskId) throws MugException {
        String tempFile = "deleteTemp.txt";
        File oldFile = new File(super.filepath);
        File newFile = new File(tempFile);
        int taskTrack = 0;

        try {
            // writer
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            // reader
            FileReader fr = new FileReader(super.filepath);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            while (Optional.ofNullable(line).isPresent()) {
                taskTrack++;
                if (taskTrack != taskId) {
                    pw.println(line);
                } else {
                    writeUndoRecord(Action.DELETE, line, taskId);
                }
                line = br.readLine();
            }

            br.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File renameFile = new File(super.filepath);
            newFile.renameTo(renameFile);
        } catch (IOException ex) {
            throw new MugException("Something went wrong. Mug fail to delete the task :_:");
        }
    }
}
