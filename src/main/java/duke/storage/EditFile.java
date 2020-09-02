package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import duke.exception.DukeException;
import duke.tools.FormatString;



/**
 * This class is to edit the file in
 * the Directory class.
 * It has two methods: deleteLine,
 * and setTaskDone.
 */
public class EditFile {
    private String path;

    /**
     * Initializes a EditFile object.
     *
     * @param path A string which contains
     *             the directory of the file
     *             that is to be edited.
     */
    public EditFile(String path) {
        this.path = path;
    }

    /**
     * Deletes the task recorded in the file
     * provided by the Directory class
     * with the corresponding line number.
     *
     * @param lineNum The index of task that the user want to
     *                delete.
     */
    public void deleteLine(int lineNum) {
        try {
            File fileToBeModified = new File(this.path);
            StringBuilder text = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
            int currentLineNum = 1;
            String currentLine = reader.readLine();

            while (currentLine != null) {
                if (currentLineNum != lineNum) {
                    text.append(currentLine).append("\n");
                }
                currentLineNum++;
                currentLine = reader.readLine();
            }

            FileWriter fileWriter = new FileWriter(fileToBeModified);
            fileWriter.write(text.toString());
            reader.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(DukeException.readLineException());
        }
    }

    /**
     * Clears all tasks recorded in the file
     * with directory in the FileDirectory in Directory class.
     */
    public void clearFile() {
        try {
            PrintWriter printWriter = new PrintWriter(path);
            printWriter.close();
        } catch (IOException e) {
            System.out.println(DukeException.fileException());
        }
    }

    /**
     * Sets the corresponding
     * task to be marked as done in Duke.txt.
     *
     * @param lineNum The index of task that the user want to
     *                delete.
     */
    public void setTaskDone(int lineNum) {
        try {
            File fileToBeModified = new File(this.path);
            StringBuilder text = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
            int currentLineNum = 1;
            String currentLine = reader.readLine();

            while (currentLine != null) {
                if (currentLineNum != lineNum) {
                    text.append(currentLine).append("\n");
                } else {
                    String editedLine =
                            currentLine.substring(0, 4)
                                    + FormatString.TICK.toString()
                                    + currentLine.substring(5);
                    text.append(editedLine).append("\n");
                }
                currentLineNum++;
                currentLine = reader.readLine();
            }

            FileWriter fileWriter = new FileWriter(fileToBeModified);
            fileWriter.write(text.toString());
            reader.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(DukeException.readLineException());
        }
    }
}
