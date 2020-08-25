import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;

public class FileOperation {
    private final String filePath;

    public FileOperation() {
        this.filePath = "./data/taskList.txt";
    }

    public void readTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String lineData = bufferedReader.readLine();
            while (lineData != null) {
                String[] lineSegment = lineData.split(" \\| ");
                boolean isDone = lineSegment[1].equals("1") ? true : false;

                switch (lineSegment[0]) {
                    case "T":
                        ToDos.loadTodoTask(lineSegment[2], isDone, tasks);
                        break;
                    case "D":
                        Deadlines.loadDeadlineTask(lineSegment[2], lineSegment[3], isDone, tasks);
                        break;
                    case "E":
                        Events.loadEventTask(lineSegment[2], lineSegment[3], isDone, tasks);
                        break;
                }
                lineData = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            Warnings.invalidFileInput(e);
        }
    }

    public void writeTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileWriter fileWriter;

            fileWriter = new FileWriter(file, false);

            for (Task task : tasks) {
                fileWriter.write(task.writeToFile() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
