import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Storage {
    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String load() throws FileNotFoundException {
        try {
            return FileReading.printFileContents(filePath);
        } catch (FileNotFoundException e) { // if the required file/path directory is not yet created
            System.out.println("Creating the storage file...");

            File f = new File(filePath);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs(); //create directory if not created
            }

            if (!f.exists()) {
                try {
                    f.createNewFile(); // create file if not created
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            // read the file again
            try {
                return FileReading.printFileContents(filePath);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return FileReading.printFileContents(filePath);
    }

    public static void updateTasks(int count, List<Task> list, String filePath) {
        String output = "";
        for (int i = 1; i < count + 1; i++) {
            Task cur = list.get(i - 1);
            String currentTask = "" + i + "." + cur + "\n";
            output = output + currentTask;
        }
        // System.out.println(output);
        try {
            FileWriting.writeToFile(filePath, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
