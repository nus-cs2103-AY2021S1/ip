import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;

/**
 * Utility class to help write to and modify .txt files
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Appends task description to a .txt file
     *
     * @param task
     * @throws IOException
     */
    public void writeToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        if (task instanceof Todo) {
            fw.write(((Todo) task).getIdentifier() + " | " + (task.getDone() ? 1 : 0) + " | " + task.getDescription() + "\n");
        } else if (task instanceof Deadline) {
            fw.write(((Deadline) task).getIdentifier() + " | " + (task.getDone() ? 1 : 0) + " | " + task.getDescription() + "| " + ((Deadline) task).getDate() + "\n");
        } else {
            fw.write(((Event) task).getIdentifier() + " | " + (task.getDone() ? 1 : 0) + " | " + task.getDescription() + "| " + ((Event) task).getDate() + "\n");
        }
        fw.close();
    }

    /**
     * helper function to update the done status of task
     *
     * @param replaceWith
     */
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
