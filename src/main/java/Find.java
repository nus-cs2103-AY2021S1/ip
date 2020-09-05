public class Find extends Command {

    String searchQuery;

    Find(String query) {
        this.name = "find";
        this.usage = "find [KeyString]";
        this.description = "Can filter tasks based on String";
        searchQuery = query;
    }

    public String response() {
        return "Do I look like Google Morty? I-Is that what I am nowadays?" +
                " I searched for your stuff anyway cause I'm not an aaaa*BUURRPPS*ss." +
                " You better be grateful for all these things that I'm helping you with.\n\n" +
                DataStorageInterface.filteredTasks(searchQuery);
    }
}
