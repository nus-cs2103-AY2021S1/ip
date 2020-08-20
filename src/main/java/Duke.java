import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String line = "____________________________________________________________\n";
    private static final String bye = "bye";
    private static final String list = "list";
    private static final String done = "done";
    private ArrayList<Task> input;

    private Duke() {
        input = new ArrayList<>();
    }

    public void init() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        String[] commands = command.split(" ");
        while (!command.equals(bye)) {
            if (command.equals(list)) {
                display();
            } else if (commands[0].equals(done) && commands.length == 2) {
                updateTask(Integer.valueOf(commands[1]));
            } else {
                add(command);
            }
            command = sc.nextLine();
            commands = command.split(" ");
        }
        System.out.println(line + "Cya!!" + "\n" + line);
    }

    private void display() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < input.size(); i++) {
            System.out.println(String.valueOf(i+1) + "." + input.get(i).getDescription() + "\n");
        }
        System.out.println(line);
    }

    private void add(String task) {
        input.add(new Task(task));
        System.out.println(line + "added: " + task + "\n" + line);
    }
    private void updateTask(int index) {
        input.get(index - 1).markAsDone();
        System.out.println(line);
        System.out.println("Solid bro!! I've marked this task as done:\n");
        System.out.println(input.get(index -1 ).getDescription() + "\n");
        System.out.println(line);
    }

    public static void main(String[] args) {
        String welcome = "Yo I'm Dood!!\nAnything I can do for you?\n";
        System.out.println(line + welcome + line);
        Duke bot = new Duke();
        bot.init();
    }
}
