package main.java;
import java.util.Scanner;

public class Emily {


    public static void main(String[] args) {

        Task current;
        Task[] store = new Task[100];

        String divider = "---------------";
        int counter = 1;
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello, I am Emily\n" +
                "What can i do for you?\n"+
                divider);


        String input = sc.nextLine();
        try {
            while (!input.equals("bye")) {

                if (input.equals("list")) {

                    System.out.println("    " + divider);
                    for (int i = 1; i < counter; i++) {
                        current = store[i];
                        String item = "    " + i + ". " + current;

                        System.out.println(item);
                    }
                    System.out.println("    " + divider);


                } else if (input.contains("done")) {

                    int index = Character.getNumericValue(input.charAt(5));
                    current = store[index];
                    current.finished = true;

                    String item = "Nice work! I have marked the task as done:\n" +
                            "     " + current;

                    System.out.println("    " + divider +
                            "\n    " + item +
                            "\n    " + divider);


                } else { //case to add task

                    Task item = new Task("");
                    System.out.println("    " + divider
                            + "\n    Got it! I have added this task: ");

                    if (input.contains("todo")) {
                        String describe = input.substring(5);
                        item = new ToDos(describe);

                    } else if (input.contains("deadline")){
                        String describe = input.substring(9);
                        String[] temp = describe.split("/by ");
                        item = new Deadline(temp[0], temp[1]);
                    } else if (input.contains("event")){
                        String describe = input.substring(6);
                        String[] temp = describe.split("/at ");
                        item = new Event(temp[0], temp[1]);
                    }

                    store[counter] = item;
                    counter++;

                    System.out.println("        " + item);
                    System.out.println("    Now you have " + (counter-1) + " tasks in the list" +
                            "\n    " + divider);
                }

                input = sc.nextLine();
            }

        System.out.println(divider + "\nBye~, hope to see you again!");

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
