package duke;

public class Ui {
    public void sayHi() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you mate?");
    }

    public void saySomthing(String str) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println(str);
        System.out.println("------------------------------------------------------------------------");
    }

    public void echo(String input) {
        this.saySomthing(input);
    }

    public void byeMessage() {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-");
        System.out.println("Bye. Hope to see you again soon!!!");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-");
    }
}
