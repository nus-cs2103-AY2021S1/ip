import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Duke {
    public static void main(String[] args) {
        System.out.println("My name? You don't need to know that. \nStop bothering me already... What do you want??");
//        BufferedReader reader =
//                new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        String command = sc.next();

        Set<String> terminationCommands = new HashSet<String>();
        terminationCommands.add("bye");
        terminationCommands.add("toodles");
        terminationCommands.add("farewell");
        terminationCommands.add("sayonara");
//        while (!command.equals("bye")) {
        while (!terminationCommands.contains(command)) {
            System.out.println(command);
            command = sc.next();
        }
        System.out.println("Finally... don't come back if you can possibly help it, jeez.");
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }
}
