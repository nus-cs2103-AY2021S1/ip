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

        while (!sc.hasNext("bye")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("    " + sc.nextLine());
            System.out.println("    ____________________________________________________________");
            System.out.println("");
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
