public class Bot {
    public void repeat(String input) {
        System.out.println("___________________");
        System.out.println("added: " + input);
        System.out.println("___________________");
    }

    public void listItems(Store store) {
        System.out.println("___________________");
        int counter = 1;
        for (String item: store.store) {
            System.out.println(counter + ". " + item);
            counter++;
        }
        System.out.println("___________________");
    }

    public void sayBye() {
        System.out.println("___________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________");
    }
}
