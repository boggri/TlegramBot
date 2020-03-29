package dnd_damager;

import java.util.ArrayList;
import java.util.List;

public class ActionDice {
    private List<Integer> diceThrows;
    private List<Integer> diceCriticals;

    public ActionDice() {
        diceThrows = new ArrayList<>();
        diceCriticals = new ArrayList<>();
    }

    public void throwDice(int numOfAttacks, int critValue) {
        for (int i = 0; i < numOfAttacks; i++) {
            int throwResult = (int)(Math.random() * 20 + 1);

            if (throwResult >= critValue) {
                diceCriticals.add(throwResult);
            } else {
                diceThrows.add(throwResult);
            }

        }
    }


    public List<Integer> getDiceThrows() {
        return diceThrows;
    }

    public List<Integer> getDiceCriticals() {
        return diceCriticals;
    }
}
