package Duke;

import java.util.ArrayList;

/**
 * Represents a user interface and is in charge of printing contents to screen
 */
public class Ui {

    public void welcome(){
        String logo =   "_|_|_|_|_|    _|_|                _|_|_|      _|_|     _|  \n" +
                        "    _|      _|    _|              _|    _|  _|    _|   _|  \n" +
                        "    _|      _|    _|  _|_|_|_|_|  _|    _|  _|    _|   _|  \n" +
                        "    _|      _|    _|              _|    _|  _|    _|       \n" +
                        "    _|        _|_|                _|_|_|      _|_|     _|  ";
        System.out.println("Hello from\n" + logo);
        System.out.println("This is a Duke.Duke Project.\n");
        System.out.println("What you are going to do today?");
    }

    /**Reads incoming commands and use a parser to pase it.
     *
     * @param parser a parser used to parse incoming commands
     * @return the User interface itself
     */
    public String read(String input, Parser parser){
        Command command = Command.INIT;
        command = Command.getCommand(input);
        try {
            return parser.runCommand(command);
        } catch (Exception e){
            return showExceptionContent(e.getMessage());
        }
    }

    /**
     * Prints all the tasks stored in memory
     *
     * @param todoList a list contains all the tasks
     */
    static public String printList(ArrayList<Task> todoList){
        if (todoList.size() == 0)
            return "There is no task in your list now.";
        String tmp="";
        for (int i = 0; i < todoList.size(); i++){
            Task task = todoList.get(i);
            if (task.checkDone())
                tmp = tmp + print(i+1 + ".["+task.getTaskType()+"][X] " + task.toString());
            else
                tmp = tmp + print(i+1 + ".["+task.getTaskType()+"][ ] " + task.toString())+ "\n";
        }

        return tmp;
    }

    static public String print(String str){
        String tmp = "";

        for (String s:str.split("\n")) {
            tmp = tmp + s + "\n";
            System.out.println("    " + s);
        }

        assert tmp.compareTo("")!=0: "An empty string is passed to print()";
        return tmp;
    }

    public String showExceptionContent(String err){
        return print(err);
    }

    /**
     * Print TodoTasks to screen
     *
     * @param taskContent content of todo task
     * @param size total number of tasks left in the list
     * @param undoneCount number of tasks in the list that has not been done.
     */
    static public String printTodoTask(String taskContent, int size, int undoneCount){
        return (Ui.print("The following task has been added to your list:")+
                Ui.print("  [T][ ] " + taskContent)+
                Ui.print(String.format("Now you have %d tasks in your list.", size))+
                Ui.print(String.format("There are %d tasks waiting to be done.", undoneCount)));
    }

    /**
     * Print DeadlineTasks to screen
     *
     * @param taskContent content of deadline task
     * @param size total number of tasks left in the list
     * @param undoneCount number of tasks in the list that has not been done.
     */
    static public String printDeadlineTask(String taskContent, int size, int undoneCount){
        return (Ui.print("The following task has been added to your list:")+
                Ui.print("  [D][ ] " + taskContent)+
                Ui.print(String.format("Now you have %d tasks in your list.", size))+
                Ui.print(String.format("There are %d tasks waiting to be done.", undoneCount)));
    }

    /**
     * Print EventTasks to screen
     *
     * @param taskContent content of event task
     * @param size total number of tasks left in the list
     * @param undoneCount number of tasks in the list that has not been done.
     */
    static public String printEventTask(String taskContent, int size, int undoneCount){
        return (Ui.print("The following task has been added to your list:")+
                Ui.print("  [E][ ] " + taskContent)+
                Ui.print(String.format("Now you have %d tasks in your list.", size))+
                Ui.print(String.format("There are %d tasks waiting to be done.", undoneCount)));
    }

    /**
     * Inform the used a task is deleted and print the information on screen
     *
     * @param task the deleted task
     * @param undoneCount number of tasks in the list that has not been done.
     */
    static public String printDelete(Task task, int undoneCount) {
        String tmp = Ui.print("Nice! I've deleted following task:");
        if (task.checkDone())
            tmp = tmp + Ui.print("  ["+task.getTaskType()+"][X] " + task.toString());
        else
            tmp = tmp +Ui.print("  ["+task.getTaskType()+"][ ] " + task.toString());
        return (tmp + Ui.print(String.format("Now you have %d tasks waiting to be done.", undoneCount)));
    }

    /**
     * Inform the used a task is archived and print additional information on screen
     *
     * @param task the deleted task
     * @param undoneCount number of tasks in the list that has not been done.
     */
    static public String printArchive(Task task, int undoneCount) {
        String tmp = Ui.print("Nice! I've achived following task:");
        if (task.checkDone())
            tmp = tmp + Ui.print("  ["+task.getTaskType()+"][X] " + task.toString());
        else
            tmp = tmp +Ui.print("  ["+task.getTaskType()+"][ ] " + task.toString());
        return (tmp + Ui.print(String.format("Now you have %d tasks waiting to be done.", undoneCount)));
    }

    /**
     * Inform the used a task is done and print the information on screen
     *
     * @param task the finished task
     * @param undoneCount number of tasks in the list that has not been done.
     */
    static public String printDone(Task task, int undoneCount) {
        String tmp = Ui.print("Nice! I've marked following task as done:");
        if (task.checkDone())
            tmp = tmp + Ui.print("  ["+task.getTaskType()+"][X] " + task.toString());
        else
            tmp = tmp + Ui.print("  ["+task.getTaskType()+"][ ] " + task.toString());
        return (tmp + Ui.print(String.format("Now you have %d tasks waiting to be done.", undoneCount)));
    }

    static public String printFind(ArrayList<Task> allMatches){
        String tmp = Ui.print("Here are the matching tasks in your list:");
        for (int i = 0; i < allMatches.size(); i++){
            Task task = allMatches.get(i);
            if (task.checkDone())
                tmp = tmp + Ui.print(String.format("%d.[", i+1)+task.getTaskType()+"][X] " + task.toString());
            else
                tmp = tmp + Ui.print(String.format("%d.[", i+1)+task.getTaskType()+"][ ] " + task.toString());
        }
        return tmp;
    }
}
