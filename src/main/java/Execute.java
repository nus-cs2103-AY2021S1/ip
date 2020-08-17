import java.util.Scanner;

public class Execute {
    public static void output() {
        // Initial welcome message
        Salutations welcome = new Salutations(Salutations.type.WELCOME);
        welcome.printMessage();

        // creation of List
        Storage listOfItems = new Storage();

        // Subsequent messages
        Scanner scan = new Scanner(System.in);
        while (true) {
            String userInput = scan.nextLine();

            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                listOfItems.print();
            } else if (userInput.contains("done")) {


                char numberCharacter = userInput.charAt(5);
                int index = Integer.parseInt(String.valueOf(numberCharacter)) - 1;

                Task taskToChange = listOfItems.getItem(index);
                taskToChange.markDone();

                // printing part
                Salutations markedDone = new Salutations(Salutations.type.TASKDONE);
                markedDone.printMessage();
                taskToChange.print();


            } else {
                Task task = new Task(userInput);
                listOfItems.addItem(task);
            }
        }
        // Exit message
        Salutations goodbye = new Salutations(Salutations.type.GOODBYE);
        goodbye.printMessage();
    }
}
