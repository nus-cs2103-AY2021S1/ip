import task.*;

import java.io.*;
import java.util.Scanner;

public class Storage {

    public static void saveTasks(String path, TaskList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(fw);
        for(Task t : taskList){
            bw.write(t.encode());
            bw.newLine();
        }
        bw.close();
    }
    public static TaskList<Task> loadTasks(String path){
        File f = new File(path);
        TaskList<Task> taskList = new TaskList<Task>();
        try {
            Scanner fileReader = new Scanner(f);
            while(fileReader.hasNextLine()){
                String[] command = Parser.parseCommand(fileReader.nextLine());
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
