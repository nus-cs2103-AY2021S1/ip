import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readFromClient() {
        return scanner.nextLine();
    }

    public String conciseInput() {
        String extract = readFromClient();
        return new Formating<>(extract).shorten().getContent();
    }

    public void greet() {
        System.out.println(
                new Formating<>(
                        new Response(Status.GREET.toString()
                        )
                )
        );
    }

    public void exit() {
        scanner.close();
        System.out.println(
                new Formating<>(
                        new Response(Status.BYE.toString()
                        )
                )
        );
    }
}
