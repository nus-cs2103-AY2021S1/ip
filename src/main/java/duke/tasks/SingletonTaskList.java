package duke.tasks;

import duke.exceptions.TaskOutOfBoundException;
import duke.storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class SingletonTaskList {
    List<Task> tasks;
    Storage storage = new Storage();


    private static SingletonTaskList instance;

    public static synchronized SingletonTaskList getInstance() {
        if (instance == null) {
            instance = new SingletonTaskList();
        }
        return instance;
    }


    private SingletonTaskList() {
        tasks = storage.readAll();
    }

    public void add(Task task) {
        tasks.add(task);
        int numOfTask = tasks.size();
        System.out.println("Got it. I've added this task:\n" +
                "   " + task +  '\n' +
                "Now you have " + numOfTask + (numOfTask > 1 ? "duke/tasks " : " task ") + "in the list.");
        storage.update(tasks);
    }

    public void delete(int idx) throws TaskOutOfBoundException {
        try {
            System.out.println("Noted. I've removed this task:\n" + "   " +
                    tasks.get(idx - 1) + "\n" + "Now you have " + (tasks.size() - 1) + " duke.tasks in the list.");
            tasks.remove(idx - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskOutOfBoundException("task number out of bound", idx);
        }
    }

    public void listAll() {
        if(tasks.size() == 0){
            System.out.println("😝You don't have any task in the schedule yet~~\n" +
                    "use todo/deadline/event command to create your duke.tasks~");
        }
        for(int i =1; i< tasks.size()+1; i++){
            System.out.println(i + ". " + tasks.get(i-1));
        }
    }

    public void doneTask(int idx, String[] taskDescription) throws TaskOutOfBoundException {
        try {
            Task task = this.tasks.get(idx);
            task.setStatus(true);
            System.out.println("Très bien!I have helped you marked task " + taskDescription + " as done\n"
                    + task);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskOutOfBoundException("Target number of task out of bound", idx + 1);
        }

    }

}
