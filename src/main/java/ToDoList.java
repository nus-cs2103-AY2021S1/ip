import java.util.Scanner;
import java.util.ArrayList;

public class ToDoList{
    private ArrayList<Task> todoList = new ArrayList<>();

    /**
     * It starts to read commands from screen and stops only when "bye"
     * command is read.
     */

    public static ToDoList start(){
        Scanner sc = new Scanner(System.in);
        Command command = Command.INIT;
        ToDoList todo = new ToDoList();
        while (command != Command.BYE){      //when last command was bye
            command = Command.getCommand(sc.nextLine());
            todo.runCommand(command);
        }
        sc.close();
        return todo;
    }

    private void runCommand(Command command) {
        switch (command) {
            case BYE:
                print("Bye. Hope to see you again soon!");
                break;
            case LIST:
                printList();
                break;
            case DONE:
                print(command.getTaskContent());
                int index = Integer.parseInt(command.getTaskContent())-1;
                finishTask(index);
                break;
            default:
                Task task = new Task(command.echo());
                print("added: " + task.getName());
                todoList.add(task);
                break;
        }
    }

    private void printList(){
        for (int i = 0; i < todoList.size(); i++){
            Task task = todoList.get(i);
            if (task.checkDone())
                print(i+1 + ". [X] " + task.getName());
            else
                print(i+1 + ". [ ] " + task.getName());
        }
    }

    private void finishTask(int index){
        print("Nice! I've marked following task as done: ");
        Task task = todoList.get(index);
        task.closeTask();
        print("  [x] "+ task.getName());
    }

    private void print(String str){
        System.out.println("    " + str);
    }

    private void print(String prefix, String str){
        System.out.println(prefix+str);
    }
}