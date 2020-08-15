package main.java;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<String> list = new ArrayList<>();

    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static final String TERMINATE_COMMAND = "bye";
    static final String LIST_COMMAND  = "list";
    static final String LINE = "    _____________________________________________________________________\n";
    static final String SINGLE_TAB = "  ";
    static final String DOUBLE_TAB = "      ";

    public void mainProgram() {
        Scanner sc = new Scanner(System.in);
        greet();
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals(TERMINATE_COMMAND)) {
                bye();
                break;
            } else if (input.equals(LIST_COMMAND)) {
                System.out.println(LINE + showList() + LINE);
            }else {
                add(input);
            }
        }
    }

    private String showList() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < list.size() ; i++) {
            sb.append(DOUBLE_TAB + (i+1) + ". " + list.get(i) +"\n");
        }
        return sb.toString();
    }

    private void greet() {
        System.out.println(LINE + DOUBLE_TAB + "Hello! I'm Rich.\n" + DOUBLE_TAB  + "What can I do for you?\n" + LINE);
    }

    private void add(String input) {
        list.add(input);
        System.out.println(LINE + DOUBLE_TAB + "added: " + input + "\n" + LINE);
    }

    public void bye() {
        System.out.println(LINE + DOUBLE_TAB +"Bye.Hope to see you again soon!\n" + LINE);
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.mainProgram();
    }
}
