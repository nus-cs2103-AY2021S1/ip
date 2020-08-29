import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Storage {
    private static String horizontalLine = "    ____________________________________________________________\n";

    private String pathString;

    Storage(String pathString) {
        this.pathString = pathString;
    }

    public TaskList getListFromStorage() {
        Path path = Path.of(pathString);
        File savedListFile = new File(pathString);
        TaskList taskList = new TaskList();
        Parser parser = new Parser();
        try {
            boolean doesSavedListExist = savedListFile.createNewFile();
            if (!doesSavedListExist) {
                List<String> contents = Files.readAllLines(path, StandardCharsets.UTF_8);
                if (contents.size() > 0) {
                    System.out.printf(horizontalLine + "     Found an existing list at %s%n" + horizontalLine,
                                            path);
                } else {
                    System.out.printf(horizontalLine + "     Found an existing list, but it was empty!%n" +
                                        horizontalLine);
                }

                for (String taskLine : contents) {
                    taskList.addTask(parser.parseFromStorage(taskLine));
                }
            } else {
                System.out.printf(horizontalLine + "     Existing list not found, creating new list\n" + horizontalLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (IndexOutOfBoundsException e) {
            // FOR TESTING, TO BE DISCARDED BEFORE RELEASE
            System.out.println("     Encoding error: creating new list");
            taskList = new TaskList();
            Path listFilePath = Path.of(pathString);

            try {
                new PrintWriter(pathString).close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        }

        return taskList;
    }

    public void saveListToFile(TaskList taskList) {
        String stringToWrite = taskList.getListForStorage();

        Path listFilePath = Path.of(pathString);

        try {
            new PrintWriter(pathString).close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(listFilePath, StandardCharsets.UTF_8,
                StandardOpenOption.WRITE)) {
            writer.write(stringToWrite, 0, stringToWrite.length());
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}