import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void greet() {
        System.out.println("Hello! I'm Jia Le :)");
        System.out.println("How may I be of assistance to you?");
    }

    public static Task taskClassify(String str) {
        String[] words = str.split("\\s+");
        int len = words.length;

        if (words[0].equals("todo")) {
            String desc = "";
            for (int i = 1; i < len; i++) {
                desc += " " + words[i];
            }
            return new ToDo(desc);
        } else if (words[0].equals("deadline")) {
            String desc = "";
            String time = "";
            int count = 0;
            for (int i = 1; i < len; i++) {
                if (words[i].equals("/by")) {
                    count = i + 1;
                    break;
                }
                desc += " " + words[i];
            }
            for (int j = count; j < len; j++) {
                time += " " + words[j];
            }
            return new Deadline(desc, time);
        } else if (words[0].equals("event")) {
            String desc = "";
            String time = "";
            int count = 0;
            for (int i = 1; i < len; i++) {
                if (words[i].equals("/at")) {
                    count = i + 1;
                    break;
                }
                desc += " " + words[i];
            }
            for (int j = count; j < len; j++) {
                time += " " + words[j];
            }
            return new Event(desc, time);
        }
        return new Task(str);
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
                    Task task = taskClassify(line);
                    list.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
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
