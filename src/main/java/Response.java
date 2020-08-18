public class Response {
    public String[] texts;

    public Response(String[] texts) {
        this.texts = texts;
    }

    public void printResponse() {
        String line = "    __________________________________________________________ \n";
        String linesOfText = "";
        for(int i = 0; i < this.texts.length; i++) {
            linesOfText += "     " + this.texts[i] + "\n";
        }
        String output = line + linesOfText + line;
        System.out.println(output);
    }
}
