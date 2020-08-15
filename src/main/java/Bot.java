public class Bot {
    public void repeat(Store store) {
        System.out.println("\t___________________");
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t\t" + store.getList().get(store.size() - 1).toString());
        System.out.println("\tNow you have " + store.size() + " tasks in the list.");
        System.out.println("\t___________________");
    }

    public void listItems(Store store) {
        System.out.println("\t___________________");
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (int i = 0; i < store.size(); i++) {
            System.out.println("\t" + counter + "." + store.getItem(i).toString());
            counter++;
        }
        System.out.println("\t___________________");
    }

    public void sayBye() {
        System.out.println("\t___________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t___________________");
    }

    public void markDone(int index, Store store) {
        store.setDone(index);
        System.out.println("\t_________________");
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t[✓] " + store.getItem(index).getTask());
        System.out.println("\t_________________");
    }
}
