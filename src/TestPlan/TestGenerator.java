package TestPlan;

import ClassXml.Action;
import ClassXml.FinalState;
import WorkClass.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class TestGenerator {
    ArrayList<Action> actions;

    ActionWithVariation actionWithVariation = new ActionWithVariation();
    AVWithInitialStates avWithInitialStates = new AVWithInitialStates();
    AVWithFinalState avWithFinalState = new AVWithFinalState();
    SetOfScenarios setOfScenarios = new SetOfScenarios();



public SetOfScenarios createTestPlanWithoutAssert(ArrayList<Action> actions,String round){

    var av = actionWithVariation.creator(actions);
    var avWithIS = avWithInitialStates.creator(av);
    var avWithFS = avWithFinalState.creator(av);

    for (int i = 0; i < avWithIS.size(); i++) {
        ArrayList<ActionWithVariation> actionWithVariations = new ArrayList<>();
        actionWithVariations.add(avWithIS.get(i).getActionWithVariation());
        Scenario scenario = new Scenario(actionWithVariations, avWithIS.get(i).getInitialStates());
        ArrayList<Scenario> scenarios = new ArrayList<Scenario>();
        scenarios. add(scenario);

        TestSuite testSuite = new TestSuite(scenarios, scenario.getInitialStates().size());
        while (testSuite.getN() > 0){
            for (int j = 0; j < scenarios.size(); j++) {
                for (int k = 0; k <scenarios.get(i).getInitialStates().size() ; k++) {
                    var avFound = avWithFinalState.foundAV(avWithFS, scenarios.get(i).getInitialStates().get(k));
                    ArrayList<AVWithInitialStates> foundAvWithIS = avWithInitialStates.creator(avFound);
                    scenarios.get(i).getInitialStates().remove(scenarios.get(i).getInitialStates().get(k));
                    testSuite.setN(testSuite.getN()-1);

                    Scenario sc = scenarios.get(i);
                    testSuite.getScenarios().remove(scenarios.get(i));

                    TreeSet<AVWithInitialStates> sortFoundAvWithIS = new TreeSet<>();
                    sortFoundAvWithIS.addAll(foundAvWithIS);

                    switch (round) {
                        case "All":{
                            for (int l = 0; l < foundAvWithIS.size(); l++) {

                                if (sc.foundCountAv(foundAvWithIS.get(l).getActionWithVariation()) < 3) { //проверка на зацикливание
                                    Scenario scen = sc;
                                    scen.getActionWithVariations().add(foundAvWithIS.get(l).getActionWithVariation());
                                    scen.getInitialStates().addAll(foundAvWithIS.get(l).getInitialStates());

                                    testSuite.getScenarios().add(scen);
                                    testSuite.setN(testSuite.getN() + foundAvWithIS.get(l).getInitialStates().size());
                                }
                            }
                        }
                        case "Max":{

                            if (sc.foundCountAv(sortFoundAvWithIS.first().getActionWithVariation()) < 3) { //проверка на зацикливание
                                Scenario scen = sc;
                                scen.getActionWithVariations().add(sortFoundAvWithIS.first().getActionWithVariation());
                                scen.getInitialStates().addAll(sortFoundAvWithIS.first().getInitialStates());

                                testSuite.getScenarios().add(scen);
                                testSuite.setN(testSuite.getN() + sortFoundAvWithIS.first().getInitialStates().size());
                            }
                        }
                        case "Random":
                            HashMap<AVWithInitialStates, Integer> weigthFoundAvWithIS = new HashMap<>();
                            int n = 0;

                        {for (int l = 0; l < foundAvWithIS.size(); l++) {
                            n = n + foundAvWithIS.get(l).getActionWithVariation().getAction().getbValue() +
                                    foundAvWithIS.get(l).getActionWithVariation().getVariation().getBValue();
                            weigthFoundAvWithIS.put(foundAvWithIS.get(l), n);
                        }

                        int p = (int)(Math.random()*n+1);

                        AVWithInitialStates randomFoundAvWithIS = new AVWithInitialStates();

                            for (Map.Entry<AVWithInitialStates, Integer> entry : weigthFoundAvWithIS.entrySet()) {
                                if(entry.getValue() >= p){
                                    randomFoundAvWithIS = entry.getKey();
                                    break;
                                };
                            }

                            if (sc.foundCountAv(randomFoundAvWithIS.getActionWithVariation()) < 3) { //проверка на зацикливание
                                Scenario scen = sc;
                                scen.getActionWithVariations().add(randomFoundAvWithIS.getActionWithVariation());
                                scen.getInitialStates().addAll(randomFoundAvWithIS.getInitialStates());

                                testSuite.getScenarios().add(scen);
                                testSuite.setN(testSuite.getN() + randomFoundAvWithIS.getInitialStates().size());
                            }
                        }

                    }


                }
            }
        }
        setOfScenarios.getTestSuites().add(testSuite);

    }
    return setOfScenarios;

}

public TestPlan TestPlanWithAssert(SetOfScenarios setOfScenarios, String type, int priority){


    TestPlan testPlan = new TestPlan();
    for (int i = 0; i < setOfScenarios.getTestSuites().size(); i++) {
        for (int j = 0; j < setOfScenarios.getTestSuites().get(i).getScenarios().size(); j++) {
            ArrayList<ActionWithVariation> testWithoutAssert = new ArrayList<>();
            for (int k = 0; k < setOfScenarios.getTestSuites().get(i).getScenarios().get(j).getActionWithVariations().size(); k++) {
                testWithoutAssert.add(setOfScenarios.getTestSuites().get(i).getScenarios().get(j).getActionWithVariations()
                        .get(setOfScenarios.getTestSuites().get(i).getScenarios().get(j).getActionWithVariations().size() - k));
            }
                switch (type){
                   case "leaf":{
                       for (int l = 0; l < testWithoutAssert.get(testWithoutAssert.size()-1).getVariation().getFinalStates().size(); l++) {
                           if(testWithoutAssert.get(testWithoutAssert.size()-1).getVariation().getFinalStates().get(l).getPriority() > priority) {
                               continue;
                           }
                           Test test = new Test();
                           for (int t = 0; t < testWithoutAssert.size(); t++) {
                               if (t != testWithoutAssert.size() - 1) {
                                   Asserts asserts = new Asserts(testWithoutAssert.get(t), null);
                                   test.getTest().add(asserts);
                               } else {
                                   ArrayList<FinalState> singleFS = new ArrayList<>();
                                   singleFS.add(testWithoutAssert.get(testWithoutAssert.size() - 1).getVariation().getFinalStates().get(l));
                                   Asserts asserts = new Asserts(testWithoutAssert.get(t), singleFS);
                                   test.getTest().add(asserts);
                               }
                           }
                           testPlan.getTests().add(test);
                       }


                   } break;
                   case "branch":{

                           Test test = new Test();

                           for (int t = 0; t < testWithoutAssert.size(); t++) {
                               if(t != testWithoutAssert.size()-1) {
                                   Asserts asserts = new Asserts(testWithoutAssert.get(t), null);
                                   test.getTest().add(asserts);
                               }
                               else {
                                   ArrayList<FinalState> finalStatesWithFilter = new ArrayList<>();
                                   for (int l = 0; l < testWithoutAssert.get(t).getVariation().getFinalStates().size(); l++) {
                                       if (testWithoutAssert.get(t).getVariation().getFinalStates().get(l).getPriority() > priority) {
                                           continue;
                                       }
                                       finalStatesWithFilter.add(testWithoutAssert.get(t).getVariation().getFinalStates().get(l));
                                   }
                                   Asserts asserts = new Asserts(testWithoutAssert.get(t), finalStatesWithFilter);
                                   test.getTest().add(asserts);
                               }
                           }
                           testPlan.getTests().add(test);


                   }break;
                    case"tree":{

                        Test test = new Test();

                        for (int t = 0; t < testWithoutAssert.size(); t++) {
                            ArrayList<FinalState> finalStatesWithFilter = new ArrayList<>();
                            for (int l = 0; l < testWithoutAssert.get(t).getVariation().getFinalStates().size(); l++) {
                                if (testWithoutAssert.get(t).getVariation().getFinalStates().get(l).getPriority() > priority){
                                    continue;
                                }
                                finalStatesWithFilter.add(testWithoutAssert.get(t).getVariation().getFinalStates().get(l));
                            }
                            Asserts asserts = new Asserts(testWithoutAssert.get(t), finalStatesWithFilter);
                            test.getTest().add(asserts);
                        }
                        testPlan.getTests().add(test);

                    }break;


                }
            }

        }

    return testPlan;

}

public void APITest(TestPlan testPlan){

}



}


