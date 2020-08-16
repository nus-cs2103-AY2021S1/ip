import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Duke.run();
    }
    public static void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String INITIAL_PRINTING = "Hello! I'm Duke\n" + "What can I do for you?\n" + "____________________________________________________________\n";
        System.out.println(INITIAL_PRINTING);
        Duke.repeat();
    }
    public static void repeat() {
        String answer = "";
        Scanner sc = new Scanner(System.in);
        do {
            answer = sc.nextLine();
            System.out.println(answer);
            answer = answer.toLowerCase();
        } while (!answer.equals("bye"));
    }

}
