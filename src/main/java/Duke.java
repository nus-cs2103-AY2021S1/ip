import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "        ___\n"
                    + "    . -^   `--,\n"
                    + "   /# =========`-_\n"
                    + "  /# (--====___====\\\n"
                    + " /#   .- --.  . --.|\n"
                    + "/##   |  * ) (   * ),\n"
                    + "|##   \\    /\\ \\   / |\n"
                    + "|###   ---   \\ ---  |\n"
                    + "|####      ___)    #|\n"
                    + "|######           ##|\n"
                    + " \\##### ---------- /\n"
                    + "  \\####           (\n"
                    + "   `\\###          |\n"
                    + "     \\###         |\n"
                    + "      \\##        |\n"
                    + "       \\###.    .)\n"
                    + "         `======/";

        System.out.println("A massive alien head has appeared\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    SHOW ME WHAT YOU'VE GOT");
        System.out.println("    ____________________________________________________________");
        System.out.println("");

        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int itemsIndex = 0;

        while (!sc.hasNext("bye")) {
            String newItem = sc.nextLine();
            System.out.println("    ____________________________________________________________");
            if (newItem.equals("list")) {
                for (int i = 0; i < itemsIndex; i++) {
                    System.out.println("     " + (i + 1) + ". " + tasks[i]);
                }
            } else if (isDoneString(newItem)) {
                //Need to handle out of bounds number.
                String itemNumber = newItem.substring(5, newItem.length());
                int itemIndex = Integer.valueOf((itemNumber)) - 1;
                Task selectedTask = tasks[itemIndex];
                selectedTask.setDone();
                System.out.println("     Nice! I've marked this task as done: ");
                System.out.println("        " + selectedTask);

            } else {
                tasks[itemsIndex] = new Task(newItem);
                itemsIndex++;
                System.out.println("     added: " + newItem);
            }
            System.out.println("    ____________________________________________________________");
            System.out.println("");
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    private static boolean isDoneString(String s) {
        String[] split = s.split(" ");
        if (split.length != 2) {
            return false;
        } else {
            for (char c : split[1].toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }
    }
}
