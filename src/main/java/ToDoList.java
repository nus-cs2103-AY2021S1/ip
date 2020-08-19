import java.util.Scanner;
import java.util.ArrayList;

public class ToDoList{
    private ArrayList<Task> todoList = new ArrayList<>();
    private int undoneCount = 0;

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
                int index = Integer.parseInt(command.getTaskContent())-1;
                finishTask(index);
                break;
            case TODO:
                addTodo(command.getTaskContent());
                break;
            case DEADLINE:
                addDeadline(command.getTaskContent());
                break;
            case EVENT:
                addEvent(command.getTaskContent());
                break;
            case INVALID:
                System.out.println("There is supposed to be an exception here.");
                break;
            default:
                System.out.println("This situation is not supposed to happen. Please call the idiot programmer.");

        }
    }

    private void addTodo(String taskContent){
        this.undoneCount++;
        Task task = new Todo(taskContent);
        this.todoList.add(task);
        print("The following task has been added to your list: ");
        print("  [T][ ] "+task.toString());
        print(String.format("Now you have %d tasks in your list.", todoList.size()));
        print(String.format("There are %d tasks waiting to be done.", this.undoneCount));
    }

    private void addDeadline(String taskContent){
        this.undoneCount++;
        String[] splitedContent = taskContent.split("/by");
        Task task = new Deadline(splitedContent[0],splitedContent[1]);
        this.todoList.add(task);
        print("The following task has been added to your list: ");
        print("  [D][ ] "+task.toString());
        print(String.format("Now you have %d tasks in your list.", todoList.size()));
        print(String.format("There are %d tasks waiting to be done.", this.undoneCount));
    }

    private void addEvent(String taskContent){
        this.undoneCount++;
        String[] splitedContent = taskContent.split("/at");
        Task task = new Event(splitedContent[0],splitedContent[1]);
        this.todoList.add(task);
        print("The following task has been added to your list: ");
        print("  [E][ ] "+task.toString());
        print(String.format("Now you have %d tasks in your list.", todoList.size()));
        print(String.format("There are %d tasks waiting to be done.", this.undoneCount));
    }

    private void printList(){
        for (int i = 0; i < todoList.size(); i++){
            Task task = todoList.get(i);
            if (task.checkDone())
                print(i+1 + ".["+task.getTaskType()+"][X] " + task.toString());
            else
                print(i+1 + ".["+task.getTaskType()+"][ ] " + task.toString());
        }
    }

    private void finishTask(int index){
        this.undoneCount--;
        print("Nice! I've marked following task as done: ");
        Task task = todoList.get(index);
        task.closeTask();
        print("  [x] "+ task.toString());
        print(String.format("Now you have %d tasks waiting to be done.", this.undoneCount));
    }

    private void print(String str){
        System.out.println("    " + str);
    }

    private void print(String prefix, String str){
        System.out.println(prefix+str);
    }
}