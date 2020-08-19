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
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case LIST:
                printList();
                break;
            default:
                Task task = new Task(command.echo());
                System.out.println("added: " + task.getName());
                todoList.add(task);
                break;
        }
    }

    private void printList(){
        for (int i = 0; i < todoList.size(); i++){
            System.out.println(i+1 + ". " + todoList.get(i).getName());
        }
    }
}