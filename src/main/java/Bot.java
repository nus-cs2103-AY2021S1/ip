public class Bot {
    public void repeat(String input) {
        System.out.println("___________________");
        System.out.println("added: " + input);
        System.out.println("___________________");
    }

    public void listItems(Store store) {
        System.out.println("___________________");
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (int i = 0; i < store.size(); i++) {
            String doneIndicator = store.getDoneIndicator(i) ? "✓" : "✗";
            System.out.println(counter + ".[" + doneIndicator + "] " + store.getItem(i));
            counter++;
        }
        System.out.println("___________________");
    }

    public void sayBye() {
        System.out.println("___________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________");
    }

    public void markDone(int index, Store store) {
        store.setDone(index);
        System.out.println("_________________");
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + "✓" + "] " + store.getItem(index));
        System.out.println("_________________");
    }
}
