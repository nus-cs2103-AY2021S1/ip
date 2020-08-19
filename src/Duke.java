import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println(
            new Formating<>(
                new Greet(Status.GREET.toString())));

        String extract = input.nextLine();
        String nextLine = new Formating<>(extract).shorten();
        while (!nextLine.equals("bye")) {
            System.out.println(
                new Formating<>(
                    new Echo(nextLine)));
            extract = input.nextLine();
            nextLine = new Formating<>(extract).shorten();
        }

        input.close();
        System.out.println(new Formating<>(
            new Exit(Status.BYE.toString())));
    }
}