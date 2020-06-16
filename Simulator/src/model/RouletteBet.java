package model;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.HashSet;

public class RouletteBet {
    private HashSet<Integer> fields = new HashSet<>();
    private int bet;

    public RouletteBet(int bet) {
        this.bet = bet;
    }

    public void setFields(HashSet<Integer> fields) {
        this.fields = fields;
    }

    public int getBet() {
        return bet;
    }

    public HashSet<Integer> getFields() {
        return fields;
    }

    public void calculateFields(String betField, ArrayList<Label> fieldLabels) {
        if (betField.length()==1 || betField.length()==2){
            fields.add(Integer.parseInt(betField));
        } else if (betField.contains("-")){
            for (int i = Integer.parseInt(betField.substring(0,betField.indexOf("-")));
                 i <= Integer.parseInt(betField.substring(betField.indexOf("-")+1)); i++) {
                fields.add(i);
            }
        } else if (betField.equals("Even")|| betField.equals("Odd")){
            int start = 0;
            if (betField.equals("Odd")){
                start=1;
            }
            for (int i = start; i <=36; i++) {
                fields.add(i++);
            }
        } else if (betField.equals("red")|| betField.equals("black")){
            String color = " #cc0000";
            if (betField.equals("black")){
                color="black";
            }
            for (Label fieldLabel : fieldLabels) {
                if (fieldLabel.getStyle().contains(color)){
                    fields.add(Integer.parseInt(fieldLabel.getText()));
                }
            }
        }
    }
}
