package duke;

import task.*;

import java.io.*;
import java.util.Scanner;

/**
 * duke.Storage is a utility class that handles the saving and loading of tasks for the duke.Duke program.
 */
public class Storage {
    /**
     * Saves the a duke.TaskList into a specified file.
     * @param path  Path of the file.
     * @param taskList List of tasks to be saved.
     * @throws IOException Throws IOException when it encounters an IO error.
     */
    public static void saveTasks(String path, TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(fw);
        for(Task t : taskList){
            bw.write(t.encode());
            bw.newLine();
        }
        bw.close();
    }

    /**
     * Returns a list of Tasks loaded from the specified path.
     * If the file is not found, an empty list of Tasks is returned.
     * @param path Path of where the file is.
     * @return The list of tasks loaded from the path
     */
    public static TaskList loadTasks(String path){
        File f = new File(path);
        TaskList taskList = new TaskList();
        try {
            Scanner fileReader = new Scanner(f);
            while(fileReader.hasNextLine()){
                String[] command = Parser.parseCommand(fileReader.nextLine());
                switch(command[0]){
                    case "done":

                }
                if(command[0].contentEquals("done")){
                    int index = taskList.size() - 1;
                    taskList.get(index).setDone();
                }
                else if(command[0].contentEquals("todo")){
                    try {
                        Task newTask = new Todo(command[1]);
                        taskList.add(newTask);
                    }
                    catch(EmptyStringException e){
                        Ui.printFileError("Error encountered in save file.");
                    }
                }
                else if(command[0].contentEquals("deadline")){
                    try {
                        Task newTask = new Deadline(command[1]);
                        taskList.add(newTask);
                    }
                    catch(EmptyStringException e){
                        Ui.printFileError("Error encountered in save file.");
                    }
                }
                else if(command[0].startsWith("event")){
                    try {
                        Task newTask = new Event(command[1]);
                        taskList.add(newTask);
                    }
                    catch(EmptyStringException e){
                        Ui.printFileError("Error encountered in save file.");
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            Ui.printFileError("Previous file not found, creating a new save file");
            return taskList;
        }
        return taskList;
    }

}
