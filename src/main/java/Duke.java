import java.util.Scanner;
enum Commands {
    LIST("list"), BYE("bye");
    private String action;

    public String getAction() {
        return this.action;
    }
    private Commands(String action) {
        this.action = action;
    }
};
public class Duke {
    private String[] taskStorage;
    private int index;
    Duke() {
        this.taskStorage = new String[100];
        this.index = 0;
    }
    private void addTask(String task) {
        this.taskStorage[this.index++] = task;
    }
    static void printDialog(String content) {
        System.out.println("    ----------------------------------------");
        System.out.println("    " + content + "\n");
        System.out.println("    ----------------------------------------");
    }
    private void printStoredTasks() {
        String result = "";
        for(int i= 0; i < this.index; i++) {
            result += ((i+1) + ". " + this.taskStorage[i]);
            if(i < this.index - 1) result += "\n    ";
        }
        printDialog(result);
    }
    public static void main(String[] args) {
        Duke dk = new Duke();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printDialog("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while(true) {
            String content = sc.nextLine();
            if(content.equals(Commands.BYE.getAction())) {
                printDialog("Bye. Hope to see you again soon!");
                break;
            }
            if(content.equals(Commands.LIST.getAction())) {
                dk.printStoredTasks();
            }
            else if(!content.equals("")) {
                dk.addTask(content);
                printDialog(content);
            }
        }
    }
}
