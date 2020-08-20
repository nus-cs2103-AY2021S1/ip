package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean check = true;
        Scanner sc = new Scanner(System.in);
        ArrayList<String> storedItems = new ArrayList<>();
        String border = "____________________________________________________________";

        String logo = " ____        ____  \n"
                + "|  _ \\  ___ |  _ \\\n"
                + "| | | |/ _ \\| | | |\n"
                + "| |_| || __/| |_| |\n"
                + "|____/ \\___||____/\n";
        System.out.println("Hello I am\n" + logo + "\n" + "Feed me some input! :3\n");

        while (check) {
            String input = sc.nextLine();
            switch (input) {
                case "list":
                    System.out.println(border);
                    for (int i = 0; i < storedItems.size(); i ++) {
                        System.out.println(String.format("%d. %s", i + 1, storedItems.get(i)));
                    }
                    System.out.println(border);
                    break;
                case "uwu":
                    System.out.println(border + "\n" + "owo\n" + border);
                    break;
                case "exit":
                    System.out.println(border + "\n" + "bb cya again!\n" + border);
                    check = false;
                    break;
                default:
                    storedItems.add(input);
                    System.out.println(border + "\n" + "*Gobble gobble* " + input + " has been eated OwO\n" + border);
            }
        }
    }
}
