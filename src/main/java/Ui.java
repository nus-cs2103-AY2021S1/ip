import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private void welcome(){
        String logo =   "_|_|_|_|_|    _|_|                _|_|_|      _|_|     _|  \n" +
                        "    _|      _|    _|              _|    _|  _|    _|   _|  \n" +
                        "    _|      _|    _|  _|_|_|_|_|  _|    _|  _|    _|   _|  \n" +
                        "    _|      _|    _|              _|    _|  _|    _|       \n" +
                        "    _|        _|_|                _|_|_|      _|_|     _|  ";
        System.out.println("Hello from\n" + logo);
        System.out.println("This is a Duke Project.\n");
        System.out.println("What you are going to do today?");
    }

    public Ui read(Parser parser){
        welcome();
        Scanner sc = new Scanner(System.in);
        Command command = Command.INIT;
        while (command != Command.BYE){      //when last command was bye
            System.out.print("> ");
            command = Command.getCommand(sc.nextLine());
            try {
                parser.runCommand(command);
            } catch (Exception e){
                showExceptionContent(e.getMessage());
            }
        }
        sc.close();
        return this;
    }

    static public void printList(ArrayList<Task> todoList){
        for (int i = 0; i < todoList.size(); i++){
            Task task = todoList.get(i);
            if (task.checkDone())
                print(i+1 + ".["+task.getTaskType()+"][X] " + task.toString());
            else
                print(i+1 + ".["+task.getTaskType()+"][ ] " + task.toString());
        }
    }

    static public void print(String str){
        for (String s:str.split("\n"))
            System.out.println("    " + s);
    }

    public void showExceptionContent(String err){
        print(err);
    }

    static public void printTodoTask(String taskContent, int size, int undoneCount){
        Ui.print("The following task has been added to your list:");
        Ui.print("  [T][ ] " + taskContent);
        Ui.print(String.format("Now you have %d tasks in your list.", size));
        Ui.print(String.format("There are %d tasks waiting to be done.", undoneCount));
    }

    static public void printDeadlineTask(String taskContent, int size, int undoneCount){
        Ui.print("The following task has been added to your list:");
        Ui.print("  [D][ ] " + taskContent);
        Ui.print(String.format("Now you have %d tasks in your list.", size));
        Ui.print(String.format("There are %d tasks waiting to be done.", undoneCount));
    }

    static public void printEventTask(String taskContent, int size, int undoneCount){
        Ui.print("The following task has been added to your list:");
        Ui.print("  [E][ ] " + taskContent);
        Ui.print(String.format("Now you have %d tasks in your list.", size));
        Ui.print(String.format("There are %d tasks waiting to be done.", undoneCount));
    }

    static public void printDelete(Task task, int undoneCount) {
        Ui.print("Nice! I've deleted following task:");
        if (task.checkDone())
            Ui.print("  ["+task.getTaskType()+"][X] " + task.toString());
        else
            Ui.print("  ["+task.getTaskType()+"][ ] " + task.toString());
        Ui.print(String.format("Now you have %d tasks waiting to be done.", undoneCount));
    }


    static public void printDone(Task task, int undoneCount) {
        Ui.print("Nice! I've marked following task as done:");
        if (task.checkDone())
            Ui.print("  ["+task.getTaskType()+"][X] " + task.toString());
        else
            Ui.print("  ["+task.getTaskType()+"][ ] " + task.toString());
        Ui.print(String.format("Now you have %d tasks waiting to be done.", undoneCount));
    }
}
