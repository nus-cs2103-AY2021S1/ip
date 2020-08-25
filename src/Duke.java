import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println(
                new Formating<>(
                        new Greet(Status.GREET.toString())));

        Operation operation = new Operation();
        String extract = input.nextLine();
        String nextLine = new Formating<>(extract).shorten().getContent();
        while (!nextLine.equals(Status.BYE.name().toLowerCase())) {
            operation.run(nextLine);
            extract = input.nextLine();
            nextLine = new Formating<>(extract).shorten().getContent();
        }

        input.close();
        System.out.println(new Formating<>(
                new Exit(Status.BYE.toString())));
    }
}