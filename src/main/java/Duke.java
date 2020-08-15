import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Initializing\n" + logo);
        System.out.println("Yo what's up man, it's ya boi DUKE \n" + "What can I do for you today, homie?");

        ArrayList<Task> arraylst = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String cmd = sc.nextLine();
            if (!cmd.equals("bye")) {
                String[] stringarr = cmd.split(" ");
                if (stringarr[0].equals("list")) {
                    System.out.println("_________________________________________");
                    for (int i = 0; i < arraylst.size(); i++) {
                        int index = i+1;
                        String checked = arraylst.get(i).getDone() ? "O" : "X";
                        System.out.println(index + ".[" + checked + "] " + arraylst.get(i).getTask());
                    }
                    System.out.println("_________________________________________");
                } else if (stringarr[0].equals("done")) {
                    int index = Integer.parseInt(stringarr[1]);
                    int i = index - 1;
                    arraylst.get(i).setDone();
                    System.out.println("_________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(index + ".[O] " + arraylst.get(i).getTask());
                    System.out.println("_________________________________________");
                } else {
                    Task task = Task.setTask(cmd);
                    arraylst.add(task);
                    System.out.println("_________________________________________\n" + "added: " + cmd + "\n" + "_________________________________________");
                }
            } else {
                System.out.println("_________________________________________\n" + "Bye. Hope to see you again soon!" + "\n" + "_________________________________________");
                break;
            }
        }
    }
}
