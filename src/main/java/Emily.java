package main.java;
import java.util.Scanner;
public class Emily {



    public static void main(String[] args) {
        String divider = "---------------";
        String[] store = new String[100];
        int counter = 1;


        System.out.println("Hello, I am Emily\n" +
                "What can i do for you?\n"+
                divider);
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while(!input.equals("bye")){

            if(input.equals("list")){
                System.out.println("    " + divider);

                for(int i = 1; i<counter; i++){
                    String description = store[i];
                    String item = "    " + i + ". " + description;

                    System.out.println(item);
                }
                System.out.println("    " + divider);
                input = sc.nextLine();

            }else {
                store[counter] = input;
                counter++;

                System.out.println("    " + divider +
                        "\n    added " + input +
                        "\n    " + divider);

                input = sc.nextLine();
            }
        }

        System.out.println(divider + "\n" +
                "Bye~, hope to see you again!");
    }
}
