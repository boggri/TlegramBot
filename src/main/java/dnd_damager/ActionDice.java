package dnd_damager;

import dnd_damager.user_input.UserInputVars;

import java.util.ArrayList;
import java.util.List;

public class ActionDice {
    private List<Integer> diceThrowsNormal;
    private List<Integer> diceThrowsCritical;

    public ActionDice() {
        diceThrowsNormal = new ArrayList<>();
        diceThrowsCritical = new ArrayList<>();
    }

    public void throwDice(UserInputVars user) {
        for (int i = 0; i < user.getNumOfAttack(); i++) {
            int throwResult = (int)(Math.random() * 20 + 1);

            if (throwResult >= user.getValueForСritical()) {
                diceThrowsCritical.add(throwResult);
            } else {
                diceThrowsNormal.add(throwResult);
            }

        }
    }


    public List<Integer> getDiceThrowsNormal() {
        return diceThrowsNormal;
    }

    public List<Integer> getDiceThrowsCritical() {
        return diceThrowsCritical;
    }
}
