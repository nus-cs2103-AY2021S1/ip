package Command;

public class ShowFunctionCommand extends Command{
    public static void execute(){
        System.out.println("list: to show all existing tasks.");
        System.out.println("bye: to exit the todo bot.");
        System.out.println("delete [task index]: to delete the selected task from the todolist.");
        System.out.println("todo [task name]: to add the todo task into the list.");
        System.out.println("deadline [task name] /by [dd-MM-uuuu HHmm]: " +
                        "add a deadline task with the specific time and date.");
        System.out.println("event [task name] /at [dd-MM-uuuu HHmm]: " +
                        "add a event task with the specific period.");
        System.out.println("done [task index]: to mark the specific task as completed.");
        System.out.println("filter [d-MM-uuuu]: to show all the tasks with this date.");
    }
}
