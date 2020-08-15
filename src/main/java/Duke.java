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
            String next = sc.nextLine();
            String [] arr = next.split(" ");

            if (next.equals("")) {
                System.exit(0);
                sc.close();
            } else if (next.equals("bye")) {
                layout.print("Bye. Hope to see you again soon!");
            } else if (next.equals("list")) {
                tasks.showTasks();
            } else if (arr[0].equals("done")) {
                tasks.markDone(arr[1]);
            } else {
                tasks.addTask(next);
            }
        }


    }
}
