package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            String parentFilepath = Path.of(this.filepath).getParent().toString();
            File parentFolder = new File(parentFilepath);
            if (!parentFolder.exists()) {
                parentFolder.mkdir();
            }
            File taskFile = new File(this.filepath);
            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }
            Scanner scanner = new Scanner(taskFile);
            ArrayList<Task> tasks = new ArrayList<>();
            while (scanner.hasNext()) {
                tasks.add(Parser.parseTaskFromFile(scanner.nextLine()));
            }
            return tasks;
        } catch (IOException e) {
            throw DukeException.FILE_LOADING_EXCEPTION;
        }
    }

    public void addLine(String line) {
        try {
            FileWriter fw = new FileWriter(this.filepath, true);
            fw.write(line + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("Encountered an unexpected error with the file :(");
        }
    }

    public void deleteLine(int index) {
        try {
            String tempFilepath = Path.of(this.filepath).getParent().toString() + "/temp.txt";
            Files.copy(Path.of(this.filepath), Path.of((tempFilepath)));
            FileWriter fw = new FileWriter(this.filepath);
            File copy = new File(tempFilepath);
            Scanner scanner = new Scanner(copy);
            int lineNumber = 1;
            while (scanner.hasNext()) {
                if (lineNumber != index) {
                    fw.write(scanner.nextLine() + System.lineSeparator());
                } else {
                    scanner.nextLine();
                }
                lineNumber++;
            }
            fw.close();
            scanner.close();
            Files.delete(Path.of(tempFilepath));
        } catch (IOException e) {
            System.out.println("Encountered an unexpected error with the file :(");
        }
    }

    public void replaceLine(int index, String line) {
        try {
            String tempFilepath = Path.of(this.filepath).getParent().toString() + "/temp.txt";
            Files.copy(Path.of(this.filepath), Path.of((tempFilepath)));
            FileWriter fw = new FileWriter(this.filepath);
            File copy = new File(tempFilepath);
            Scanner scanner = new Scanner(copy);
            int lineNumber = 1;
            while (scanner.hasNext()) {
                if (lineNumber != index) {
                    fw.write(scanner.nextLine() + System.lineSeparator());
                } else {
                    fw.write(line + System.lineSeparator());
                    scanner.nextLine();
                }
                lineNumber++;
            }
            fw.close();
            scanner.close();
            Files.delete(Path.of(tempFilepath));
        } catch (IOException e) {
            System.out.println("Encountered an unexpected error with the file :(");
        }
    }
}
