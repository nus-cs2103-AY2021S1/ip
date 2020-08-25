import java.util.List;

public class TaskList {
    private List<Task> stored_tasks;
    private Storage storage;
    private final String line = "____________________________________________________________";

    public TaskList(List<Task> stored_tasks, Storage storage){
        this.stored_tasks = stored_tasks;
        this.storage = storage;
    }

    /**
     * Lists stored task by looping through stored_task, along with their status.
     **/
    public void listStoredTasks() {
        if (stored_tasks.isEmpty()) {
            System.out.println("No tasks stored...");
        } else {
            System.out.println(line);
            System.out.println("Quack! Here are the tasks in your list:");
            int count = 1;
            for (Task task : stored_tasks) {
                System.out.println(count + ". " + task);
                count++;
            }
            System.out.println(line);
        }
    }

    public void displayTaskCount() {
        int numOfTasks = stored_tasks.size();
        if (numOfTasks == 1) {
            System.out.println("My duck senses tell me you have 1 task in the list.");
        } else {
            System.out.println("My duck senses tell me you have " + numOfTasks + " tasks in the list.");
        }
    }

    /**
     * Adds input task into stored_task.
     *
     * @param newTask Input task from user to be stored.
     **/
    public void addTask(Task newTask) {
        try {
            stored_tasks.add(newTask);
            storage.updateTasks(stored_tasks);
            System.out.println(line);
            System.out.println("Quack! I have added: " + newTask);
            displayTaskCount();
            System.out.println(line);
        } catch (DukeException e) {
            System.out.println(line);
            System.out.println(e.getMessage());
            System.out.println(line);
        }
    }

    /**
     * Marks task as done.
     *
     * @param taskNumber Task number of task to be marked as done.
     **/
    public void markTaskAsDone(int taskNumber) {
        try {
            if (taskNumber <= 0 || taskNumber > stored_tasks.size()) {
                throw new DukeException("Wrong task number!");
            } else {
                Task t = stored_tasks.get(taskNumber - 1);
                if (t.isDone()) {
                    throw new DukeException("This task is already done: " + t.getDescription());
                } else {
                    t.markAsDone();
                    storage.updateTasks(stored_tasks);
                    System.out.println(line);
                    System.out.println("Quack! I have marked this task as done: \n" + t);
                    System.out.println(line);
                }
            }
        } catch (DukeException e) {
            System.out.println(line);
            System.out.println(e.getMessage());
            System.out.println(line);
        }
    }

    /**
     * Deletes input task from stored_task.
     *
     * @param taskNumber Task number of task to be deleted.
     **/
    public void deleteTask(int taskNumber) {
        try {
            if (taskNumber <= 0 || taskNumber > stored_tasks.size()) {
                throw new DukeException("Wrong task number!");
            } else {
                Task taskToDelete = stored_tasks.get(taskNumber - 1);
                stored_tasks.remove(taskToDelete);
                storage.updateTasks(stored_tasks);
                System.out.println(line);
                System.out.println("Quack! I have deleted this task: \n" + taskToDelete);
                displayTaskCount();
                System.out.println(line);
            }
        } catch (DukeException e) {
            System.out.println(line);
            System.out.println(e.getMessage());
            System.out.println(line);
        }
    }
}
