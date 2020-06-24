package ClassXml;

public class Step {
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Step(String text) {
        this.text = text;

    }

    @Override
    public String toString() {
        return "Step{" +
                "text='" + text + '\'' +
                '}';
    }
}
