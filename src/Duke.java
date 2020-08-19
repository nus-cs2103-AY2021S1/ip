import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println(
            new Formating<>(
                new Greet(Status.GREET.toString())));

        Memory<String> memory = new Memory();
        String extract = input.nextLine();
        String nextLine = new Formating<>(extract).shorten();
        while (!nextLine.equals("bye")) {
            if (nextLine.equals("list")) {
                Formating<Memory> formatedMemo = new Formating<>(memory);
                System.out.println(formatedMemo);
            } else {
                memory.addMemory(nextLine);
                Formating<Response> formatedEecho =
                        new Formating<>(new Echo(Status.ADD.toString() + nextLine));
                System.out.println(formatedEecho);
            }
            extract = input.nextLine();
            nextLine = new Formating<>(extract).shorten();
        }

        input.close();
        System.out.println(new Formating<>(
            new Exit(Status.BYE.toString())));
    }
}