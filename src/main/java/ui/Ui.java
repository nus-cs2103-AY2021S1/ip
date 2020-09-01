package ui;

public class Ui {

    public Ui() { };

    public String greeting() {

        String border = "    ============================================================";
//
//        System.out.println(border);
//        System.out.println("    Hello! I'm Duke" + "\n" + "    What can I do for you?");
//        System.out.println(border);
        return border + "\n" + "    Hello! I'm Duke" + "\n" + "    What can I do for you?" + "\n" + border;
    }

    public void showLine() {
        String line = "    ____________________________________________________________";
        System.out.println(line);
    }

    public String sayBye() {
        return "     Bye. Hope to see you again soon!";
    }


}
