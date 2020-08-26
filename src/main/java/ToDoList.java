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
            System.out.print("> ");
            command = Command.getCommand(sc.nextLine());
            todo.runCommand(command);
        }
        sc.close();
        return todo;
    }

    private void runCommand(Command command){
        try {
            switch (command) {
                case BYE:
                    print("Bye. Hope to see you again soon!");
                    break;
                case LIST:
                    printList();
                    break;
                case DONE:
                    try {
                        int index = Integer.parseInt(command.getTaskContent()) - 1;
                        finishTask(index);
                    } catch (IndexOutOfBoundsException e){
                        print("Sorry, I can't do that for you.");
                        print("You only have " + String.format("%d",todoList.size()) + " tasks on your list.");
                    } catch (Exception e) {
                        try {
                            String tmp = command.getTaskContent();
                            if (tmp.length() < 1)
                                throw new EmptyDescriptionException(command.getAction());
                            throw new WrongDescriptionException(command.getAction());
                        } catch (EmptyDescriptionException error) {
                            print("The description of 'done' should not be empty.");
                            print("Please re-enter your command.");
                        } catch (WrongDescriptionException error){
                            print("The description of 'done' should be an integer.");
                            print("Please re-enter your command.");
                        }
                    }
                    break;
                case TODO:
                    addTodo(command.getTaskContent());
                    break;
                case DEADLINE:
                    try {
                        addDeadline(command.getTaskContent());
                    } catch (WrongDescriptionException e){
                        print("The format of 'deadline' is:");
                        print("> deadline TASK /by TIME");
                        print("Please re-enter your command.");
                    }
                    break;
                case EVENT:
                    try {
                        addEvent(command.getTaskContent());
                    } catch (WrongDescriptionException e){
                        print("The format of 'event' is:");
                        print("> event TASK /at TIME");
                        print("Please re-enter your command.");
                    }
                    break;
                case DELETE:
                    try {
                        int index = Integer.parseInt(command.getTaskContent()) - 1;
                        deleteTask(index);
                    } catch (IndexOutOfBoundsException e){
                        print("Sorry, I can't do that for you.");
                        print("You only have " + String.format("%d",todoList.size()) + " tasks on your list.");
                    } catch (Exception e) {
                        try {
                            String tmp = command.getTaskContent();
                            if (tmp.length() < 1)
                                throw new EmptyDescriptionException(command.getAction());
                            throw new WrongDescriptionException(command.getAction());
                        } catch (EmptyDescriptionException error) {
                            print("The description of 'delete' should not be empty.");
                            print("Please re-enter your command.");
                        } catch (WrongDescriptionException error){
                            print("The description of 'delete' should be an integer.");
                            print("Please re-enter your command.");
                        }
                    }
                    break;
                case INVALID:
                    throw new CommandException(command.echo() + " is an invalid command.\n"+
                                                "please try another one.");
                default:
                    System.out.println("This default situation should not happen. Please contact the idiot programmer.");

            }
        }
        catch (CommandException e){
            print(command.echo() + " is an invalid command.");
            print("please try another one.");
        }
        catch (EmptyDescriptionException e){
            print("The description of '"+command.getAction()+"' should not be empty.");
            print("Please re-enter your command.");
        }
        catch (Exception e){
            System.out.println("This situation is not supposed to happen. Please contact the idiot programmer.");
        }

    }

    private void addTodo(String taskContent) throws EmptyDescriptionException{
        if (taskContent.length() < 1)
            throw new EmptyDescriptionException("");
        Task task = new Todo(taskContent);
        this.todoList.add(task);
        print("The following task has been added to your list:");
        print("  [T][ ] "+task.toString());
        print(String.format("Now you have %d tasks in your list.", todoList.size()));
        this.undoneCount++;
        print(String.format("There are %d tasks waiting to be done.", this.undoneCount));
    }

    private void addDeadline(String taskContent) throws WrongDescriptionException, EmptyDescriptionException{
        if (taskContent.length() < 1)
            throw new EmptyDescriptionException("");
        try {
            String[] splitedContent = taskContent.split("/by");
            Task task = new Deadline(splitedContent[0], splitedContent[1]);
            this.todoList.add(task);
            print("The following task has been added to your list:");
            print("  [D][ ] "+task.toString());
            print(String.format("Now you have %d tasks in your list.", todoList.size()));
            this.undoneCount++;
            print(String.format("There are %d tasks waiting to be done.", this.undoneCount));
        } catch (Exception e){
            throw new WrongDescriptionException("");
        }
    }

    private void addEvent(String taskContent) throws WrongDescriptionException, EmptyDescriptionException{
        if (taskContent.length() < 1)
            throw new EmptyDescriptionException("");
        try {
            String[] splitedContent = taskContent.split("/at");
            Task task = new Event(splitedContent[0],splitedContent[1]);
            this.todoList.add(task);
            print("The following task has been added to your list:");
            print("  [E][ ] "+task.toString());
            print(String.format("Now you have %d tasks in your list.", todoList.size()));
            this.undoneCount++;
            print(String.format("There are %d tasks waiting to be done.", this.undoneCount));
        } catch (Exception e){
            throw new WrongDescriptionException("");
        }
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

    private void finishTask(int index) throws IndexOutOfBoundsException{
        try {
            Task task = todoList.get(index);
            this.undoneCount--;
            print("Nice! I've marked following task as done:");
            task.closeTask();
            print("  ["+task.getTaskType()+"][X] " + task.toString());
            print(String.format("Now you have %d tasks waiting to be done.", this.undoneCount));
        } catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException();
        }
    }

    private void deleteTask(int index) throws IndexOutOfBoundsException{
        try {
            Task task = todoList.get(index);
            if (!task.checkDone())
                this.undoneCount--;
            todoList.remove(index);
            print("Nice! I've deleted following task as done:");
            if (task.checkDone())
                print("  ["+task.getTaskType()+"][X] " + task.toString());
            else
                print("  ["+task.getTaskType()+"][ ] " + task.toString());
            print(String.format("Now you have %d tasks waiting to be done.", this.undoneCount));
        } catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException();
        }
    }

    private void print(String str){
        System.out.println("    " + str);
    }

    private void print(String prefix, String str){
        System.out.println(prefix+str);
    }
}