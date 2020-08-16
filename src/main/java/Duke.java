import java.util.Scanner;

public class Duke {
    /**
     * get_input() is in charge of asking for input from user
     * and return the input as a string to be used for something else.
     * @param sc Scanner object
     * @return a string representing user input
     */
    public static String get_input(Scanner sc) {
        String user_input = sc.nextLine().trim(); // Read user input
        return user_input;
    }

    /**
     * get_output(String) decides what Hal's output should be
     * based on user's input.
     * @param user_input input from user as String
     * @return output by Hal9000
     */
    public static String get_output(String user_input) {
        return user_input; // level 1
    }

    public static String format_response(String output_msg) {
        return 
        "____________________________________________________________\n"+
        output_msg + "\n" +
        "____________________________________________________________\n";
    }

    public static void hal9000_op() {
        boolean end = false;
        Scanner sc = new Scanner(System.in);
        while (!end) {
            String input = get_input(sc);
            if (input.compareTo("bye") == 0) {
                end = true;
            } else {
                String output_string = format_response(get_output(input));
                System.out.println(output_string);
            }
        }
        System.out.println(format_response("Bye. Hope to see you again soon!"));
    }
    
    public static void main(String[] args) {
        String logo =
"   __ _____   __  ___  ___  ___  ___\n" +
"  / // / _ | / / / _ \\/ _ \\/ _ \\/ _ \\\n" +
" / _  / __ |/ /__\\_, / // / // / // /\n" +
"/_//_/_/ |_/____/___/\\___/\\___/\\___/\n";
                                     
        // Intro message
        System.out.println(logo);
        System.out.println(format_response(
            "Hello! I'm Hal9000\nWhat can I do for you?"
        ));

        hal9000_op();

    }
}
