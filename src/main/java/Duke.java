import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<String> tasks;

    public Duke() {
        this.tasks = new ArrayList<>();
    }

    public void greet() {
        System.out.println(String.format("Hello! I'm Duke\nWhat can I do for you?"));
    }

    public void add(String task) {
        this.tasks.add(task);
        System.out.println(String.format("added: %s", task));
    }

    public void list() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, this.tasks.get(i)));
        }
    }

    public void bye() {
        System.out.println("Bye! Hope to never see you again!");
    }
    

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();

        while (!cmd.equals("bye")) {
            switch (cmd) {
                case "list":
                    duke.list();
                    break;
                default:
                    duke.add(cmd);
                    break;
            }

            cmd = sc.nextLine();
        }

        duke.bye();
        sc.close();
    }
}
