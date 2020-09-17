package duke;

import duke.exceptions.IllegalTaskTypeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Represents a storage and deals with loading tasks from
 * the file and saving tasks in the file.
 */

public class Storage {
    private File file;
    Storage(Path path) {
        this.file = new File(path.toString());
    }

    /**
     * Writes content of saved file to task list (and store extra information).
     * @param lst the task list to be written to
     */
    public void writeToList(TaskList lst) throws IOException {
        try {
            if (!file.createNewFile()) {
                Scanner scanner = new Scanner(file);
                // a pure number stored in the saved file, guaranteed to be there
                String tempNumberOfDoneTasks = scanner.nextLine();
                TaskList.setNumberOfDoneTasks(Integer.parseInt(
                        tempNumberOfDoneTasks));

                // a date stored in the saved file, guaranteed to be there
                String tempLastLoginDate = scanner.nextLine();
                TaskList.setLastLoginDate(LocalDate.parse(tempLastLoginDate));

                while (scanner.hasNextLine()) {
                    String task = scanner.nextLine();
                    lst.add(Converter.add(task));
                }
                scanner.close();
            }
        } catch (IllegalTaskTypeException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Writes content of task list to saved file (and store extra information).
     * @param lst the task list to be written from
     */
    public void writeToFile(TaskList lst) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        String content;
        fileWriter.write(TaskList.getNumberOfDoneTasks() + "\n");
        fileWriter.write(LocalDate.now() + "\n");
        for (int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            content = task.getPureTypeLetter() + " ; " + task.getStatusNum()
                    + " ; " + task.getStoreMessage() + "\n";
            fileWriter.write(content);
        }
        fileWriter.close();
    }

}
