import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println(line());
        System.out.println(greet());
        System.out.println(line());
        while (sc.hasNext()) {
            String input = sc.nextLine();
            System.out.println(line());
            if (input.equals("bye")) {
                System.out.println(exit());
                System.out.println(line());
                System.exit(0);
            } else {
                    System.out.println(echo(input));
            }
            System.out.println(line());
        }
    }

    private String greet() {
        return "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n";
    }

    private String echo(String str) {
        return "\t" + str + '\n';
    }

    private String exit() {
        return "\tBye. Hope to see you again soon!\n";
    }

    private String line() {
        StringBuilder str = new StringBuilder("\t");
        for (int i = 0; i < 50; i++) {
            str.append("-");
        }
        return str.toString();
    }

}
