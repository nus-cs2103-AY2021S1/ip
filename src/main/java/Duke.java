import java.util.*;

public class Duke {
    private String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private String outputFormat = "  %s\n";
    private boolean ongoing;
    private ArrayList<Task> todoList;

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
            String[] instructions = input.split(" ");
            if (instructions[0].equals("done")) {
                completeItem(Integer.valueOf(instructions[1]) - 1);
            } else {
                addItem(input);
            }
        }
    }

    public void showList() {
        int count = 1;
        System.out.printf(outputFormat, "The tasks in your Todo List: ");
        for (Task item : todoList) {
            System.out.printf(outputFormat, Integer.toString(count) + ". " + item.getItem());
            count += 1;
        }
    }

    public void completeItem(Integer index) {
        Task item = todoList.get(index);
        item.completeTask();
        System.out.printf(outputFormat, "   \\\\(^o^)/ *.*.* \\\\(^o^)/");
        System.out.printf(outputFormat, "Yay! This task has been completed: ");
        System.out.printf(outputFormat, "  " + item.getItem());
    }

    public void addItem(String input) {
        Task newTask = new Task(input);
        todoList.add(newTask);
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
