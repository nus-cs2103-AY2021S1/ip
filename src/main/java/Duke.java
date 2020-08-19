import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    static class Task {
        boolean completed = false;
        String name;

        Task(String name) {
            this.name = name;
        }
        public void setCompleted() {
            completed = true;
        }

        @Override
        public String toString() {
            return (completed ? "[✓]" : "[✗]") + " " + name;
        }

    }

    static boolean isNum(String s) {
        if (s == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    static void print(String s) {
        System.out.println("    ____________________________________________________________\n" +
                s +
                "    ____________________________________________________________\n");
    }
    static ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s;
        print("     Hello! I'm Duke\n" +
                "     What can I do for you?\n");
        while(!(s = scanner.nextLine()).equals("bye")) {
            String[] done = s.split(" ");
            if (done.length == 2 && done[0].equals("done") && isNum(done[1])
                    && Integer.parseInt(done[1]) <= list.size() && Integer.parseInt(done[1]) > 0) {
                list.get(Integer.parseInt(done[1]) - 1).setCompleted();
                print("     Nice! I've marked this task as done:\n" +
                        "     " + list.get(Integer.parseInt(done[1]) - 1) + "\n");
            } else if(s.equals("list")) {
                String temp = "";
                for(int i = 0; i < list.size(); i++) {
                    temp += "     " + (i+1) + ". " + list.get(i) + "\n";
                }
                print("     Here are the tasks in your list:\n" + temp);
            } else {
                list.add(new Task(s));
                print("    added: " + s + "\n");
            }
        }
        print("     Bye. Hope to see you again soon!\n");
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }
}
