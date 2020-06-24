package TestPlan;

import ClassXml.Action;
import ClassXml.InitialState;
import ClassXml.Variation;

import java.util.ArrayList;
import java.util.HashMap;

public class TestGenerator {
    ArrayList<Action> actions;

    public String[][] TableForm(String state){
        //String[][] tableState = new String[actions.size()][4];
        for (int i = 0; i < actions.size() ; i++) {
            for (int j = 0; j < actions.get(i).getVariations().size(); j++) {
                for (int k = 0; k <actions.get(i).getVariations().get(j).getFinalStates().size() ; k++) {
                    if(actions.get(i).getVariations().get(j).getFinalStates().get(k).equals(state)){

                    }

                }

            }

        }
    }

    public HashMap<Action, Variation> foundFinalState(String state){

    }

   public ArrayList<InitialState>
}
