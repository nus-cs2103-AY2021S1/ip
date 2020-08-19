import java.util.*;

public class Duke {
    public static void errorMsg(String msg) {
        System.out.println("⚠ " + msg + " ⚠");
    }
    public static void print(String msg) {
        System.out.println("✰ " + msg + " ✰");
    }
    public static void main(String[] args) {
        print("hi! im conundrum boy :)");
        List<Task> things = new ArrayList<Task>();
        Scanner input = new Scanner(System.in);

        String in;
        while (true) {
            in = input.nextLine();
            if ("bye".equals(in)) {
                break;
            } else if ("".equals(in)) {
                continue;
            } else if ("list".equals(in)) {
                if (things.size() == 0) {
                    print("you haven't added any tasks to the list yet!");
                }
                for (int i = 1; i <= things.size(); i++) {
                    print(i + ". " + things.get(i - 1));
                }
            } else if (in.startsWith("done ")){
                int current;
                try {
                    current = Integer.parseInt(in.substring(5));
                } catch (Exception e){
                    errorMsg("you haven't entered a task number to complete!");
                    continue;
                }
                current--;
                Task task;
                if (current < 0 || current >= things.size()) {
                    errorMsg("that is not the number of a task in the list!");
                } else {
                    task = things.get(current);
                    if (task.done) {
                        errorMsg("you have already completed " + task.task + "!");
                        continue;
                    }
                    task.complete();
                    print("congrats on finishing your task :) it's marked as done:\n\t" + task);

                }
            } else if (in.startsWith("delete ")){
                int current;
                try {
                    current = Integer.parseInt(in.substring(7));
                } catch (Exception e){
                    errorMsg("you haven't entered a task number to delete!");
                    continue;
                }
                current--;
                Task task;
                if (current < 0 || current >= things.size()) {
                    errorMsg("that is not the number of a task in the list!");
                } else {
                    task = things.get(current);
                    things.remove(current);
                    print("i've removed the following task from the list:\n\t" + task + "\nnow you have " + things.size() + " items in your tasklist.");

                }
            } else if (in.startsWith("todo ")){
                String taskname = in.substring(5);
                if (taskname.length() == 0) {
                    errorMsg("the task description cannot be nothing D:");
                    continue;
                }
                Task temp = new ToDo(taskname);
                things.add(temp);
                print("i've added this task for you: \n\t" + temp + "\nnow you have " + things.size() + " items in your tasklist.");
            } else if (in.startsWith("deadline ")){
                int ind = in.indexOf("/by ");
                if (ind < 0 || ind == in.length() - 4) {
                    errorMsg("you haven't entered a time that this task is due by. you can do that by typing \"deadline xxx /by yyy\".");
                    continue;
                }

                if (ind - 1 <= 9) {
                    errorMsg("the task description cannot be nothing D:");
                    continue;
                }
                String taskname = in.substring(9,ind - 1);
                String dead = in.substring(ind + 4);
                Task temp = new Deadline(taskname,dead);
                things.add(temp);
                print("i've added this task for you: \n\t" + temp + "\nnow you have " + things.size() + " items in your tasklist.");
            } else if (in.startsWith("event ")){
                int ind = in.indexOf("/at ");
                if (ind < 0 || ind == in.length() - 4) {
                    errorMsg("you haven't entered a time that this task happens at. you can do that by typing \"event xxx /at yyy\".");
                    continue;
                }

                if (ind - 1 <= 6) {
                    errorMsg("the task description cannot be nothing D:");
                    continue;
                }
                String taskname = in.substring(6,ind - 1);
                String time = in.substring(ind + 4);
                Task temp = new Event(taskname,time);
                things.add(temp);
                print("i've added this task for you: \n\t" + temp + "\nnow you have " + things.size() + " items in your tasklist.");
            } else {
                errorMsg("i dont know what that means :(");
            }

        }
        print("bye bye!");

    }
}
