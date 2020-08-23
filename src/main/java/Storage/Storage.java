package Storage;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {

    private String fileName;
    private boolean appendToFile = false;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public Storage(String fileName, boolean appendToFile) {
        this.fileName = fileName;
        this.appendToFile = appendToFile;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> taskList= new ArrayList<>();
        try {
            for (int i = 1; i <= getNumOfTasks(); i++) {
                String taskLine = printLine(i);
                String[] taskInfo = taskLine.trim().split(" [|] ");
                String taskType = taskInfo[0];
                boolean isDone = taskInfo[1].equals(String.valueOf(1)) ? true : false;
                String taskName = taskInfo[2];
                Task taskToAdd;
                switch (taskType) {
                    case "T":
                        taskToAdd = new Todo(taskName);
                        if (isDone) {
                            taskToAdd.markAsDone();
                        }
                        taskList.add(taskToAdd);
                        break;
                    case "E":
                        String at = taskInfo[3];
                        taskToAdd = new Event(taskName, at);
                        if (isDone) {
                            taskToAdd.markAsDone();
                        }
                        taskList.add(taskToAdd);
                        break;
                    case "D":
                        String by = taskInfo[3];
                        taskToAdd = new Deadline(taskName, by);
                        if (isDone) {
                            taskToAdd.markAsDone();
                        }
                        taskList.add(taskToAdd);
                        break;
                    default:
                        break;
                }
            }
            return taskList;
        } catch (IOException e) {
            fileError();
            return taskList;
        }
    }

    public static void fileError() {
        System.out.println("Oops! There's been an error with the data file, please try again!");
    }

    public int getNumOfTasks() {
        try {
            createFile(this.fileName);
            Scanner sc = new Scanner(new FileReader(this.fileName));
            sc.useDelimiter("\\n");
            int count = 0;
            while (sc.hasNext()) {
                String next = sc.next();
                count++;
            }
            sc.close();
            return count;
        } catch (FileNotFoundException e) {
            fileError();
        }
        return 0;
    }

    public void createFile(String fileName) {
        try {
            File dataFile = new File(fileName);
            if (dataFile.createNewFile()) {
                ;
            }
        } catch (IOException e) {
            fileError();
        }
    }

    public String processLine(String taskLine) {
        String[] taskInfo = taskLine.trim().split(" [|] ");
        String taskType = taskInfo[0];
        String isDone = taskInfo[1].equals(String.valueOf(1)) ? "[\u2713] " : "[\u2718] ";
        String taskName = taskInfo[2];
        String result = "";
        switch (taskType) {
            case "T":
                result = String.format("[T]%1$s%2$s", isDone, taskName);
                break;
            case "E":
                String at = taskInfo[3];
                at = LocalDate.parse(at).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                result = String.format("[E]%1$s%2$s (at: %3$s)", isDone, taskName, at);
                break;
            case "D":
                String by = taskInfo[3];
                by = LocalDate.parse(by).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                result = String.format("[D]%1$s%2$s (by: %3$s)", isDone, taskName, by);
                break;
            default:
                break;
        }
        return result;
    }

    public void readFile() {
        createFile(this.fileName);
        BufferedReader reader = null;
        int i;
        String curr;
        try {
            reader = new BufferedReader(new FileReader(this.fileName));
            i = 1;
            while ((curr = reader.readLine()) != null) {
                System.out.println(i + ". " + processLine(curr));
                i++;
            }
        } catch (IOException e) {
            fileError();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                fileError();
            }
        }
    }

    public void writeToFile(String text) throws IOException{
        FileWriter writer = new FileWriter(this.fileName, this.appendToFile);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(text);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

    public void saveData(String text) {
        try {
            createFile(this.fileName);
            Storage data = new Storage(this.fileName, true);
            data.writeToFile(text);
        } catch (IOException e) {
            fileError();
        }
    }

    public String printLine(int lineNumber) throws IOException {
        lineNumber = lineNumber - 1;
        String lineToRemove = Files.readAllLines(Paths.get(this.fileName)).get(lineNumber);
        return lineToRemove;
    }

    public void deleteFromFile(int lineNumber) throws IOException{
        File currFile = new File(this.fileName);
        File tempFile = new File("duke_data_temp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(currFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        lineNumber = lineNumber - 1;
        String lineToRemove = Files.readAllLines(Paths.get(this.fileName)).get(lineNumber);
        String currLine;
        while((currLine = reader.readLine()) != null) {
            String trimLine = currLine.trim();
            if (trimLine.equals(lineToRemove)) {
                continue;
            }
            writer.write(currLine + '\n');
        }
        writer.close();
        reader.close();
        if (currFile.delete()) {
            if (!tempFile.renameTo(currFile)) {
                fileError();
            }
        } else {
            fileError();
        }
    }

    public void setDoneLine(int lineNumber) throws IOException  {
        File currFile = new File(this.fileName);
        File tempFile = new File("duke_data_temp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(currFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        lineNumber = lineNumber - 1;
        String lineToUpdate = Files.readAllLines(Paths.get(this.fileName)).get(lineNumber);
        String[] taskInfo = lineToUpdate.trim().split(" [|] ");
        taskInfo[1] = String.valueOf(1);
        String doneLine = String.join(" | ", taskInfo);
        String currLine;
        while((currLine = reader.readLine()) != null) {
            String trimLine = currLine.trim();
            if (trimLine.equals(lineToUpdate)) {
                writer.write(doneLine + '\n');
            } else {
                writer.write(currLine + '\n');
            }
        }
        writer.close();
        reader.close();
        if (currFile.delete()) {
            if (!tempFile.renameTo(currFile)) {
                fileError();
            }
        } else {
            fileError();
        }
    }

}
