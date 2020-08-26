package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList(){
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list){
        this.list = list;
    }

    public TaskList(BufferedReader reader) throws IOException {
        if(reader == null){
            list = new ArrayList<>();
        }
        else{
            list = readTextFile2List(reader);
        }
    }

    public static ArrayList<Task> readTextFile2List(BufferedReader reader) throws IOException {
        ArrayList<Task> list = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null){
            String[] part = line.split("\\|");
            if(part[0].equals("T")){
                list.add(new Todo(part[2], part[1].equals("1")));
            }
            else if(part[0].equals("D")){
                list.add(Deadline.of(part[2], part[3], part[1].equals("1")));
            }
            else{
                list.add(Event.of(part[2], part[3], part[1].equals("1")));
            }
        }
        return list;
    }

    public Task get(int index){
        return list.get(index);
    }

    public void printList(){
        for(int i = 0; i < list.size(); i++){
            System.out.println((i + 1) + list.get(i).toString());
        }
    }

    public Task markTaskDone(int index){
        if(index < list.size()){
            list.get(index).markAsDone();
            return list.get(index);
        }
        else{
            return null;
        }
    }

    public int getSize(){
        return list.size();
    }

    public Task delete(int index){
        if(index < list.size()){
            return list.remove(index);
        }
        else{
            return null;
        }
    }

    public void add(Task task){
        list.add(task);
    }
}
