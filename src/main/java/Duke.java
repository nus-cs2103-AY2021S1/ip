import java.util.Scanner;

enum Commands {
    LIST("list"), BYE("bye"), DONE("done"), DEADLINE("deadline"), EVENT("event"), TODO("todo");
    private String action;

    public String getAction() {
        return this.action;
    }
    private Commands(String action) {
        this.action = action;
    }
};
public class Duke {
    private Task[] taskStorage;
    private int index;
    Duke() {
        this.taskStorage = new Task[100];
        this.index = 0;
    }
    private void addTask(String task) {
        Task newTask = new Task(task);
        this.taskStorage[this.index++] = newTask;
    }
    static void printDialog(String content) {
        System.out.println("    ----------------------------------------");
        System.out.println("    " + content + "\n");
        System.out.println("    ----------------------------------------");
    }

    static String furtherProcessing(Commands commandType, String[] tokens, Duke dk) {
        String content = "";
        String result_prefix = "Got it. I've added this task:\n      ";
        String result_subfix = "Now you have " + (dk.index + 1) + " tasks in the list.";
        String main_content = "";
        for(int i = 1;i < tokens.length; i++) {
            if(tokens[i].charAt(0) == '/')
                break;

            content += tokens[i];
        }
        if(commandType == Commands.DEADLINE) {
            String deadline = tokens[tokens.length - 1];
            Deadline deadlineTask = new Deadline(content, deadline);
            dk.taskStorage[dk.index++] = deadlineTask;
            main_content = deadlineTask.returnStringForm();
        } else if(commandType == Commands.EVENT) {
            String deadline = tokens[tokens.length - 1];
            Event eventTask = new Event(content, deadline);
            dk.taskStorage[dk.index++] = eventTask;
            main_content = eventTask.returnStringForm();
        } else if(commandType == Commands.TODO) {
            Todo todoTask = new Todo(content);
            dk.taskStorage[dk.index++] = todoTask;
            main_content = todoTask.returnStringForm();
        }
        return result_prefix + main_content + "\n    " + result_subfix;
    }

    static String processedCommand(String command, Duke dk) {
        if(command.equals("")) return "";
        String[] tokens = command.split(" ");
        if(tokens[0].equals(Commands.DONE.getAction())) {
            int mark_number = Integer.parseInt(tokens[1]);
            Task marked = dk.taskStorage[mark_number - 1];
            marked.markAsDone();
            return "Nice! I've marked this task as done:\n      " + marked.returnStringForm();
        } else if(tokens[0].equals(Commands.TODO.getAction())) {
            return furtherProcessing(Commands.TODO, tokens, dk);
        } else if(tokens[0].equals(Commands.DEADLINE.getAction())) {
            return furtherProcessing(Commands.DEADLINE, tokens, dk);
        } else if(tokens[0].equals(Commands.EVENT.getAction())) {
            return furtherProcessing(Commands.EVENT, tokens, dk);
        }
        else {
            dk.addTask(command);
        }
        return command;
    }
    private void printStoredTasks() {
        String result = "Here are the tasks in your list:\n    ";
        for(int i= 0; i < this.index; i++) {
            result += ((i+1) + "." + this.taskStorage[i].returnStringForm());
            if(i < this.index - 1) result += "\n    ";
        }
        printDialog(result);
    }
    public static void main(String[] args) {
        Duke dk = new Duke();
       /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        printDialog("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while(true) {
            String content = sc.nextLine();
            if(content.equals(Commands.BYE.getAction())) {
                printDialog("Bye. Hope to see you again soon!");
                break;//exit the program
            }
            if(content.equals(Commands.LIST.getAction())) {
                dk.printStoredTasks();
            } else  {
                String result = processedCommand(content, dk);
                if(!result.equals("")) printDialog(result);
            }
        }
    }
}
