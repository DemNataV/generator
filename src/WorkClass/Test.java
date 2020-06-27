package WorkClass;

import java.util.ArrayList;

public class Test {
    ArrayList<Asserts> test;

    public ArrayList<Asserts> getTest() {
        return test;
    }

    public void setTest(ArrayList<Asserts> test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Test{" +
                "test=" + test +
                '}';
    }
}
