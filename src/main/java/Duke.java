package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "________________________________";


        System.out.println("Hello from");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        String output;
        boolean added = false;

         do {

             output = sc.nextLine();
             System.out.println(line);

             if (output.equals("list")) {
                 for (int i = 0; i < list.size(); i++) {
                     System.out.println((i + 1) + ". " + list.get(i));
                 }
                 System.out.println(line);
             }

             else if (output.indexOf("done ") == 0) {
                 String[] arr = output.split(" ");

                 try {

                     int index = Integer.parseInt(arr[1]) - 1;

                     if (index < list.size()) {
                         list.get(index).completeTask();
                         System.out.println("Nice! I've marked this task as done:");
                         System.out.println(list.get(index));
                         System.out.println(line);
                     }

                 } catch (NumberFormatException nfe) {

                 }
             }

             else if (output.indexOf("todo ") == 0 && output.length() > 5) {
                 list.add(new ToDo(output.substring(5)));
                 added = true;
             }

             else if (output.indexOf("deadline ") == 0) {

                 int deadlineIndex = output.indexOf("/by ");

                 if (deadlineIndex != -1 && output.length() > deadlineIndex + 4) {
                     list.add(new Deadline(output.substring(9, deadlineIndex - 1), output.substring(deadlineIndex + 4)));
                     added = true;
                 }
             }

             else if (output.indexOf("event ") == 0) {

                 int timeIndex = output.indexOf("/at ");

                 if (timeIndex != -1 && output.length() > timeIndex + 4) {
                     list.add(new Event(output.substring(6, timeIndex - 1), output.substring(timeIndex + 4)));
                     added = true;
                 }
             }

             if (added){
                 added = false;
                 System.out.println("Got it. I've added this task:");
                 System.out.println(list.get(list.size() - 1));
                 System.out.println(line);
             }

        } while (!output.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
