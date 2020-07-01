package WorkClass;

import ClassXml.InitialState;

import java.util.ArrayList;

public class AVWithInitialStates implements Comparable<AVWithInitialStates> {
    ActionWithVariation actionWithVariation;
    ArrayList<InitialState> initialStates;

    public AVWithInitialStates(ActionWithVariation actionWithVariation, ArrayList<InitialState> initialStates) {
        this.actionWithVariation = actionWithVariation;
        this.initialStates = initialStates;
    }

    public AVWithInitialStates() {}

    public ActionWithVariation getActionWithVariation() {
        return actionWithVariation;
    }

    public void setActionWithVariation(ActionWithVariation actionWithVariation) {
        this.actionWithVariation = actionWithVariation;
    }

    public ArrayList<InitialState> getInitialStates() {
        return initialStates;
    }

    public void setInitialStates(ArrayList<InitialState> initialStates) {
        this.initialStates = initialStates;
    }

    public ArrayList<AVWithInitialStates> creator(ArrayList<ActionWithVariation> actionWithVariations){
        ArrayList<AVWithInitialStates> avWithInitialStates = new ArrayList<>();
        for (int i = 0; i < actionWithVariations.size(); i++) {
            ArrayList<InitialState> initialStatesVariation = new ArrayList<>();
            initialStatesVariation.addAll(actionWithVariations.get(i).getAction().getInitialStates());
            initialStatesVariation.addAll(actionWithVariations.get(i).getVariation().getInitialStates());

            avWithInitialStates.add(new AVWithInitialStates(actionWithVariations.get(i), initialStatesVariation));

        }
        return avWithInitialStates;
    }



    @Override
    public String toString() {
        return "AVWithInitialStates{" +
                "actionWithVariation=" + actionWithVariation +
                ", initialStates=" + initialStates +
                '}';
    }

    @Override
    public int compareTo(AVWithInitialStates o) {
        return ((this.actionWithVariation.getAction().getbValue()+this.actionWithVariation.getVariation().getBValue())
                -(o.getActionWithVariation().getAction().getbValue()+o.getActionWithVariation().getVariation().getBValue()));
    }
}
