package dnd_damager;

import dnd_damager.user_input.UserInputVars;

import java.util.List;

public class DamageDice {
    private int userDmg = 0;
    private int casualDmg = 0;
    private int criticalDmg = 0;

    private int numOfSucceedAttacks = 0;
    private int numOfCreatAttacks = 0;

    UserInputVars user;

    public DamageDice(UserInputVars user) {
        this.user = user;
    }

    public DamageDice throwDice(List<?extends Integer> diceThrows, int numOfCriticals) {
        for (Integer attack:
                diceThrows) {
            if (attack + user.getMasteryClass() + user.getDmgModifier() >= user.getArmorClass()) {
                for (int i = 0; i < user.getNumOfDamageDices(); i++) {
                    casualDmg += (int) (Math.random() * user.getDiceOfDamage() + 1);
                }

                casualDmg += user.getDmgModifier();
                numOfSucceedAttacks++;
            }
        }

        for (int i = 0; i < numOfCriticals; i++) {
            //Double number of dmgDices
            for (int j = 0; j <2 ; j++) {
                criticalDmg += (int) (Math.random() * user.getDiceOfDamage() + 1);
            }
            numOfSucceedAttacks++;
            numOfCreatAttacks++;
        }
        userDmg = (getCasualDmg() + getCriticalDmg());
        isResist();
        return this;
    }


    public void isResist() {
        if (user.getResist() == -1) {
            userDmg *= 2;
            casualDmg *= 2;
            criticalDmg *= 2;

        } else if (user.getResist()  == 1) {
            if (userDmg != 0) { userDmg /= 2; }
            if (casualDmg != 0) { casualDmg /= 2; }
            if (criticalDmg != 0) { criticalDmg /= 2; }
        }
    }

    public int getUserDmg() {
        return userDmg;
    }

    public void setUserDmg(int userDmg) {
        this.userDmg = userDmg;
    }

    public int getCasualDmg() {
        return casualDmg;
    }

    public void setCasualDmg(int casualDmg) {
        this.casualDmg = casualDmg;
    }

    public int getCriticalDmg() {
        return criticalDmg;
    }

    public void setCriticalDmg(int criticalDmg) {
        this.criticalDmg = criticalDmg;
    }

    public int getNumOfSucceedAttacks() {
        return numOfSucceedAttacks;
    }

    public void setNumOfSucceedAttacks(int numOfSucceedAttacks) {
        this.numOfSucceedAttacks = numOfSucceedAttacks;
    }

    public int getNumOfCreatAttacks() {
        return numOfCreatAttacks;
    }

    public void setNumOfCreatAttacks(int numOfCreatAttacks) {
        this.numOfCreatAttacks = numOfCreatAttacks;
    }
}
