import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "------------------------";

        System.out.println(line);
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        System.out.println("\n" + line);

        Scanner sc = new Scanner(System.in);
        List<String> cmds = new ArrayList<>();
        while(true) {
            System.out.print("your input: ");
            String cmd = sc.nextLine();
            System.out.println("\n" + line);
            if (cmd.equals("bye")) {
                end();
                System.out.println(line + "\n");
                break;
            } else if (cmd.equals("list")) {
                list(cmds);
                System.out.println(line + "\n");
            } else {
                System.out.println("added: " + cmd);
                cmds.add(cmd);
                System.out.println(line + "\n");
            }
        }
    }

    private static void end() {
         System.out.println("Bye. Hope to see you again soon!");       
    }

    private static void list(List<String> cmds) {
        for (int i = 0; i < cmds.size(); i++) {
            System.out.println((i + 1) + ". " + cmds.get(i));
        }
        System.out.println();
    }
}
