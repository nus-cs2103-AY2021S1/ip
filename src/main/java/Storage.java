import java.io.*;
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

    public TaskList getFileList() {
        Path path = Path.of(pathString);
        File savedListFile = new File(pathString);
        TaskList taskList = new TaskList();
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
                    String[] taskData = taskLine.split(" \\| ");
                    String taskType = taskData[0];
                    boolean taskIsDone = Integer.valueOf(taskData[1]) == 1;
                    String taskName = taskData[2];

                    if (!taskType.equals("T")) {
                        String taskTime = taskData[3];
                        if (taskType.equals("D")) {
                            TaskDate dueTime = DateParser.parseDateFromStorage(taskTime);
                            taskList.addTask(new Deadline(taskName, dueTime));
                        } else {
                            TaskDate startDate = DateParser.parseRangeFromStorage(taskTime, true);
                            TaskDate endDate = DateParser.parseRangeFromStorage(taskTime, false);

                            taskList.addTask(new Event(taskName, startDate, endDate));
                        }
                    } else {
                        taskList.addTask(new Todo(taskName));
                    }
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
        String stringToWrite = "";
        for (int i = 1; i <= taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            stringToWrite += task.getAbbreviatedString() + "\n";
        }

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

        //System.out.printf(horizontalLine + "     List has been saved to file.%n"  + horizontalLine);
    }
}