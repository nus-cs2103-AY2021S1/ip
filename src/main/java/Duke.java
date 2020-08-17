import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "I'm DukeForQ, your chatbot! You can enter everything you want to enter. If you want to exit, enter 'bye'!");

        String[] store = new String[100];
        int counter = 0;
        String s;
        while (true) {
            s = sc.nextLine();
            if (s.equals("bye")) {
                System.out.println("Bye, hope to see you again!");
                sc.close();
                System.exit(0);
            } 
            
            if(s.equals("list")) {
                if(counter == 0) {
                    System.out.println("There is no text now.");
                }

                for(int i = 1; i <= counter; i++) {
                    System.out.println(i + ". " + store[i-1]);
                }
            }

            else {
                store[counter] = s;
                counter++;
                System.out.println("added: " + s);
            }
        }
    }
}
