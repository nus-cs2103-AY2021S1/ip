import ip.src.main.java.Layout;
import ip.src.main.java.Tasks;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Layout layout = new Layout();
        Tasks tasks = new Tasks();

        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm" + "\n" + logo + "\n\t" + "What can I do for you?";
        layout.print(greeting);

        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String [] arr = sc.nextLine().split(" ");

            switch(arr[0]) {
                case "" :
                    System.exit(0);
                    sc.close();
                    break;
                case "bye":
                    layout.print("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    tasks.showTasks();
                    break;
                case "done":
                    tasks.markDone(arr[1]);
                    break;
                case "deadline":
                    tasks.addTask("deadline", arr);
                    break;
                case "event":
                    tasks.addTask("event", arr);
                    break;
                case "todo":
                    tasks.addTask("todo", arr);
                    break;
                default:
                    layout.print("I do not understand");
            }
        }
    }
}
