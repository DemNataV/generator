package Parser;

import classXml.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.ArrayList;


public class Parser {
    public Parser() throws ParserConfigurationException {
    }

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // f.setValidating(false); // не делать проверку валидации
        DocumentBuilder db = dbf.newDocumentBuilder(); // создали конкретного строителя документа
        Document doc = db.parse(new File("sample.xml")); // стооитель построил документ

        visitDoc(doc, 0);
    }

    public static void visitDoc(Node node, int level) {
        ArrayList<Action> actions = new ArrayList<>();

        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {

            Node childNode = list.item(i); // текущий нод
            process(childNode, level + 1); // обработка
            visit(childNode, level + 1, actions); // рекурсия
        }
    }
    public static void visit(Node node, int level, ArrayList<Action> actions) {
        String act = node.getAttributes().getNamedItem("TEXT").getNodeValue();


        switch (act){
            case "Б-С": {

                NodeList list = node.getChildNodes();
                for (int i = 0; i < list.getLength(); i++) {

                    Node childNode = list.item(i); // текущий нод
                    //process(childNode, level + 1); // обработка
                   Action action = visitAction(childNode); // рекурсия
                    actions.add(action);
                }

            }
            break;
        }



    }

    public static Action visitAction(Node node){
        Action action = new Action();
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {

            Node childNode = list.item(i); // текущий нод
            switch (childNode.getNodeName()){
                case "attribute": {
                   var attributes = childNode.getAttributes();
                   if (attributes.getNamedItem("NAME").getNodeValue().equals("B Value")){
                       action.setbValue(Integer.parseInt(attributes.getNamedItem("VALUE").getNodeValue()));
                   }
                    if (attributes.getNamedItem("NAME").getNodeValue().equals("Estimation")){
                        action.setEstimation(Double.parseDouble(attributes.getNamedItem("VALUE").getNodeValue()));
                    }
                }
                break;
                case "node": {
                   switch (childNode.getAttributes().getNamedItem("TEXT").getNodeValue()){
                       case "parameters":{
                           ArrayList<Parameter> parameters = new ArrayList<>();
                           NodeList list1 = node.getChildNodes();
                           for (int j = 0; j < list.getLength(); j++) {

                               Node childNode1 = list1.item(j); // текущий нод
                               Parameter parameter = visitParameter(childNode1); // рекурсия
                               parameters.add(parameter);
                           }
                           action.setParameters(parameters);

                       }
                       break;
                       case "initial states":{
                           ArrayList<InitialState> initialStates = new ArrayList<>();
                           NodeList list1 = node.getChildNodes();
                           for (int j = 0; j < list.getLength(); j++) {

                               Node childNode1 = list1.item(j); // текущий нод
                               InitialState initialState = visitInitialState(childNode1); // рекурсия
                               initialStates.add(initialState);
                           }
                           action.setInitialStates(initialStates);

                       }
                       break;
                       case "variations":{
                           ArrayList<Variation> variations = new ArrayList<>();
                           NodeList list1 = node.getChildNodes();
                           for (int j = 0; j < list.getLength(); j++) {

                               Node childNode1 = list1.item(j); // текущий нод
                               Variation variation = visitVariation(childNode1); // рекурсия
                               variations.add(variation);
                           }
                           action.setVariations(variations);

                       }
                       break;
                       case "Steps":{
                           ArrayList<Step> steps = new ArrayList<>();
                           NodeList list1 = node.getChildNodes();
                           for (int j = 0; j < list.getLength(); j++) {

                               Node childNode1 = list1.item(j); // текущий нод
                               Step step = visitStep(childNode1); // рекурсия
                               steps.add(step);
                           }
                           action.setSteps(steps);
                       }
                       break;
                   }

                }
                break;
            }


        }
       // action.setbValue(node.getAttributes().getNamedItem().getNodeValue());
       return action ;
    }

    public static Variation visitVariation(Node node){
        Variation variation = new Variation(node.getAttributes().getNamedItem("TEXT").getNodeValue());
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {

            Node childNode = list.item(i); // текущий нод
            switch (childNode.getNodeName()){
                case "attribute": {
                    var attributes = childNode.getAttributes();
                    if (attributes.getNamedItem("NAME").getNodeValue().equals("done")){
                        variation.setDone(attributes.getNamedItem("VALUE").getNodeValue());
                    }
                    if (attributes.getNamedItem("NAME").getNodeValue().equals("type")){
                        variation.setType(attributes.getNamedItem("VALUE").getNodeValue());
                    }
                }
                break;
                case "node": {
                    switch (childNode.getAttributes().getNamedItem("TEXT").getNodeValue()){
                        case "final states":{
                            ArrayList<FinalState> finalStates = new ArrayList<>();
                            NodeList list1 = node.getChildNodes();
                            for (int j = 0; j < list.getLength(); j++) {

                                Node childNode1 = list1.item(j); // текущий нод
                                FinalState finalState = visitFinalState(childNode1); // рекурсия
                                finalStates.add(finalState);
                            }
                            variation.setFinalStates(finalStates);

                        }
                        break;
                        case "initial states":{
                            ArrayList<InitialState> initialStates = new ArrayList<>();
                            NodeList list1 = node.getChildNodes();
                            for (int j = 0; j < list.getLength(); j++) {

                                Node childNode1 = list1.item(j); // текущий нод
                                InitialState initialState = visitInitialState(childNode1); // рекурсия
                                initialStates.add(initialState);
                            }
                            variation.setInitialStates(initialStates);

                        }
                        break;

                        case "Steps":{
                            ArrayList<Step> steps = new ArrayList<>();
                            NodeList list1 = node.getChildNodes();
                            for (int j = 0; j < list.getLength(); j++) {

                                Node childNode1 = list1.item(j); // текущий нод
                                Step step = visitStep(childNode1); // рекурсия
                                steps.add(step);
                            }
                            variation.setSteps(steps);
                        }
                        break;
                    }

                }
                break;
            }


        }
        // action.setbValue(node.getAttributes().getNamedItem().getNodeValue());
        return variation ;
    }

    public static Parameter visitParameter(Node node){
        Parameter parameter = new Parameter(node.getAttributes().getNamedItem("TEXT").getNodeValue());
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {

            Node childNode = list.item(i);
            var attributes = childNode.getAttributes();
            switch (attributes.getNamedItem("NAME").getNodeValue()){
                case "diapason": {
                    parameter.setDiapason(attributes.getNamedItem("VALUE").getNodeValue());
                }
                break;
                case "min": {
                    parameter.setMin(Integer.parseInt(attributes.getNamedItem("VALUE").getNodeValue()));
                }
                break;
                case "max": {
                    parameter.setMax(Integer.parseInt(attributes.getNamedItem("VALUE").getNodeValue()));
                }
                break;
                case "valid": {
                    parameter.setValid(attributes.getNamedItem("VALUE").getNodeValue());
                }
                break;
                case "not valid": {
                    parameter.setNotValid(attributes.getNamedItem("VALUE").getNodeValue());
                }
                break;
                case "required": {
                    parameter.setRequired(Boolean.parseBoolean(attributes.getNamedItem("VALUE").getNodeValue()));
                }
                break;
                case "available": {
                    parameter.setAvailable(Boolean.parseBoolean(attributes.getNamedItem("VALUE").getNodeValue()));
                }
                break;
                case "type": {
                    parameter.setType(attributes.getNamedItem("VALUE").getNodeValue());
                }
                break;
            }

        }
        return parameter;
    }

    public static Step visitStep(Node node) {
        Step step = new Step(node.getAttributes().getNamedItem("TEXT").getNodeValue());
        return step;
    }

    public static InitialState visitInitialState(Node node){
        InitialState initialState = new InitialState();
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {

            Node childNode = list.item(i);
            var attributes = childNode.getAttributes();
            switch (attributes.getNamedItem("NAME").getNodeValue()){
                case "priority": {
                    initialState.setPriority(Integer.parseInt(attributes.getNamedItem("VALUE").getNodeValue()));
                }
                break;
                case "Epic": {
                    initialState.setEpic(attributes.getNamedItem("VALUE").getNodeValue());
                }
                break;
                case "Page": {
                    initialState.setPage(attributes.getNamedItem("VALUE").getNodeValue());
                }
                break;
                case "Table": {
                    initialState.setTable(attributes.getNamedItem("VALUE").getNodeValue());
                }
                break;
                case "Value": {
                    initialState.setValue(attributes.getNamedItem("VALUE").getNodeValue());
                }
                break;
                case "object": {
                    initialState.setObject(attributes.getNamedItem("VALUE").getNodeValue());
                }
                break;

            }

        }
        return initialState;
    }

    public static FinalState visitFinalState(Node node){
        FinalState finalState = new FinalState();
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {

            Node childNode = list.item(i);
            var attributes = childNode.getAttributes();
            switch (attributes.getNamedItem("NAME").getNodeValue()){
                case "priority": {
                    finalState.setPriority(Integer.parseInt(attributes.getNamedItem("VALUE").getNodeValue()));
                }
                break;
                case "Epic": {
                    finalState.setEpic(attributes.getNamedItem("VALUE").getNodeValue());
                }
                break;
                case "Page": {
                    finalState.setPage(attributes.getNamedItem("VALUE").getNodeValue());
                }
                break;
                case "Table": {
                    finalState.setTable(attributes.getNamedItem("VALUE").getNodeValue());
                }
                break;
                case "Value": {
                    finalState.setValue(attributes.getNamedItem("VALUE").getNodeValue());
                }
                break;
                case "object": {
                    finalState.setObject(attributes.getNamedItem("VALUE").getNodeValue());
                }
                break;

            }

        }
        return finalState;
    }

    public static void process(Node node, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print('\t');
        }
        System.out.print(node.getNodeName() + " : " + node.getAttributes());
        if (node instanceof Element) {
            Element e = (Element) node;
            // работаем как с элементом (у него есть атрибуты и схема)
        }
        System.out.println();
    }



}