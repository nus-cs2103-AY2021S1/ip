package Tasks;

import DateTime.DateTimeManager;
import Errors.ErrorExceptions;
import UI.UserInterface;
import File.FileManager;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskManager {
    private static ArrayList<task> store = new ArrayList<>();
    private static String fileDir;

    public static void newTask(String name, String itype, String date, String fileDir) throws ErrorExceptions {
        task Task;
        if(itype.equals("Todo")){
            Task = new Todo(name,"[T]");
        } else if(itype.equals("Deadline")){
            Task = new Deadline(name,"[D]");
            DateTimeManager.addDate(Task,date);
        } else{
            Task = new Event(name,"[E]");
            DateTimeManager.addDate(Task,date);
        }
        store.add(Task);
        UserInterface.addedTask(Task);
        save(fileDir,Task);
    }

    public static task getTask(int index){
        task t = store.get(index-1);
        return t;
    }
    public static void completed(task t){
        t.done();
        try {
            FileManager.edit(fileDir, store);
        } catch(IOException e){
            System.out.println(e);
        }
    }

    public static void delete(int index){
        store.remove(index-1);
        try {
            FileManager.edit(fileDir, store);
        } catch(IOException e){
            System.out.println(e);
        }
    }
    public static int storeIndex(){
        return store.size();
    }
    public static String read(task t){
        String done = "";
        if(t.taskCompleted()){
            done = "[✓]";
        }
        else{
            done = "[X]";
        }
        if(t.taskType().equals("[T]")){
            return t.taskType() + done + " " + t.taskName();
        } else if(t.taskType().equals("[D]")){
            return t.taskType() + done + " " + t.taskName() + "(by: " +
                    t.taskDate().format(DateTimeFormatter.ofPattern("dd MMM uuuu HHmm")) + ")";
        } else{
            return t.taskType() + done + " " + t.taskName() + "(at: " +
                    t.taskDate().format(DateTimeFormatter.ofPattern("dd MMM uuuu HHmm")) + ")";
        }
    }
    public static void listing(){
        System.out.println("These are your current tasks!");
        int count = 1;
        for(task i : store){
            System.out.println(count + ". " + read(i));
            count++;
        }
    }
    public static void load(File save){
        FileManager.read(save,store);
    }
    public static void save(String fileDir,task t){
        try{
            FileManager.add(fileDir,TaskManager.read(t));
        } catch(IOException e){
            System.out.println(e);
        }
    }
    public static void fileDir(String d){
        fileDir = d;
    }

    public static ArrayList<task> getStore(){
        ArrayList<task> clone = new ArrayList<>();
        for(task i : store){
            clone.add(i);
        }
        return clone;
    }
}
