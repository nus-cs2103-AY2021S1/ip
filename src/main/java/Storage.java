import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.util.List;
import java.util.ArrayList;

public class Storage {
    private static final String DEFAULT_PATH = "./data/duke.txt";
    private final Path path;

    public Storage() throws IOException {
        path  = Paths.get(DEFAULT_PATH);
        File file = new File(DEFAULT_PATH);
        file.getParentFile().mkdirs();

    }

    public TaskList load(){
        if(!Files.exists(path) || !Files.isRegularFile(path)){
            return new TaskList();
        }
        try {
            return convertTextToTask(Files.readAllLines(path));
        } catch (FileNotFoundException e){
            UI.printFormattedMessage("ERROR: There is an error in reading the files");
            return new TaskList();
        } catch (IOException e) {
            UI.printFormattedMessage("ERROR: There is an error with the inputs from the txt file");
            return new TaskList();
        }
    }


    public ArrayList<String> convertArrayToSaveFormat(TaskList tasklist) {
        ArrayList<String> strings = new ArrayList<>();
        for(Task tasks: tasklist.getTasks()){
            strings.add(tasks.writerSave());
        }
        return strings;
    }

    public TaskList convertTextToTask(List<String> lines){
            TaskList tasks = new TaskList();
            try{
                for (String line : lines) {
                    String[] currLine = line.split(" \\| ");
                    Task currTask = null;
                    if (currLine[0].equals("T")) {
                        currTask = new ToDo(currLine[2]);
                    } else if (currLine[0].equals("D")) {
                        currTask = new Deadline(currLine[2], currLine[3]);
                    } else if (currLine[0].equals("E")) {
                        currTask = new Event(currLine[2], currLine[3]);
                    }
                    if (currLine[1].equals("1")) {
                    currTask.markAsDone();
                    }
                    tasks.addTask(currTask);
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                UI.printFormattedMessage("ERROR: Incorrect inputs in data file!");
            }
            return tasks;

    }


    public void save(ArrayList<String> strings) {
        try{
            Files.write(path, strings);
        } catch (IOException e) {
            UI.printFormattedMessage("ERROR: There is error in writing to file");
        }

    }
}
