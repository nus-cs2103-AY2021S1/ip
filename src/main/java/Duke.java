import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    public interface Printable{
        public String print();
    }
    public static void main(String[] args) {
        List<String> tasks = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Printable greeting = () -> "Hello! I'm Duke \nWhat can I do for you?";
        Printable goodbye = () -> "Bye. Hope to see you again soon!";
        speak(greeting);
        Printable input;
        Scanner sc = new Scanner(System.in);

        do {
            input = getUserInput(sc);
            String command = input.print();
            if (command.toLowerCase().equals("bye")) {
                speak(goodbye);
                break;
            } else if (command.toLowerCase().equals("list")) {
                listTasks(tasks);
            } else {
                storeInput(command,tasks);
            }
        } while (true);
    }

    public static void speak(Printable printable) {
        System.out.println("------------------------------------------------------");
        System.out.println(printable.print() + "\n");
        System.out.println("------------------------------------------------------");
    }

    public static Printable getUserInput(Scanner sc) {
        return () -> sc.nextLine();
    }

    public static void storeInput(String command, List<String> tasks) {
        tasks.add(command);
        Printable userReply = () -> String.format("added: %s",command);
        speak(userReply);
        return;
    }

    public static void listTasks (List<String> tasks) {
        int i;
        StringBuilder sb = new StringBuilder();
        for (i = 0 ; i < tasks.size() ; i++) {
            if (i==tasks.size()-1) {
                sb.append(String.format("%d. %s",i+1,tasks.get(i)));
            } else {
                sb.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
            }
        }
        Printable task = () -> sb.toString();
        speak(task);
        return;
     }


}
