import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String itemName = sc.nextLine();
        ArrayList<Item> list = new ArrayList<>();

        while (!itemName.equals("bye")) {
            if (itemName.equals("list")) {
                if (list.isEmpty()) {
                    System.out.println("List is empty.");
                } else {
                    StringBuilder listOutput = new StringBuilder();
                    for (int j = 0; j < list.size(); j++) {
                        int num = j + 1;
                        Item item = list.get(j);
                        listOutput.append(num + "." + item.toString() + "\n");
                    }
                    System.out.println(listOutput);
                }
            } else {
                if (itemName.contains(" ")) {
                    int i = itemName.indexOf(" ");
                    String firstWord = itemName.substring(0, i);
                    if (firstWord.equals("done")) {
                        int num = Integer.parseInt(itemName.substring(i+1));
                        Item itemToSetToDone = list.get(num-1);
                        itemToSetToDone.setDone();
                        System.out.println("Nice! I've marked this task as done:" + "\n" + itemToSetToDone.toString());
                    } else {
                        Item item = new Item(itemName);
                        list.add(item);
                        System.out.println("added: " + itemName);
                    }
                } else {
                    Item item = new Item(itemName);
                    list.add(item);
                    System.out.println("added: " + itemName);
                }
            }
            itemName = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
