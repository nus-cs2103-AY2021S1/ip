import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private TaskList tasks;

    Storage (String filePath, TaskList tasks) {
        this.filePath = filePath;
        this.tasks = tasks;
    }

    public void readFile () {
        try {
            File file = new File(this.filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                try {
                    (this.tasks).creatingFromStorage(str);
                } catch (ParseException e) { // should never be called
                    Ui.reply("\n    " + e.getMessage() + "\n    ");
                }
            }
        } catch (FileNotFoundException e) {
            Ui.reply("\n    " + e.getMessage() + "\n    ");
            System.exit(1);
        }
    }
    public void rewriteFile() {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.flush();
            for (int i = 0; i < (this.tasks).getSize() - 1; i++) {
                Task task = (this.tasks).getTask(i);
                fw.write(task.getDescription() + System.lineSeparator());
            }
            fw.write(tasks.getTask((this.tasks).getSize() - 1).getDescription());
            fw.close();
        } catch (IOException e) {
            Ui.reply("\n    " + e.getMessage() + "\n    ");
        }
    }
}
