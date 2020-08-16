import java.util.Scanner;

public class Duke {
    Scanner sc;
    TaskList tasks;

    public Duke() {
        this.sc = new Scanner(System.in);
        this.tasks = new TaskList();
    }

    void takeInputs() {
        boolean quit = false;
        while (!quit) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                quit = true;
                System.out.println("cya");
            } else if (input.equals("list")) {
                System.out.println(tasks);
            } else {
                System.out.println("added: " + input);
                tasks.addItem(input);
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
