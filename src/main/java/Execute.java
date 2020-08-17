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
            } else {
                Item item = new Item(userInput);
                listOfItems.addItem(item);
            }
        }
        // Exit message
        Salutations goodbye = new Salutations(Salutations.type.GOODBYE);
        goodbye.printMessage();
    }
}
