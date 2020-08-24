import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.sql.SQLOutput;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String DEFAULT_PATH = "./data/duke.txt";
    private final Path path;

    public Storage() throws IOException {
        path  = Paths.get(DEFAULT_PATH);
        File file = new File(DEFAULT_PATH);
        file.getParentFile().mkdirs();

    }

    public ArrayList<Task> load(){
        if(!Files.exists(path) || !Files.isRegularFile(path)){
            return new ArrayList<Task>();
        }
        try {
            return convertTextToTask(Files.readAllLines(path));
        } catch (FileNotFoundException e){
            Duke.printLine();
            System.out.println("ERROR: There is an error in reading the files");
            Duke.printLine();
            return new ArrayList<Task>();
        } catch (IOException e) {
            Duke.printLine();
            System.out.println("ERROR: There is an error with the inputs from the txt file");
            Duke.printLine();
            return new ArrayList<Task>();
        }
    }


    public ArrayList<String> convertArrayToSaveFormat(ArrayList<Task> stringStore) {
        ArrayList<String> strings = new ArrayList<>();
        for(Task tasks: stringStore){
            strings.add(tasks.writerSave());
        }
        return strings;
    }

    public ArrayList<Task> convertTextToTask(List<String> lines){
            ArrayList<Task> tasks = new ArrayList<>();
            try{
                for (String line : lines) {
                    String[] currLine = line.split(" \\| ");
                    Task currTask = null;
                    if (currLine[0].equals("T")) {
                        currTask = new Todo(currLine[2]);
                    } else if (currLine[0].equals("D")) {
                        currTask = new Deadline(currLine[2], currLine[3]);
                    } else if (currLine[0].equals("E")) {
                        currTask = new Event(currLine[2], currLine[3]);
                    }
                    if (currLine[1].equals("1")) {
                    currTask.markAsDone();
                    }
                    tasks.add(currTask);
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                Duke.printLine();
                System.out.println("ERROR: Incorrect inputs in data file!");
                Duke.printLine();
            }
            return tasks;

    }


    public void save(ArrayList<String> strings) {
        try{
            Files.write(path, strings);
        } catch (IOException e) {
            Duke.printLine();
            System.out.println("ERROR: There is error in writing to file");
            Duke.printLine();
        }

    }
}
