import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<String> tasks = new ArrayList<>();

    void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    void exit() {
        System.out.println("Bye. Hope to see you soon!");
    }

    void addTask(String task) {
        tasks.add(task);
        System.out.println("added: " + task);
    }

    void list() {
        for(int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i-1));
        }
    }

    void start() {
        greet();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String command = sc.nextLine();
            if(command.equals("bye")) {
                exit();
                break;
            } else if(command.equals("list")){
                list();
            } else {
                addTask(command);
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
