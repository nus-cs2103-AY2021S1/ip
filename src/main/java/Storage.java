import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the Storage class. It is a class consists of methods
 * related to storing the list of tasks and reading the file when the
 * users just started it and save the file when the user quit.
 */
public class Storage {
    private static String filePath;
    private static File file;
    private static List<Task> listOfContents = new ArrayList<Task>();


    /**
     * Constructor for Storage class.
     *
     * @param filePath The file path.
     */
    Storage (String filePath) {
        this.file = file;
        this.filePath = filePath;
    }

    /**
     * read the content in the file name bill.txt
     * @param filePath The file path to be read the file.
     */
    protected static String checkFile(String filePath) throws FileNotFoundException {

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();

        java.nio.file.Path path = java.nio.file.Paths.get(s, "data");
        boolean directoryExists = java.nio.file.Files.exists(path);
        if (!directoryExists) {
            File dir = new File(String.valueOf(path));
            dir.mkdir();
        }

        Path getFilePath = Paths.get(s, "data", "bill.txt");
        filePath = String.valueOf(getFilePath);
        System.out.println(filePath);
        File file = new File(filePath);
        String message = "\n I will open up the file now \n";
        try {
            if (!file.createNewFile()) {
                message = message + read(file);
            } else {
                message = message + "The file is not exist, i will create a file for the Tasks now \n";
                try {
                    FileWriter fileWriter = new FileWriter(filePath);
                    fileWriter.close();
                    message = message + read(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        message = message + "\n remember to use \"save\" command to save the tasks you have added";
        return message;
    }

    /**
     * read the content in the file name bill.txt
     * @param file The file path to be read the file.
     */
    public static String read(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        if (!scanner.hasNextLine()) {
            String message = "\n There is no tasks in the list yet, please add in the tasks now \n";
            return message;
        } else {
            String msg = "\n The Tasks are shown here: \n";
            List<Task> listOfContent = new ArrayList<Task>();
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                boolean containCross = currentLine.contains("\u2718");
                boolean containTick = currentLine.contains("\u2713");
                boolean isDone = true;
                if (containCross) {
                    isDone = false;
                }
                if (containTick) {
                    isDone = true;
                }
                Task task = new Task(currentLine, isDone);
                listOfContent.add(task);
                msg = msg + currentLine + "\n";
            }
            listOfContents = listOfContent;
            return msg;
        }
    }

    /**
     * Returns the list of tasks that the users have stored in the file.
     *
     * @return The list of tasks that the users have stored in the file.
     */
    public static List<Task> getListOfContents() {
        return listOfContents;
    }

    /**
     * Stores the updated Lists that contain the tasks to bill.txt.
     * @param listOfContent TaskList that manages tasks.
     */
    public static void write(TaskList listOfContent) {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        Path getFilePath = Paths.get(s, "data", "bill.txt");
        filePath = String.valueOf(getFilePath);
        try {
            FileWriter fileWriter = new FileWriter(filePath, false);
            for (Task task : listOfContent.getTheList()) {
                fileWriter.write(task.timeConverted() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
