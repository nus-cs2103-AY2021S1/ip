import java.util.Scanner;

public class Duke {
    Scanner sc = new Scanner(System.in);

    void takeInputs() {
        boolean quit = false;
        while (!quit) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                quit = true;
                System.out.println("cya");
            } else {
                System.out.println(input);
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        
        Duke duke = new Duke();
        duke.takeInputs();
    }
}
