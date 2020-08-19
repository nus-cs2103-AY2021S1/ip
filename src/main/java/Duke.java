package main.java;

import java.util.*;

public class Duke {

    List<String> list;


    public Duke(){
        list = new ArrayList<>();
    }

    public void sayHi() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you mateeee?");
    }

    public void echo(String input) {
        System.out.println("---------------------------");
        System.out.println(input);
        System.out.println("---------------------------");
    }

    public void byeMessage() {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("Bye. Hope to see you again soon!!!");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.equalsIgnoreCase("bye")) {
                break;
            } else if (str.equalsIgnoreCase("list")) {
                showList();
            } else {
                addToList(str);
            }
        }
        byeMessage();
    }

    public void addToList(String task) {
        list.add(task);
        System.out.println("---------------------------");
        System.out.println("added: " + task);
        System.out.println("---------------------------");
    }
    public void showList() {
        System.out.println("---------------------------");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("%d. ", i+1) + list.get(i));
        }
        System.out.println("---------------------------");
    }
    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.sayHi();
        bot.run();
    }
}
