import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class Duke {
    public static String getFirstWord(String command) {
        return command.contains(" ") ? command.split(" ")[0] : command;
    }

    public static void main(String[] args) {
        System.out.println("My name? You don't need to know that. \nStop bothering me already... What do you want??");
//        BufferedReader reader =
//                new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        // allow multiple termination commands
        Set<String> terminationCommands = new HashSet<String>();
        terminationCommands.add("bye");
        terminationCommands.add("toodles");
        terminationCommands.add("farewell");
        terminationCommands.add("sayonara");

//        ArrayList<Task> tasks = new ArrayList<Task>();
        String[] tasks = new String[100];
        int iter = 0;
//        while (!command.equals("bye")) {
        while (!terminationCommands.contains(command)) {
            if (command.equals("list")) {
                for (int i = 0; i < iter; i++) {
                    System.out.println(i+1 + ". " + tasks[i]);
//                for (int i = 0; i < tasks.size(); i++) {
//                    System.out.println(i+1 + ". " + tasks.get(i));
                }
                System.out.println("I wouldn't bother doing them if I were you.");
//            } else if (getFirstWord(command).equals("done")) {
//                int taskNumber = parseInt(command.split(" ")[1]) - 1;
//                tasks.get(taskNumber).markAsDone();
//                System.out.println("Oh goody... You actually accomplished something!!\n" +
//                        tasks.get(taskNumber));
            } else {
                System.out.println("You're making me feel tired... " + command);
//                tasks.add(new Task(command));
                tasks[iter] = command;
                iter++;
            }
            command = sc.nextLine();
        }
        System.out.println("Finally... don't come back if you can possibly help it, please.");
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }
}
