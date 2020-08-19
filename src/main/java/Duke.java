package main.java;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String WRONG_INPUT = "    The Covenant are trying to plug up " +
            "our list with meaningless garbage. " +
            "Try again with commands this time.";
    private static final String MISSING_INPUT = "    Every Spartan knows you need a proper input " +
            "for this to work. Try again.";
    private static final String DONE_ERROR = "    Sorry Chief, I can't do that.";
    private static final String[] ERROR_MESSAGES = {WRONG_INPUT, MISSING_INPUT, DONE_ERROR};
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
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
    protected static void printLine() {
        System.out.println("    ************************************************************");
    }
    private static void viewList() {
        if (thingsOnList.size() == 0) {
            System.out.println("    No tasks here.");
        } else {
            for (int i = 0; i < thingsOnList.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + thingsOnList.get(i));
            }
        }
    }
    protected static String getWrongInput() {
        int rnd = new Random().nextInt(2);
        return ERROR_MESSAGES[rnd];
    }
    protected static void Echo() throws DukeExceptions {
        int startingSize = thingsOnList.size();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (!input.isEmpty()) {
            printLine();
            if (input.toLowerCase().equals(BYE)) {
                System.out.println(GOODBYE_MSG);
            } else if (input.toLowerCase().equals(LIST)) {
                viewList();
            } else if (!input.isEmpty()) {
                int spaceIndex = input.indexOf(" ");
                if (spaceIndex != -1 && spaceIndex != input.length() - 1 && input.substring(0, spaceIndex).equals(DONE)) {
                        int x = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
                        if (x + 1 > thingsOnList.size()) {
                            throw new DukeExceptions("    This task doesn't exist Chief!");
                        }
                        thingsOnList.get(x).markAsDone();
                        viewList();
                } else if (spaceIndex != -1 && spaceIndex != input.length() - 1 && input.substring(0, spaceIndex).equals(DELETE)) {
                    int x = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
                    if (x + 1 > thingsOnList.size()) {
                        throw new DukeExceptions("    This task doesn't exist Chief!");
                    }
                    System.out.println("Mission Accomplished. I'm removing the task: " + thingsOnList.get(x));
                    thingsOnList.remove(x);
                } else {
                    int cmdIndex = input.indexOf("/");
                    if (cmdIndex == 0 || (input.contains("todo") && input.length() == 4)) {
                    } else if (cmdIndex != -1 && cmdIndex != input.length() - 1) {
                        String cmd = input.substring(cmdIndex, cmdIndex + 3);
                        if (cmd.equals("/by")) {
                            thingsOnList.add(new Deadlines(input));
                        } else if (cmd.equals("/at")) {
                            thingsOnList.add(new Events(input));
                        }
                    } else if (input.contains("todo") && !(input.substring(input.length() - 4).contains("todo"))) {
                        thingsOnList.add(new ToDos(input));
                    }
                    if (thingsOnList.size() == startingSize) {
                        throw new DukeExceptions("    There has been an incorrect use of a command.");
                    } else {
                        System.out.println("    Roger. I've added this task:\n    " +
                                thingsOnList.get(thingsOnList.size() - 1) + "\n    " +
                                "Now you have " + thingsOnList.size() + " tasks in the list.");
                    }
                }
            }
            printLine();
        }
        if (!input.equals(BYE)) {
            Echo();
        } else {
            sc.close();
        }
    }
    public static void main(String[] args) {
        System.out.println(logo + "\nSpartan 117 reporting for list-making duty");
        try {
            Echo();
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
            System.out.println(getWrongInput());
            printLine();
            e.continueTrying();
        }
    }
}