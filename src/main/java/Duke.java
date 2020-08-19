import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void greet() {
        System.out.println("Hello! I'm Jia Le :)");
        System.out.println("How may I be of assistance to you?");
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line =scanner.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye! Hope to see you again ;)");
                break;
            } else if (line.equals("list")) {
                int count = 1;
                for (Task task : list) {
                    System.out.println(count + "." + task.toString());
                    count++;
                }
            } else {
                String[] words = line.split("\\s+");
                if (words[0].equals("done") && words.length > 1) {
                    int index = Integer.parseInt(words[1]) - 1;
                    list.get(index).done();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(index).toString());
                } else {
                    Task task = new Task(line);
                    list.add(task);
                    System.out.println("added: " + line);
                }
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
        greet();
        echo();
    }
}
