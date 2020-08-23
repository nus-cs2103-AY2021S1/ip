package duke.utility;

import duke.exception.DukeException;
import duke.exception.FailToReadFileException;
import duke.task.Task;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private Path dataPath;
    private Path storagePath;

    public Storage() {
        this.dataPath = Paths.get("data");
        this.storagePath = Paths.get("data", "storage.txt");
    }

    public void saveTaskToFile(Task task) throws DukeException {
        try {
            FileWriter fw = new FileWriter(storagePath.toString(), true);

            String toBeAppend = Parser.parseForSave(task);
            fw.write(toBeAppend);
            fw.close();
        } catch (IOException e) {
            throw new FailToReadFileException();
        }
    }

    public void changeTaskInFile(int line) throws DukeException {
        try {
            StringBuilder sb = new StringBuilder();
            File file = new File(storagePath.toString());

            Scanner sc = new Scanner(file);

            int count = 1;
            while (sc.hasNext()) {
                String taskLine = sc.nextLine() + "\n";

                if (count == line) {
                    if (taskLine.charAt(4) == '0') {
                        taskLine = taskLine.replaceFirst("0", "1");
                    }
                }

                sb.append(taskLine);
                count++;
            }

            String tobeWritten = sb.toString();
            FileWriter fw = new FileWriter(storagePath.toString());

            fw.write(tobeWritten);
            fw.close();
        } catch (IOException e) {
            throw new FailToReadFileException();
        }
    }

    public void deleteTaskInFile(int line) throws DukeException {
        try {
            StringBuilder sb = new StringBuilder();
            File file = new File(storagePath.toString());

            Scanner sc = new Scanner(file);

            int count = 1;
            while (sc.hasNext()) {
                String taskLine = sc.nextLine() + "\n";

                if (count != line) {
                    sb.append(taskLine);
                }
                count++;
            }

            String tobeWritten = sb.toString();
            FileWriter fw = new FileWriter(storagePath.toString());

            fw.write(tobeWritten);
            fw.close();
        } catch (IOException e) {
            throw new FailToReadFileException();
        }
    }

    private void createStorageFile() throws DukeException {
        try {
            File dataFile = new File(dataPath.toString());

            if (!dataFile.exists()) {
                dataFile.mkdir();
            }

            Path storagePath = Paths.get("data", "storage.txt");
            File storageFile = new File(storagePath.toString());


            if (!storageFile.exists()) {
                storageFile.createNewFile();
            }
        } catch (IOException e) {
            throw new FailToReadFileException();
        }
    }

    public List<Task> load() throws DukeException {
        try {
            createStorageFile();
            File file = new File(storagePath.toString());

            Scanner sc = new Scanner(file);

            List<Task> tasks = new ArrayList<>();

            while (sc.hasNext()) {
                String taskString = sc.nextLine();
                Task taskInFile = Parser.parseForReadingFile(taskString);
                tasks.add(taskInFile);
            }

            return tasks;
        } catch (IOException e) {
            throw new FailToReadFileException();
        }
    }
}
