import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class WriteFile {

    private String path;
    private boolean appendToFile = false;
    private static final String fileName = "duke_data.txt";

    public WriteFile(String path) {
        this.path = path;
    }

    public WriteFile(String path, boolean appendToFile) {
        this.path = path;
        this.appendToFile = appendToFile;
    }

    public static void fileError() {
        System.out.println("Oops! There's been an error with the data file, please try again!");
    }

    public static int getNumOfTasks() {
        try {
            Scanner sc = new Scanner(new FileReader(fileName));
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

    public static void createFile(String fileName) {
        try {
            File dataFile = new File(fileName);
            if (dataFile.createNewFile()) {
                ;
            }
        } catch (IOException e) {
            fileError();
        }
    }

    public static String processLine(String taskLine) {
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
                result = String.format("[E]%1$s%2$s (at: %3$s)", isDone, taskName, at);
                break;
            case "D":
                String by = taskInfo[3];
                result = String.format("[D]%1$s%2$s (by: %3$s)", isDone, taskName, by);
                break;
            default:
                break;
        }
        return result;
    }

    public static void readFile() {
        createFile(fileName);
        BufferedReader reader = null;
        int i;
        String curr;
        try {
            reader = new BufferedReader(new FileReader(fileName));
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
        FileWriter writer = new FileWriter(this.path, this.appendToFile);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(text);
        bufferedWriter.newLine();
        bufferedWriter.close();
        //PrintWriter printer = new PrintWriter(writer);
        //printer.printf("%s" + "%n", text);
        //printer.close();
        //writer.write(text);
        //writer.write("\r\n");
        //writer.close();
    }

    public static void saveData(String text) {
        try {
            createFile(fileName);
            WriteFile data = new WriteFile(fileName, true);
            data.writeToFile(text);
        } catch (IOException e) {
            fileError();
        }
    }

    public static String printLine(int lineNumber) throws IOException {
        lineNumber = lineNumber - 1;
        String lineToRemove = Files.readAllLines(Paths.get(fileName)).get(lineNumber);
        return lineToRemove;
    }

    public static void deleteFromFile(int lineNumber) throws IOException{
        File currFile = new File(fileName);
        File tempFile = new File("duke_data_temp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(currFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        lineNumber = lineNumber - 1;
        String lineToRemove = Files.readAllLines(Paths.get(fileName)).get(lineNumber);
        String currLine;
        while((currLine = reader.readLine()) != null) {
            String trimLine = currLine.trim();
            if (trimLine.equals(lineToRemove)) {
                continue;
            }
            writer.write(currLine + System.getProperty("line.separator"));
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

    public static void setDoneLine(int lineNumber) throws IOException  {
        File currFile = new File(fileName);
        File tempFile = new File("duke_data_temp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(currFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        lineNumber = lineNumber - 1;
        String lineToUpdate = Files.readAllLines(Paths.get(fileName)).get(lineNumber);
        String[] taskInfo = lineToUpdate.trim().split(" [|] ");
        taskInfo[1] = String.valueOf(1);
        String doneLine = String.join(" | ", taskInfo);
        String currLine;
        while((currLine = reader.readLine()) != null) {
            String trimLine = currLine.trim();
            if (trimLine.equals(lineToUpdate)) {
                writer.append(doneLine);
                writer.append('\n');
            } else {
                writer.write(currLine + System.getProperty("line.separator"));
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
