import java.io.*;
import java.util.Scanner;

public class Storage {
    private String rootPath;
    private String directoryPath;
    private String filePath;
    private String lineSeparator = System.getProperty("line.separator");
    private String taskDetailsSeparator = " | ";

    public Storage() {
        this.rootPath = System.getProperty("user.dir");;
        this.directoryPath = this.rootPath + File.separator + "data";
        this.filePath = this.directoryPath + File.separator + "data.txt";
    }

    public void createFile() {
        File dataFolder = new File(this.directoryPath);
        try {
            // Make directory if it doesn't exist yet
            dataFolder.mkdir();
            File dataFile = new File(this.directoryPath, "data.txt");
            // Make file if it doesn't exist yet
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String taskType, String isCompleted, String taskDetails, String date) {
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            if (date.equals("")) {
                // For To-do tasks with no date
                fw.write(taskType + taskDetailsSeparator + isCompleted
                        + taskDetailsSeparator + taskDetails + this.lineSeparator);
            } else {
                // For Event and Deadline tasks
                fw.write(taskType + taskDetailsSeparator + isCompleted
                        + taskDetailsSeparator + taskDetails + taskDetailsSeparator
                        + date + this.lineSeparator);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void modifyLineInTextFile(int lineNumber, String type) throws IOException {
        String tempFilePath = System.getProperty("user.dir") + File.separator
                + "data" + File.separator + "dataTemp.txt";
        File inputFile = new File(this.filePath);
        File tempFile = new File(tempFilePath);

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        int lineToRemove = lineNumber;
        String currentLine;
        int count = 0;

        while ((currentLine = reader.readLine()) != null) {
            count++;
            if (count == lineToRemove) {
                if (type.equals("done")) {
                    // Modify current line and write to text file if is a "done" command
                    String lineToWrite[] = currentLine.split("\\|", 3);
                    writer.write(lineToWrite[0] + "| " + "1"
                            + " |" + lineToWrite[2] + this.lineSeparator);
                }
                continue;
            }
            // Write all other lines
            writer.write(currentLine + this.lineSeparator);
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    public void loadFromTextFile(TaskList taskList) throws FileNotFoundException {
        File file = new File(this.filePath); // create a File for the given file path
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            String details[] = currentLine.split("\\|");
            switch(details[0].trim()) {
                // Trims away whitespaces at the start and end of string
                case "T":
                    taskList.addTodo(details[1].trim(), details[2].trim());
                    break;
                case "D" :
                    taskList.addDeadline(details[1].trim(), details[2].trim(), details[3].trim());
                    break;
                case "E":
                    taskList.addEvent(details[1].trim(), details[2].trim(), details[3].trim());
            }
        }
        s.close();
    }
}
