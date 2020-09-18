package duke.component;

import duke.task.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;

/**
 * Utility class to help write to and modify .txt files
 */
public class Storage {
    String filePath;

    /**
     * Constructor for storage class with details of directory
     * to store the .txt files
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Adds task description to a .txt file
     *
     * @param task
     * @throws IOException
     */
    public void writeToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        String writtenTask = task.getIdentity() + describeTask(task);

        if (task instanceof Deadline) {
            writtenTask += ((Deadline) task).getDate();
        } else if (task instanceof Event) {
            writtenTask += ((Event) task).getDate();
        } else writtenTask += "\n"; //adds a new line
        fw.write(writtenTask);
        fw.close();
    }

    /**
     * Static method to format task to be stored in the txt file.
     * @param task
     * @return String of task formatted
     */
    public static String describeTask(Task task) {
        return " | " + (task.getIsDone() ? 1 : 0) + " | " + task.getDescription();
    }

    /**
     * Helper function to update the done status of task
     *
     * @param replaceWith
     */
    // Credits to @nicholas-gcc for understanding and help with code.
    public void replaceDone(String replaceWith) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(this.filePath));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();
            inputStr = inputStr.replace("| 0 | " + replaceWith, "| 1 | " + replaceWith);

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(filePath);
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
}
