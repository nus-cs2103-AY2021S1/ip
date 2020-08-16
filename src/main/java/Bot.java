public class Bot {
    public void repeat(Store store) {
        Bot.sectionize();
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t\t" + store.getList().get(store.size() - 1).toString());
        System.out.println("\tNow you have " + store.size() + " tasks in the list.");
        Bot.sectionize();
    }

    public void listItems(Store store) {
        Bot.sectionize();
        System.out.println("\tHere are the tasks in your list:");
        int counter = 1;
        for (int i = 0; i < store.size(); i++) {
            System.out.println("\t" + counter + "." + store.getItem(i).toString());
            counter++;
        }
        Bot.sectionize();
    }

    public void sayBye() {
        Bot.sectionize();
        System.out.println("\tBye. Hope to see you again soon!");
        Bot.sectionize();
    }

    public void markDone(int index, Store store) {
        store.setDone(index);
        Bot.sectionize();
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t" + store.getItem(index).toString());
        Bot.sectionize();
    }

    public static void sectionize() { System.out.println("\t____________________________________________________________"); }

}
