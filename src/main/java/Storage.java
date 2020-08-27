import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    // Loop through every task and transform it into a string file format
    public String listToString(ArrayList<Task> taskList) {
        String taskListStr = "";
        for (Task t : taskList) {
            taskListStr += t.toStringFileFormat() + "\n";
        }
        return taskListStr;
    }

    public void saveToFile(ArrayList<Task> userTasks) {
        // Check if data folder exists, if not create
        Path folderPath = Paths.get("..", "..", "..", "data");
        if (!Files.exists(folderPath)) {
            File folderDir = new File(folderPath.toString());
            folderDir.mkdir();
        }

        // Get OS-independent file path to text file
        String filePath = Paths.get("..", "..", "..", "data", "Tasklist.txt")
                .toString();

        try {
            FileWriter myFile = new FileWriter(filePath);
            myFile.write(listToString(userTasks)); // Output is already all tasks in a string
            myFile.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Task> readFromFile() {
        // Check if data folder exists, if not create
        Path folderPath = Paths.get("..", "..", "..", "data");
        if (!Files.exists(folderPath)) {
            File folderDir = new File(folderPath.toString());
            folderDir.mkdir();
        }

        // Initialise ArrayList to return
        ArrayList<Task> savedTasks = new ArrayList<Task>();

        // Check if file exists, if return empty list
        Path filePath = Paths.get("..", "..", "..", "data", "Tasklist.txt");
        if (!Files.exists(filePath)) {
            return savedTasks;
        }

        try {
            File myFile = new File(filePath.toString());
            Scanner taskReader = new Scanner(myFile);

            // Keep reading new line until file end
            while (taskReader.hasNextLine()) {
                String taskString = taskReader.nextLine();

                // Only work with non empty lines
                if (taskString != "") {
                    switch (taskString.charAt(1)) {
                    case 'T':
                        boolean isDone = taskString.split("  ")[0]
                                .substring(3)
                                .equals("[Done]");
                        String description = " " + taskString.split("  ")[1];
                        Task t = new ToDo(description);
                        if (isDone) {
                            t.setDone();
                        }
                        savedTasks.add(t);
                        break;
                    case 'D':
                        isDone = taskString.split("  ")[0]
                                .substring(3)
                                .equals("[Done]");
                        description = " " + taskString.split("  ")[1]
                                .split("\\s[(]by:\\s")[0];
                        String by = taskString.split("  ")[1]
                                .split("\\s[(]by:\\s")[1];
                        by = by.substring(0, by.length() - 1); // remove parentheses at the end
                        Deadline d = new Deadline(description, by);
                        if (isDone) {
                            d.setDone();
                        }
                        savedTasks.add(d);
                        break;
                    case 'E':
                        isDone = taskString.split("  ")[0]
                                .substring(3)
                                .equals("[Done]");
                        String[] stringSplit = taskString.split("  ")[1]
                                .split("\\s[(]at:\\s");
                        description = " " + stringSplit[0];
                        String at = stringSplit[1].split(" ")[0];
                        String timeRange = stringSplit[1].split(" ")[1];
                        timeRange = timeRange.substring(0, timeRange.length() - 1); // remove parentheses at the end
                        Event e = new Event(description, at, timeRange);
                        if (isDone) {
                            e.setDone();
                        }
                        savedTasks.add(e);
                        break;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return savedTasks;
    }
}
