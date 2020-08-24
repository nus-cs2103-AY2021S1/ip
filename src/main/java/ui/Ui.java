package main.java.ui;

public class Ui {

    public Ui() { };

    public static void greeting() {

        String border = "    ============================================================";

        System.out.println(border);
        System.out.println("    Hello! I'm Duke" + "\n" + "    What can I do for you?");
        System.out.println(border);
    }

    public void showLine() {
        String line = "    ____________________________________________________________";
        System.out.println(line);
    }

    public void sayBye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }


}
