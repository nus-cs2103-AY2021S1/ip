import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String response;
        System.out.println("Hi there! I'm Peanut.\nHow can I be of assistance?\n");
        response = sc.nextLine();
        while (!response.equals("bye")) {
            System.out.println(response + "\n");
            response = sc.nextLine();
        }
        System.out.println("Bye! Sad to see you go :(");

    }

}



