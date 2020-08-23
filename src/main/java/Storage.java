import java.io.*;
import java.util.ArrayList;

public class Storage {
    File savedFile;

    Storage(String filepath) {
        // Check if file exists and create file otherwise
        try {
            File savedFile = new File(filepath);
            if (!savedFile.exists()) {
                savedFile.createNewFile();
            }
            this.savedFile = savedFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a list of task details loaded from the text file that contains saved data.
     * @return A list of string arrays, each containing the details of the saved tasks.
     */

    public ArrayList<String[]> load() {
        ArrayList<String[]> taskDetails = new ArrayList<>();
        String currLine;
        // Read from file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(savedFile));
            while ((currLine = reader.readLine()) != null) {
                String[] taskArr = currLine.split("\\|");
                taskDetails.add(taskArr);
            }
        } catch (IOException exception) {
                exception.printStackTrace();
        }
        return taskDetails;
    }

    /**
     * Takes in a list of task details and saves them line-by-line to the specified text file.
     * @param tasksInfo A list of string containing the details of each task.
     */

    public void saveToFile(ArrayList<String> tasksInfo) {
        // Write to file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(savedFile));
            for (String info : tasksInfo) {
                writer.write(info);
                writer.newLine();
                writer.flush();
            }
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
