import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = "";
//                " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String breaker = "\t_________________________________________";
        String greetings = breaker + "\n\tHello! I'm Duke" +
                "\n\tWhat can I do for you?\n" + breaker;
        String exit = "Bye. Hope to see you again soon!";

        System.out.println(greetings);

        Scanner sc = new Scanner(System.in);
        String input = "";
        while (sc.hasNext()) {
            input = sc.nextLine();
            if (!input.equals("bye")) {
                System.out.println(breaker + "\n\t" + input + "\n" + breaker);
            } else {
                System.out.println(breaker + "\n\t" + exit + "\n" + breaker);
                break;
            }
        }
        sc.close();
    }
}
