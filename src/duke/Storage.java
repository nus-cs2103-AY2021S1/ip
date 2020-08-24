package duke;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList tasks) {
        try {
            String fileData = tasks.getAllTasksPlainText();
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(fileData);
            fw.close();
        } catch (IOException e) {
            System.out.println("An IO exception has occurred writing to ." + this.filePath);
        }
    }

    public void delete() {
        File fw = new File(this.filePath);
        System.out.println(fw.delete());
    }

    public String[] load() throws DukeException {
        try {
            File file = new File(this.filePath);

            // creates data directory if it does not exist
            file.getParentFile().mkdirs();

            // creates duke.txt if it does not exist
            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner s = new Scanner(file);
            ArrayList<String> fileData = new ArrayList<>();

            while (s.hasNext()) {
                String plainText = s.nextLine();
                fileData.add(plainText);
            }

            String[] strAr = new String[fileData.size()];
            return fileData.toArray(strAr);
        } catch (IOException e) {
            throw new DukeException("File could not be found");
        }
    }
}
