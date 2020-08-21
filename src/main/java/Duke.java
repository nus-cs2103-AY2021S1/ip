import ip.src.main.java.DukeException;
import ip.src.main.java.Layout;
import ip.src.main.java.Tasks;

import java.util.Scanner;

public class Duke {
    static Layout layout = new Layout();
    static Tasks tasks = new Tasks();
    
    public static void greet() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm" + "\n" + logo + "\n\t" + "What can I do for you?";
        layout.print(greeting);
    }
    
    public static void getCommands() {
        String [] arr = new String[] {
                "Here are all your commands:",
                "list- list all tasks",
                "todo <description> - add task",
                "deadline <description> \\by <due date> -add task with deadline",
                "event <description> \\at <event date> -add event with date",
                "done <task number> - marks task as done",
                "bye - goodbye!"
        };
        layout.printCommands(arr);
    }
    
    public static void main(String[] args) {
        greet();
        
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String [] arr = sc.nextLine().split(" ");

            switch(arr[0]) { 
            case "bye":
                layout.print("Bye. Hope to see you again soon!");
                System.exit(0);
                sc.close();
                break;
            case "list":
                tasks.showTasks();
                break;
            case "done":
                tasks.modifyTask(Tasks.Action.DONE, arr[1]);
                break;
            case "delete":
                tasks.modifyTask(Tasks.Action.DELETE, arr[1]);
                break;
            case "help": 
                getCommands();
                break;
            case "deadline":
                tasks.addTask(Tasks.Type.DEADLINE, arr);
                break;
            case "event":
                tasks.addTask(Tasks.Type.EVENT, arr);
                break;
            case "todo":
                tasks.addTask(Tasks.Type.TODO, arr);
                break;
            default:
                DukeException e = new DukeException("I do not understand your command");
                layout.print(e.getMessage());
            }
        }
    }
}
