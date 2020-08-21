[1mdiff --git a/src/main/java/Duke.java b/src/main/java/Duke.java[m
[1mindex 4ef9a94..0f8d9ef 100644[m
[1m--- a/src/main/java/Duke.java[m
[1m+++ b/src/main/java/Duke.java[m
[36m@@ -1,4 +1,5 @@[m
 import java.util.Scanner;[m
[32m+[m[32mimport java.util.ArrayList;[m
 [m
 public class Duke {[m
 [m
[36m@@ -9,11 +10,14 @@[m [mpublic class Duke {[m
             + "\tWhat can I do for you?";[m
     private static final String EXIT_MESSAGE = "Bye. "[m
             + "Hope to see you again soon!";[m
[32m+[m[32m    private static final String LIST_MESSAGE = "Here are the tasks in your list:";[m
 [m
     // Command constants for the bot[m
     private static final String EXIT_COMMAND = "bye";[m
[32m+[m[32m    private static final String LIST_COMMAND = "list";[m
 [m
     // static fields for the bot[m
[32m+[m[32m    private static ArrayList<String> store = new ArrayList<>(); // add input items here[m
     private static boolean isFirstRun = true; // is the bot run for the first time?[m
 [m
     // helper methods related to displaying[m
[36m@@ -22,6 +26,14 @@[m [mpublic class Duke {[m
         System.out.println("\t" + str);[m
         System.out.println(LINE_BREAK + "\n");[m
     }[m
[32m+[m[32m    private static void displayStoreItems() {[m
[32m+[m[32m        System.out.println(LINE_BREAK);[m
[32m+[m[32m        System.out.println("\t" + LIST_MESSAGE);[m
[32m+[m[32m        for (int ctr = 1; ctr <= store.size(); ctr++) {[m
[32m+[m[32m            System.out.println("\t" + ctr + ". " + store.get(ctr - 1));[m
[32m+[m[32m        }[m
[32m+[m[32m        System.out.println(LINE_BREAK + "\n");[m
[32m+[m[32m    }[m
 [m
     public static void main(String[] args) {[m
         // initialize scanner and add commands to set[m
[36m@@ -37,7 +49,15 @@[m [mpublic class Duke {[m
         String input = sc.nextLine();[m
 [m
         while (!input.equals(EXIT_COMMAND)) {[m
[31m-            displayToScreen(input);[m
[32m+[m[32m            switch (input) {[m
[32m+[m[32m            case LIST_COMMAND:[m
[32m+[m[32m                displayStoreItems();[m
[32m+[m[32m                break;[m
[32m+[m[32m            default:[m
[32m+[m[32m                store.add(input);[m
[32m+[m[32m                displayToScreen("added: " + input);[m
[32m+[m[32m                break;[m
[32m+[m[32m            }[m
             input = sc.nextLine();[m
         }[m
 [m
