import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

//    public TaskList(String filepath) {
//        try {
//            FileReader file = new FileReader(filepath);
//            BufferedReader reader = new BufferedReader(file);
//            String line;
//            while ((line = reader.readLine()) != null){
//                tasks.add(getTask(line));
//            }
//            file.close();
//        } catch (IOException e){
//            tasks = new ArrayList<>();
//        }
//    }
//
//    private tasks.Task getTask(String line){
//        tasks.Task task;
//        if (line.charAt(1) == 'T'){
//            task = new tasks.ToDos(line.substring(6));
//        } else if (line.charAt(1) == 'D'){
//            int index = line.indexOf("|");
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//            LocalDateTime date = LocalDateTime.parse(line.substring(index + 1).trim(), formatter);
//            task = new tasks.Deadline(line.substring(6, index), date);
//        } else {
//            int index = line.indexOf("|");
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//            LocalDateTime date = LocalDateTime.parse(line.substring(index + 1).trim(), formatter);
//            task = new tasks.Event(line.substring(6, index), date);
//        }
//        if (line.charAt(4) == '✓'){
//            task.updateStatus();
//        }
//        return task;
//    }

    public void update(Task task){
        tasks.add(task);
    }

    public Task get(int i){
        return tasks.get(i - 1);
    }

    public void delete(int i){
        tasks.remove(i - 1);
    }

    public void updateStatus(int i){
        tasks.get(i - 1).updateStatus();
    }

    public int getSize(){
        return tasks.size();
    }

    public String save(){
        StringBuilder line = new StringBuilder();
        for (Task task : tasks){
            if (!task.istodo()){
                String append = task.description() + task.getWork() + "|" + task.getDate() + "\n";
                line.append(append);
                continue;
            }
            String append = task.toString() + "\n";
            line.append(append);
        }
        return line.toString();
    }

    public String toString(){
        StringBuilder line = new StringBuilder();
        for (Task task : tasks) {
            line.append(task.toString());
            line.append('\n');
        }
        return line.toString();
    }
}
