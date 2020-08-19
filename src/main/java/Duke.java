import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Task thisTask;
        String input;
        int number;

        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
        System.out.println("    Hello from\n" + logo);
        System.out.println("    Hey there! This is Duke here(๑´•.̫ • `๑)~");
        System.out.println("    How may I help you today?");
        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");

        input = sc.nextLine();
        while(!input.equals("bye")) {
            System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    thisTask = list.get(i - 1);
                    System.out.println("     " + i + ".[" + thisTask.getStatusIcon() + "] "
                            + thisTask.getTaskname());
                }
            } else if (input.startsWith("done")) {
                number = Character.getNumericValue(input.charAt(input.length() - 1));
                System.out.println("Nice! I've marked this task as done:");
                thisTask = list.get(number - 1);
                list.set(number - 1, thisTask.markAsDone());
                System.out.println("       [" + "\u2713" + "] " + input);
            } else {
                System.out.println("     added: " + input);
                list.add(new Task(input, false));
            }
            System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
            input = sc.nextLine();
        }

        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
        System.out.println("    Bye bye~ See ya!");
        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
    }
}