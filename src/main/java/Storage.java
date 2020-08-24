import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileLockInterruptionException;
import java.util.ArrayList;

public class Storage {
    /** The default file path if the user does not provide the file path.**/
    private static final String DEFAULT_FILE_PATH = "data/tasks.txt";
    private String filePath;
    private String directoryPath;

    public Storage(String filePath, String directoryPath) {
        try {
            File directory = new File(directoryPath);
            if (!isValidFilePath(filePath)) {
                throw new InvalidFilePathException("The storage file should end with '.txt'");
            } else {
                if (!directory.exists()) {
                    directory.mkdir();
                    throw new InvalidFilePathException("The directory does not exist at the start."
                            + " a directory to store the data would be created.");
                }
                this.filePath = filePath;
                this.directoryPath = directoryPath;
            }
        } catch (InvalidFilePathException e) {
                e.getMessage();
        }
    }


    private static boolean isValidFilePath(String filepath) {
        return filepath.endsWith(".txt");
    }

    public void read(ArrayList<Task> listOfTasks) {
        try {
            File storageFile = new File(filePath);
            //FileReader fileReader = new FileReader(storageFile);
            Scanner scanner = new Scanner(storageFile);
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] wordsParsed = currentLine.split("\\|");
                String whetherIsDone = wordsParsed[1].equals("\u2713")
                        ? "true"
                        : "false";
                switch (wordsParsed[0]) {
                case "todo":
                    Task todo = new Todo(wordsParsed[2]);
                    todo.setWhetherTaskDone(whetherIsDone);
                    listOfTasks.add(todo);
                    break;
                case "deadline":
                    Task deadline = new Deadline(wordsParsed[2], wordsParsed[3]);
                    deadline.setWhetherTaskDone(whetherIsDone);
                    listOfTasks.add(deadline);
                    break;
                case "event":
                    Task event = new Event(wordsParsed[2], wordsParsed[3]);
                    event.setWhetherTaskDone(whetherIsDone);
                    listOfTasks.add(event);
                    break;
                }
            }
            scanner.close();


        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void write( ArrayList<Task> listOfTasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : listOfTasks) {
                fileWriter.write(task.writeToFile() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }



}
