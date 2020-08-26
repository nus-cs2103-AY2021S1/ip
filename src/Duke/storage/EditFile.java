package Duke.storage;

import Duke.exception.DukeException;
import Duke.main.Status;

import java.io.*;

/**
 * This class is to edit the Duke.txt
 * in ../data.
 * It has two methods: deleteLine,
 * and setTaskDone.
 */
public class EditFile {
    private String path;

    /**
     * Initialize a EditFile object.
     *
     * @param path A string which contains
     *             the directory of the file
     *             that is to be edited.
     */
    public EditFile(String path) {
        this.path = path;
    }

    /**
     * This method is to delete the task recorded
     * in Duke.txt with the corresponding line number.
     *
     * @param lineNum The index of task that the user want to
     *                delete.
     */
    public void deleteLine(int lineNum) {
        try {
            File fileToBeModified = new File(this.path);
            String text = "";
            BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
            int currentLineNum = 1;
            String currentLine = reader.readLine();

            while (currentLine != null) {
                if (currentLineNum != lineNum) {
                    text += currentLine + "\n";
                }
                currentLineNum++;
                currentLine = reader.readLine();
            }

            FileWriter fileWriter = new FileWriter(fileToBeModified);
            fileWriter.write(text);
            reader.close();
            fileWriter.close();
        } catch (IOException e) {
            DukeException.ReadLineException();
        }
    }

    /**
     * This method is to set the corresponding
     * task to be marked as done in Duke.txt.
     *
     * @param lineNum The index of task that the user want to
     *                delete.
     */
    public void setTaskDone(int lineNum) {
        try {
            File fileToBeModified = new File(this.path);
            String text = "";
            BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
            int currentLineNum = 1;
            String currentLine = reader.readLine();

            while (currentLine != null) {
                if (currentLineNum != lineNum) {
                    text += currentLine + "\n";
                } else {
                    String editedLine =
                            currentLine.substring(0, 4)
                                    + Status.FINISHED.toString()
                                    + currentLine.substring(5);
                    text += editedLine + "\n";
                }
                currentLineNum++;
                currentLine = reader.readLine();
            }

            FileWriter fileWriter = new FileWriter(fileToBeModified);
            fileWriter.write(text);
            reader.close();
            fileWriter.close();
        } catch (IOException e) {
            DukeException.ReadLineException();
        }
    }
}
