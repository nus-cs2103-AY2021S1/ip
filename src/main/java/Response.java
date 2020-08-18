public class Response {
    enum Tag {
        LIST,
        ADD,
        NORMAL
    }
    public Task[] tasks;
    public String[] texts;
    public Tag tag;

    public Response(String[] texts) {
        this.texts = texts;
        this.tag = Tag.NORMAL;
    }

    public Response(String[] texts, Tag tag) {
        this.texts = texts;
        this.tag = tag;
    }

    public Response(Task[] tasks, Tag tag) {
        this.tasks = tasks;
        this.tag = tag;
    }

    public void printResponse() {
        String line = "    __________________________________________________________ \n";
        String linesOfText = "";
        if (this.tag == Tag.LIST) {
            for (int i = 0; i < this.tasks.length; i++) {
                linesOfText += "     " + String.format("%d. ", i + 1) + this.tasks[i] + "\n";
            }
        } else {
            for (int i = 0; i < this.texts.length; i++) {
                linesOfText += "     " + (this.tag == Tag.ADD ? "added: " : "") + this.texts[i] + "\n";
            }
        }
        String output = line + linesOfText + line;
        System.out.println(output);
    }
}
