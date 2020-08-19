public class Formating<T> {
    private final T content;

    public Formating(T content) {
        this.content = content;
    }

    public String shorten() {
        try {
            String input = (String) content;
            int length = input.length();
            int frontPos = 0;
            int backPos = length - 1;
            while (frontPos < length && input.charAt(frontPos) == ' ') {
                frontPos++;
            }

            while (backPos >= 0 && input.charAt(backPos) == ' ') {
                backPos--;
            }
            return input.substring(frontPos, backPos + 1);
        } catch (ClassCastException e) {
            return "the content is not of data type of String";
        }
    }

    @Override
    public String toString() {
        String indentation = "   ";
        String underscore =
                "  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n";
        return underscore +
                indentation +
                content + "\n" +
                underscore;
    }
}

