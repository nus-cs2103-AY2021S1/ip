import java.util.ArrayList;

public class DukeLogic {
    private ArrayList<Task> taskList;

    public DukeLogic(){
        this.taskList = new ArrayList<>();
    }

    public void executeCommand(String command) {
        if(command.equals("list")){
            printTaskList();
        } else if (command.substring(0, 4).equals("done")){
            completeTask(command);
        } else {
            addTask(command);
        }
    }

    public void printTaskList(){
        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are the tasks in your list!!");
        int index = 1;
        for(Task task: this.taskList) {
            System.out.println("    " + index + ". " + task);
            index++;
        }
        System.out.println("    ____________________________________________________________");
    }

    public void completeTask(String command){
        int index = Integer.parseInt(command.substring(5));
        Task task = this.taskList.get(index - 1).markAsDone();
        System.out.println("    ____________________________________________________________");
        System.out.println("     Yay! I've marked this task as done :3");
        System.out.println("       " + task);
        System.out.println("    ____________________________________________________________");
    }

    public void addTask(String command) {
        String typeOfTask = command.split(" ")[0];
        if (typeOfTask.equals("todo")) {
            this.taskList.add(new Todo(command.substring(5)));
        } else if (typeOfTask.equals("event")) {
            String taskName = command.substring(command.indexOf("event ") + 6, command.indexOf(" /at"));
            String taskDate = command.substring(command.indexOf("/at ") + 4);
            this.taskList.add(new Event(taskName, taskDate));
        } else if (typeOfTask.equals("deadline")) {
            String taskName = command.substring(command.indexOf("deadline ") + 9, command.indexOf(" /by"));
            String taskDate = command.substring(command.indexOf("/by ") + 4);
            this.taskList.add(new Deadline(taskName, taskDate));
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Okies! I've added this task~");
        System.out.println("       " + this.taskList.get(this.taskList.size() - 1));
        System.out.println("     Now you have " + this.taskList.size() + " tasks in the list uwu");
        System.out.println("    ____________________________________________________________");
    }
}
