package duke;

import java.util.Scanner;

public class Ui {

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        return str;
    }

    public void showList(TaskList list) {
        String print = "";
        print += "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size()-1) {
                print += String.format("%d. ", i+1) + list.get(i);
            } else {
                print += String.format("%d. ", i + 1) + list.get(i) + "\n";
            }
        }
        saySomthing(print);
    }

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
