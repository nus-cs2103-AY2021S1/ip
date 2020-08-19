import java.util.*;

public class Duke {
    public static void errorMsg(String msg) {
        System.out.println(msg);
    }
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
                    errorMsg("that is not the number of a task in the list!");
                } else {
                    task = things.get(current);
                    if (task.done) {
                        errorMsg("you have already completed " + task.task + "!");
                    } else {
                        task.complete();
                        System.out.println("congrats on finishing your task :) it's marked as done:\n\t" + task);
                    }
                }
            } else if (in.startsWith("todo")){
                Task temp = new ToDo(in.substring(5));
                things.add(temp);
                System.out.println("i've added this task for you: \n\t" + temp + "\nnow you have " + things.size() + " items in your tasklist.");
            } else if (in.startsWith("deadline")){
                int ind = in.indexOf("/by");
                if (ind < 0) {
                    continue;
                }
                String taskname = in.substring(9,ind - 1);
                String dead = in.substring(ind + 4);
                Task temp = new Deadline(taskname,dead);
                things.add(temp);
                System.out.println("i've added this task for you: \n\t" + temp + "\nnow you have " + things.size() + " items in your tasklist.");
            } else if (in.startsWith("event")){
                int ind = in.indexOf("/at");
                if (ind < 0) {
                    continue;
                }
                String taskname = in.substring(6,ind - 1);
                String time = in.substring(ind + 4);
                Task temp = new Event(taskname,time);
                things.add(temp);
                System.out.println("i've added this task for you: \n\t" + temp + "\nnow you have " + things.size() + " items in your tasklist.");
            } else {
                continue;
            }

        }
        System.out.println("bye bye!");

    }
}
