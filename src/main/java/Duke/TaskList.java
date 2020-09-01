package Duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> dukeList = new ArrayList<>();

    public TaskList(ArrayList<Task> list){
        this.dukeList.addAll(list);
    }

    public void add(Task task){
        // Add to List
        dukeList.add(task);
    }

    public Task remove(int order){
        return dukeList.remove(order - 1);
    }

    public Task markDone(int order){
        Task task = dukeList.get(order - 1);
        task.markDone();
        return task;
    }

    public String listToString() {
        String output = "";
        for (int i = 0; i < dukeList.size(); i++) {
            output = output + (i + 1) + ". " + dukeList.get(i).toString() + "\n";
        }

        return output;
    }
    public int getSize(){
        return dukeList.size();
    }

    public String find (String subName) {
        StringBuilder output = new StringBuilder();
        int counter = 0;
        for (Task t: dukeList) {
            String s1 = t.getName().toLowerCase();
            if(s1.contains(subName.toLowerCase())) {
                output.append(counter + 1).append(".").append(t.toString()).append("\n");
                counter +=1;
            }
        }

        return output.toString();
    }
}