package main.java;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String ERROR = "    Sorry Chief, I can't do that.";
    private static final String GOODBYE_MSG = "    Wake me… When you need me.";
    private static final String logo = "░░░░░░░░░░░▓▓▓███████████████████████▓▓▓░░░░░░░░░░░\n"
            + "░░░░░░░░░▓▓▓░█░░░░░░░░▓░░░░░▓░░░░░░░░█░▓▓▓░░░░░░░░░\n"
            + "░░░░░░░▓▓▓░██░░░░░░░░▓░░░░░░░▓░░░░░░░░██░▓▓▓░░░░░░░\n"
            + "░░░░░░░▓░░█░░░░░░░░░▓▓░░░░░░░▓▓░░░░░░░░░█░░▓░░░░░░░\n"
            + "░░░░░░▓░░█░░░░░░░░░▓▓░░░░░░░░░▓▓░░░░░░░░░█░░▓░░░░░░\n"
            + "░░░░░▓▓▓█░░░░░░░░░▓▓░░░░░░░░░░░▓▓░░░░░░░░░█▓▓▓░░░░░\n"
            + "░░░░░▓░░█░░░░░░░░▓▓▓░░░░░░░░░░░▓▓▓░░░░░░░░█░░▓░░░░░\n"
            + "░░░░░▓░█████████████████████████████████████░▓░░░░░\n"
            + "░░░░▓░██░█░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░█░██░▓░░░░\n"
            + "░░░░▓░░█░█░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░█░█░░▓░░░░\n"
            + "░░░░▓░░█░█░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░█░█░░▓░░░░\n"
            + "░░░░▓█░█▓░█░░░░░░░░░░░░░░░░░░░░░░░░░░░░░█░▓█░█▓░░░░\n"
            + "░░░▓░██░░░█░░░░░░░░░░░░░░░░░░░░░░░░░░░░░█░░░██░▓░░░\n"
            + "░░░▓█░█░░░░█░░░░░░░░░░░░░░░░░░░░░░░░░░░█░░░░█░█▓░░░\n"
            + "░░░▓█░█░░░░░█░░░░░░░░░░░░░░░░░░░░░░░░░█░░░░░█░█▓░░░\n"
            + "░░░░▓█░▓▓░░░░█░░░░░░░░▓▓▓▓▓▓▓░░░░░░░░█░░░░▓▓░█▓░░░░\n"
            + "░░░░░░█░░▓░░░░▓█████████████████████▓░░░░▓░░█░░░░░░\n"
            + "░░░░░░░█▓▓▓░░░░░░░░█░░▓▓▓▓▓▓▓░░█░░░░░░░░▓▓▓█░░░░░░░\n"
            + "░░░░░░░░░█▓▓▓░░░░░░▓█░░░░░░░░░█▓░░░░░░▓▓▓█░░░░░░░░░\n"
            + "░░░░░░░░░░░█▓▓░░▓▓▓░█░░░░░░░░░█░▓▓▓░░▓▓█░░░░░░░░░░░\n"
            + "░░░░░░░░░░░░░█▓▓░░░░█░░░░░░░░░█░░░░▓▓█░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░░░░░█░░░░█░░░░░░░░░█░░░░█░░░░░░░░░░░░░░░\n"
            + "░░░░░░░░░░░░░░░░██████▓▓▓▓▓▓▓██████░░░░░░░░░░░░░░░░\n";;
    private static List<Task> toDo = new ArrayList<>();
    private static void viewList() {
        for (int i = 0; i < toDo.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + toDo.get(i));
        }
    }
    private static void Echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (!input.isEmpty()) {
            System.out.println("    ************************************************************");
            if (input.toLowerCase().equals(BYE)) {
                System.out.println(GOODBYE_MSG);
            } else if (input.toLowerCase().equals(LIST)) {
                viewList();
            } else if (!input.isEmpty()) {
                int spaceIndex = input.indexOf(" ");
                if ((spaceIndex != -1 && input.substring(0, spaceIndex).equals(DONE)) || input.equals("done")) {
                    try {
                        int x = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
                        toDo.get(x).markAsDone();
                        viewList();
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println(ERROR);
                    }
                } else {
                    toDo.add(new Task(input));
                    System.out.println("    added: " + input);
                }
            }
            System.out.println("    ************************************************************");
        }
        if (!input.equals(BYE)) {
            Echo();
        } else {
            sc.close();
        }
    }
    public static void main(String[] args) {
        System.out.println(logo + "\nSpartan 117 reporting for list-making duty");
        Echo();
    }
}