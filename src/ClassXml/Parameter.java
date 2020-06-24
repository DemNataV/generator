package ClassXml;

public class Parameter {
    String text;

    String diapason;
    int min;
    int max;
    String valid;
    String notValid;
    boolean required;
    boolean available;
    String type;

    public Parameter(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDiapason() {
        return diapason;
    }

    public void setDiapason(String diapason) {
        this.diapason = diapason;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getNotValid() {
        return notValid;
    }

    public void setNotValid(String notValid) {
        this.notValid = notValid;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "text='" + text + '\'' +
                ", diapason='" + diapason + '\'' +
                ", min=" + min +
                ", max=" + max +
                ", valid='" + valid + '\'' +
                ", notValid='" + notValid + '\'' +
                ", required=" + required +
                ", available=" + available +
                ", type='" + type + '\'' +
                '}';
    }
}
