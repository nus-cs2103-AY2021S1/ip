import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("hi! im conundrum boy :)");
        List<Task> things = new ArrayList<Task>();
        Scanner input = new Scanner(System.in);

        String in;
        label:
        while (true) {
            in = input.nextLine();
            if ("bye".equals(in)) {
                break label;
            } else if ("".equals(in)) {
                continue;
            } else if ("list".equals(in)) {
                for (int i = 1; i <= things.size(); i++) {
                    System.out.println(i + ". " + things.get(i - 1));
                }
            } else if (in.matches("done -?\\d+")){
                int current = Integer.parseInt(in.substring(5)) - 1;
                Task task;
                if (current < 0 || current > things.size()) {
                    System.out.println("that is not the number of a task in the list!");
                } else {
                    task = things.get(current);
                    if (task.done) {
                        System.out.println("you have already completed " + task.task + "!");
                    } else {
                        System.out.println("congrats on finishing your task :) it's marked as done:");
                        task.complete();
                        System.out.println("\t" + task);
                    }
                }
            } else {
                System.out.println("added: " + in);
                things.add(new Task(in));
            }

        }
        System.out.println("bye bye!");

    }
}
