package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import duke_exceptions.*;

/**
 * Represents a storage and deals with loading tasks from
 * the file and saving tasks in the file
 */
public class Storage {
    private File file;
    Storage(Path path) {
        this.file = new File(path.toString());
    }

    public void writeToList(TaskList lst) throws IllegalTaskTypeException {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                lst.add(Converter.add(task));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void writeToFile(TaskList lst) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        String content;
        for (int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            content = task.getPureTypeLetter() + " ; " + task.getStatusNum()
                    + " ; " + task.getStoreMessage() + "\n";
            fileWriter.write(content);
        }
        fileWriter.close();
    }

}
