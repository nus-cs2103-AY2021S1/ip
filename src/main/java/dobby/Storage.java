package dobby;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import dobby.task.Task;

/**
 * Reads the stored tasks at updates the storage file
 */
public class Storage {
    private final String filePath;
    private TaskList tasks;

    Storage(String filePath, TaskList tasks) {
        this.filePath = filePath;
        this.tasks = tasks;
    }

    /**
     * Reads the storage file and add tasks to the TaskList
     */
    public void readFile() {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                try {
                    this.tasks.createFromStorage(str);
                } catch (ParseException e) { // should never be called
                    Ui.reply(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            Ui.reply(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Updates current tasks to the storage file
     */
    public void rewriteFile() {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            fileWriter.flush();

            for (int i = 0; i < (this.tasks).getSize() - 1; i++) {
                Task task = (this.tasks).getTask(i);
                fileWriter.write(task.getDescription() + System.lineSeparator());
            }

            if ((this.tasks).getSize() > 0) {
                fileWriter.write(tasks.getTask((this.tasks).getSize() - 1).getDescription());
            }
            fileWriter.close();
        } catch (IOException e) {
            Ui.reply(e.getMessage());
        }
    }
}
