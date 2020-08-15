import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String SPACES = "____________________________________________________________";
        System.out.println("Hello from\n" + logo);
        welcomeMessage(SPACES);


    }

    public static void botTemplate(String content, String delim) {
        System.out.println(delim);
        System.out.println(content);
        System.out.println(delim);
        System.out.println();
    }

    public static void welcomeMessage(String delim) {
        botTemplate("Hello! I'm Duke\n What can I do for you? ", delim);
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {
            String next = sc.nextLine();
            if (next.equals("bye")) {
                botTemplate("Bye! Message me anytime!",delim);
                break;
            } else {
                botTemplate(next,delim);
            }
        }
    }
}
