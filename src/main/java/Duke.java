package main.java;

import java.util.*;

public class Duke {

    List<Task> list;


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
        System.out.println("------------------------------------");
        System.out.println(input);
        System.out.println("------------------------------------");
    }

    public void byeMessage() {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-");
        System.out.println("Bye. Hope to see you again soon!!!");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-");
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String str = sc.nextLine();
            String arr[] = str.split(" ");
            String keyWord = arr[0];
            if (keyWord.equals("bye")) {
                break;
            } else if (keyWord.equals("list")) {
                showList();
            } else if(keyWord.equals("todo")) {
                Todo task = new Todo(str.substring(5));
                addToList(task);
            } else if(keyWord.equals("deadline") && str.contains("/by")){
                String holder[] = str.split("deadline")[1].split("/by ");
                String description = holder[0].trim();
                String by = holder[1].trim();
                Deadline task = new Deadline(description, by);
                addToList(task);
            } else if (keyWord.equals("event") && str.contains("/at")){
                String holder[] = str.split("event")[1].split("/at ");
                String description = holder[0].trim();
                String at = holder[1].trim();
                Event task = new Event(description, at);
                addToList(task);
            } else if(keyWord.equals("done")) {
                int index = Character.getNumericValue(str.charAt(5));
                completeTask(index);
            }
        }
        byeMessage();
    }

    public void completeTask(int index) {
        list.get(index-1).markAsDone();
        System.out.println("------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(index-1).toString());
        System.out.println("------------------------------------");
    }

    public void addToList(Task task) {
        list.add(task);
        System.out.println("------------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
        System.out.println("------------------------------------");

    }
    public void showList() {
        System.out.println("------------------------------------");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("%d. ", i+1) + list.get(i));
        }
        System.out.println("------------------------------------");
    }
    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.sayHi();
        bot.run();
    }
}
