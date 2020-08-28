public class Find extends Command {

    String searchQuery;

    Find(String query) {
        this.name = "find";
        this.usage = "find [KeyString]";
        this.description = "Can filter tasks based on String";
        searchQuery = query;
    }

    public String response() {
        return "Here are the matching tasks in your list:\n" +
                DataStorageInterface.filteredTasks(searchQuery);
    }
}
