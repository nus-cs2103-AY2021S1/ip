public class Command {
    private Category category;
    private String description;

    public Command(Category category){
        this.category = category;
    }
    public Command(Category category, String description) {
        this.category = category;
        this.description = description;
    }

    public Category getCategory() {
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }
}
