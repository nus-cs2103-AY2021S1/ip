import java.util.Scanner;

public class Duke {
    private static final String INDENT = "    ";
    private static final String LINE = "____________________________________________________________";

    private static void formatResponse(String[] response) {
        System.out.println(INDENT + LINE);
        for (String resp: response) {
            System.out.println(INDENT + " " + resp);
        }
        System.out.println(INDENT + LINE);
        System.out.println();
    }

    private static void formatResponse(String response) {
        System.out.println(INDENT + LINE);
        System.out.println(INDENT + " " + response);
        System.out.println(INDENT + LINE);
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        formatResponse(new String[]{"Hello! I'm Duke", "What can I do for you?"});
        String display = "";
        while (!display.equals("bye")) {
            display = sc.nextLine();
            if (!display.equals("bye")) {
                formatResponse(display);
            }
        }
       formatResponse("Bye. Hope to see you again soon!");
       sc.close();
    }
}
