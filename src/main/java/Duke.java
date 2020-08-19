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
    private static List<Task> thingsOnList = new ArrayList<>();
    private static void viewList() {
        for (int i = 0; i < thingsOnList.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + thingsOnList.get(i));
        }
    }
    private static void Echo() {
        int startingSize = thingsOnList.size();
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
                if ((spaceIndex != -1 && spaceIndex != input.length() - 1 && input.substring(0, spaceIndex).equals(DONE)) || input.equals("done")) {
                    try {
                        int x = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
                        thingsOnList.get(x).markAsDone();
                        viewList();
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println(ERROR);
                    }
                } else {
                    int cmdIndex = input.indexOf("/");
                    if (cmdIndex != -1 && cmdIndex != input.length() - 1) {
                        String cmd = input.substring(cmdIndex, cmdIndex + 3);
                        System.out.println(cmd);
                        if (cmd.equals("/by")) {
                            thingsOnList.add(new Deadlines(input));
                        } else if (cmd.equals("/at")) {
                            thingsOnList.add(new Events(input));
                        }
                    } else if (input.contains("todo")){
                        thingsOnList.add(new ToDos(input));
                    }

                    System.out.println(thingsOnList.size() == startingSize ? "The Covenant are trying to plug up " +
                        "our list with meaningless garbage again. " +
                            "Try again with commands this time." :("    Roger. I've added this task:\n    " +
                            thingsOnList.get(thingsOnList.size() - 1) + "\n    " +
                                "Now you have " + thingsOnList.size() + " tasks in the list."));
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