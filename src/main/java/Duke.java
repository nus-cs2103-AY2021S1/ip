package main.java;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean check = true;
        Scanner sc = new Scanner(System.in);
        String border = "____________________________________________________________\n";
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _______________ \n"
//                + "| | | | | | | |/ / _ \\/ _ \\/ _ \\\n"
//                + "| |_| | |_| |   <  __/| __/| __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\\___|\\___|\n";
        String logo = " ____        ____  \n"
                + "|  _ \\  ___ |  _ \\\n"
                + "| | | |/ _ \\| | | |\n"
                + "| |_| || __/| |_| |\n"
                + "|____/ \\___||____/\n";
        System.out.println("Hello I am\n" + logo + "\n" + "What can I do for you? :3");
        while (check) {
            String input = sc.nextLine();
            switch (input) {
                case "list":
                    System.out.println(border + "list\n" + border);
                    break;
                case "uwu":
                    System.out.println(border + "owo\n" + border);
                    break;
                case "exit":
                    System.out.println(border + "bb cya again!\n" + border);
                    check = false;
                    break;
                default:
                    System.out.println(border + "I don't recognise this input :v please try again.\n" + border);
            }
        }
    }
}
