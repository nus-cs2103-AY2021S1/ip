import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a user interface and is in charge of printing contents to screen
 */
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

    /**Reads incoming commands and use a parser to pase it.
     *
     * @param parser a parser used to parse incoming commands
     * @return the User interface itself
     */
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

    /**
     * Prints all the tasks stored in memory
     *
     * @param todoList a list contains all the tasks
     */
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

    /**
     * Print TodoTasks to screen
     *
     * @param taskContent content of todo task
     * @param size total number of tasks left in the list
     * @param undoneCount number of tasks in the list that has not been done.
     */
    static public void printTodoTask(String taskContent, int size, int undoneCount){
        Ui.print("The following task has been added to your list:");
        Ui.print("  [T][ ] " + taskContent);
        Ui.print(String.format("Now you have %d tasks in your list.", size));
        Ui.print(String.format("There are %d tasks waiting to be done.", undoneCount));
    }

    /**
     * Print DeadlineTasks to screen
     *
     * @param taskContent content of deadline task
     * @param size total number of tasks left in the list
     * @param undoneCount number of tasks in the list that has not been done.
     */
    static public void printDeadlineTask(String taskContent, int size, int undoneCount){
        Ui.print("The following task has been added to your list:");
        Ui.print("  [D][ ] " + taskContent);
        Ui.print(String.format("Now you have %d tasks in your list.", size));
        Ui.print(String.format("There are %d tasks waiting to be done.", undoneCount));
    }

    /**
     * Print EventTasks to screen
     *
     * @param taskContent content of event task
     * @param size total number of tasks left in the list
     * @param undoneCount number of tasks in the list that has not been done.
     */
    static public void printEventTask(String taskContent, int size, int undoneCount){
        Ui.print("The following task has been added to your list:");
        Ui.print("  [E][ ] " + taskContent);
        Ui.print(String.format("Now you have %d tasks in your list.", size));
        Ui.print(String.format("There are %d tasks waiting to be done.", undoneCount));
    }

    /**
     * Inform the used a task is deleted and print the information on screen
     *
     * @param task the deleted task
     * @param undoneCount number of tasks in the list that has not been done.
     */
    static public void printDelete(Task task, int undoneCount) {
        Ui.print("Nice! I've deleted following task:");
        if (task.checkDone())
            Ui.print("  ["+task.getTaskType()+"][X] " + task.toString());
        else
            Ui.print("  ["+task.getTaskType()+"][ ] " + task.toString());
        Ui.print(String.format("Now you have %d tasks waiting to be done.", undoneCount));
    }

    /**
     * Inform the used a task is done and print the information on screen
     *
     * @param task the finished task
     * @param undoneCount number of tasks in the list that has not been done.
     */
    static public void printDone(Task task, int undoneCount) {
        Ui.print("Nice! I've marked following task as done:");
        if (task.checkDone())
            Ui.print("  ["+task.getTaskType()+"][X] " + task.toString());
        else
            Ui.print("  ["+task.getTaskType()+"][ ] " + task.toString());
        Ui.print(String.format("Now you have %d tasks waiting to be done.", undoneCount));
    }

    static public void printFind(ArrayList<Task> allMatches){
        Ui.print("Here are the matching tasks in your list:");
        for (int i = 0; i < allMatches.size(); i++){
            Task task = allMatches.get(i);
            if (task.checkDone())
                Ui.print(String.format("%d.[", i+1)+task.getTaskType()+"][X] " + task.toString());
            else
                Ui.print(String.format("%d.[", i+1)+task.getTaskType()+"][ ] " + task.toString());
        }
    }
}
