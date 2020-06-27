package WorkClass;

import ClassXml.InitialState;

import java.util.ArrayList;

public class Scenario {

    ArrayList<ActionWithVariation> actionWithVariations;
    ArrayList<InitialState> initialStates;

    public Scenario(ArrayList<ActionWithVariation> scenario, ArrayList<InitialState> initialStates) {
        this.actionWithVariations = scenario;
        this.initialStates = initialStates;
    }

    public Scenario() {}

    public ArrayList<ActionWithVariation> getActionWithVariations() {
        return actionWithVariations;
    }

    public void setActionWithVariations(ArrayList<ActionWithVariation> scenario) {
        this.actionWithVariations = scenario;
    }

    public ArrayList<InitialState> getInitialStates() {
        return initialStates;
    }

    public void setInitialStates(ArrayList<InitialState> initialStates) {
        this.initialStates = initialStates;
    }

    public int foundCountAv(ActionWithVariation actionWithVariation){
        int n = 0;
        for (int i = 0; i < actionWithVariations.size(); i++) {
            if (actionWithVariations.get(i).equals(actionWithVariation)){
                n++;
            }
        }
        return n;
    }

    @Override
    public String toString() {
        return "Scenario{" +
                "actionWithVariations=" + actionWithVariations +
                ", initialStates=" + initialStates +
                '}';
    }
}
