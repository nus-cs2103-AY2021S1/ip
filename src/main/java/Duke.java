import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static final String GREETING = "Hello! I'm Duke";
    static final String PROMPT = "What can I do for you?";
    static final String EXIT = "Bye. Hope to see you again soon!";


    public static void main(String[] args) {
        new Duke().repl();
    }

    public void repl() {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean isActive = true;

        print(GREETING, PROMPT);
        while(isActive) {
            input = sc.nextLine();
            isActive = !input.equalsIgnoreCase("bye");

            if(isActive) print(input);
        }
        print(EXIT);
    }

    private void print(List<String> strings) {
        final String INDENT = "\t";
        final String SEPARATOR = "_".repeat(69);

        System.out.println(INDENT + SEPARATOR);
        for(String s: strings) {
            System.out.println(INDENT + INDENT + s);
        }
        System.out.println(INDENT + SEPARATOR + "\n");
    }

    private void print(String ...strings) {
        print(Arrays.asList((strings)));
    }
}
