import java.util.*;

public class Duke {
    private String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private String outputFormat = "  %s\n";
    private boolean ongoing;
    private ArrayList<String> todoList;

    Duke() {
        ongoing = false;
        todoList = new ArrayList<>();
    }

    public void run() {
        ongoing = true;
        greeting();
        Scanner scanner = new Scanner(System.in);
        while (ongoing) {
            String input = scanner.nextLine();
            systemOutput(input);
        }
    }

    public void systemOutput(String input) {
        System.out.println(line + "\n");
        scenarios(input);
        System.out.println("\n" + line);
    }

    public void scenarios(String input) {
        if (input.equals("bye")) {
            ongoing = false;
            goodBye();
        } else if (input.equals("list")) {
            showList();
        } else {
            addItem(input);
        }
    }

    public void showList() {
        int count = 1;
        System.out.printf(outputFormat, "The tasks in your Todo List: ");
        for (String item : todoList) {
            System.out.printf(outputFormat, Integer.toString(count) + ". " + item);
            count += 1;
        }
    }

    public void addItem(String input) {
        todoList.add(input);
        System.out.printf(outputFormat, "New todo item added: " + input);
    }

    public void greeting() {
        System.out.printf(outputFormat, line + "\n         (^v^)");
        System.out.printf(outputFormat, "Hey there! I'm JavaDuke");
        System.out.printf(outputFormat, "What can I do for you?\n" + line);
    }

    public void goodBye() {
        System.out.printf(outputFormat, "          *(^v^)");
        System.out.printf(outputFormat, "Bye. Hope to see you again soon!");
    }



    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.run();
    }
}
