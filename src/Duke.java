import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println(
            new Formating<>(
                new Greet(Status.GREET.toString())));

        Memory<Task> memory = new Memory<>();
        String extract = input.nextLine();
        String nextLine = new Formating<>(extract).shorten().getContent();
        while (!nextLine.equals(Status.BYE.name().toLowerCase())) {
            if (nextLine.equals(Status.LIST.name().toLowerCase())) {
                System.out.println(
                        new Formating<>(Status.LIST.toString() + memory)
                );
            } else {
                if (nextLine.length() >= 6
                        && nextLine.substring(0, 4).equals(Status.DONE.name().toLowerCase())) {

                    try {
                        int num =
                                Integer.parseInt(new Formating<>(nextLine.substring(4)).shorten().getContent());

                        if (num > memory.getMemory().size()) {
                            System.out.println(new Formating<>(Status.EXCESS.toString()));
                        } else {
                            Task task = memory.getMemory().get(num - 1);
                            task.setDone();
                            System.out.println(
                                    new Formating<>(new Echo(Status.DONE.toString() + task)));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(new Formating<>(Status.NUMBERFORMATEXCEPTION.toString()));
                    }

                } else if (nextLine.length() == 0) { }

                else {
                    memory.addMemory(new Task(nextLine));
                    Formating<Response> formatedEcho =
                            new Formating<>(new Echo(Status.ADD.toString() + nextLine));
                    System.out.println(formatedEcho);
                }
            }
            extract = input.nextLine();
            nextLine = new Formating<>(extract).shorten().getContent();
        }

        input.close();
        System.out.println(new Formating<>(
            new Exit(Status.BYE.toString())));
    }
}