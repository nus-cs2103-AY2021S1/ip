import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;
    ArrayList<Task> loadedTasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.loadedTasks = new ArrayList<>();
    }

    private void writeToFile(String filePath, String textToAdd, boolean isAppending) throws IOException {
        FileWriter fw = new FileWriter(filePath, isAppending);
        fw.write(textToAdd);
        fw.close();
    }

    public ArrayList<Task> load() throws DukeException, IOException {
        try {
            File f = new File(this.filePath);
            f.getParentFile().mkdir();
            f.createNewFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String nextLine = s.nextLine();
                Scanner s2 = new Scanner(nextLine);
                String taskType = s2.next();
                boolean isDone = false;
                Task currTask;
                if (taskType.equals("T")) {
                    s2.useDelimiter(" ~ ");
                    if (s2.nextInt() == 1) {
                        isDone = true;
                    }
                    currTask = new Todo(s2.next());
                    loadedTasks.add(currTask);
                    if (isDone) {
                        currTask.markAsDone();
                    }
                } else if (taskType.equals("D")) {
                    s2.useDelimiter(" ~ ");
                    if (s2.nextInt() == 1) {
                        isDone = true;
                    }
                    currTask = new Deadline(s2.next(), s2.next());
                    loadedTasks.add(currTask);
                    if (isDone) {
                        currTask.markAsDone();
                    }
                } else {
                    s2.useDelimiter(" ~ ");
                    if (s2.nextInt() == 1) {
                        isDone = true;
                    }
                    currTask = new Event(s2.next(), s2.next());
                    loadedTasks.add(currTask);
                    if (isDone) {
                        currTask.markAsDone();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        } catch (IOException e) {
            throw new DukeException("Input-Output error");
        }
        return this.loadedTasks;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try {
            for (int i = 0; i < tasks.size(); i++) {
                if (i == 0) {
                    writeToFile(filePath, tasks.get(i).getStoringFormat() + System.lineSeparator(), false);
                } else {
                    writeToFile(filePath, tasks.get(i).getStoringFormat() + System.lineSeparator(), true);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
