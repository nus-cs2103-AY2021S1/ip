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
        while(!input.equals("bye")){

            if(input.equals("list")){

                System.out.println("    " + divider);
                for(int i = 1; i<counter; i++){
                    current = store[i];
                    String item = "    "+ i + ". " +current;

                    System.out.println(item);
                }
                System.out.println("    " + divider);


            }else if (input.contains("done")){

                int index = Character.getNumericValue(input.charAt(5));
                current = store[index];
                current.finished = true;

                String item = "Nice work! I have marked the task as done:\n" +
                        "     " + current;

                System.out.println("    " + divider +
                        "\n    " + item +
                        "\n    " + divider);



            }else {
                Task temp = new Task(input);
                store[counter] = temp;
                counter++;

                System.out.println("    " + divider +
                        "\n    added " + input +
                        "\n    " + divider);
            }

            input = sc.nextLine();
        }

        System.out.println(divider + "\nBye~, hope to see you again!");
    }
}
