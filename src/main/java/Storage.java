import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String dest;

    public Storage(String dest) {
        this.dest = dest;
    }

    public TaskList loadFile() throws IOException {
        try {
            TaskList tasks = new TaskList();
            File file = new File(dest);
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                tasks.add(Parser.parseSavedTask(scanner.nextLine()));
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Could not find file after creating.");
        } catch (IOException e) {
            throw new IOException("Error creating file.");
        }
    }

    public void writeFile(TaskList taskList) throws FileNotFoundException {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dest));
            for (Task task: taskList) {
                bufferedWriter.write(task.toSaveString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            throw new FileNotFoundException("Couldn't find file");
        }
    }
}
