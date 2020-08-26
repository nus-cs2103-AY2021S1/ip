import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static void writeToFile(String file, TaskManager tm) throws IOException {

        FileWriter writer = new FileWriter(file);
        ArrayList<Task> taskList = tm.getTaskList();

        for (int i = 0; i < taskList.size(); i++) {

            if (taskList.get(i) instanceof Todo) {
                writer.write("T ## " + (taskList.get(i).getDone() ? 1 : 0) + " ## " + taskList.get(i).getDescription() + "\n");
            }

            if (taskList.get(i) instanceof Deadline) {
                writer.write("D ## " + (taskList.get(i).getDone() ? 1 : 0) + " ## " + ((Deadline) taskList.get(i)).getDescription() + " ## " + ((Deadline) taskList.get(i)).getDate() + "\n");
            }

            if (taskList.get(i) instanceof Event) {
                writer.write("E ## " + (taskList.get(i).getDone() ? 1 : 0) + " ## " + ((Event) taskList.get(i)).getDescription() + " ## " + ((Event) taskList.get(i)).getDate() + " " + ((Event) taskList.get(i)).getTime() + "\n");
            }
        }
        writer.close();
    }

    public static String getInputLine(Task task) {
        if (task instanceof Todo) {
            return "T ## " + (task.getDone() ? 1 : 0) + " ## " + task.getDescription() + "\n";
        } else if (task instanceof Deadline) {
            return "D ## " + (task.getDone() ? 1 : 0) + " ## " + ((Deadline) task).getDescription() + " ## " + "\n";
        } else {
            return "E ## " + (task.getDone() ? 1 : 0) + " ## " + ((Event) task).getDescription() + " ## " + ((Event) task).getTime() + "\n";
        }
    }

    public static void replaceDone(String filePath, String replaceWith) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(filePath));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();

            inputStr = inputStr.replace("## 0 ## " + replaceWith, "## 1 ## " + replaceWith);

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(filePath);
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }

    public static List<String> readSavedFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            return Files.readAllLines(Paths.get(fileName));
        } else {
            return null;
        }
    }
}