package dnd_damager;

import dnd_damager.user_input.UserInputVars;
import interfaceReplier.CustomBotReplier;

public class DndDamager implements CustomBotReplier {
    private UserInputVars userInputVars;

    public DndDamager(String userInput) {
        this.userInputVars = new UserInputVars();
        userInputVars.setAllVariables(userInput);
    }

    @Override
    public String start() {
        ActionDice actionDice = new ActionDice();
        actionDice.throwDice(userInputVars);

        DamageDice damageDice = new DamageDice(userInputVars);
        damageDice.throwDice(actionDice.getDiceThrowsNormal(), actionDice.getDiceThrowsCritical().size());

        String dndResponse = String.format("You made: %d damage \n" +
                        "%d of %d attacks was successful \n" +
                        "%d attacks was critical and made: %d damage \n\n",
                damageDice.getUserDmg(),
                damageDice.getNumOfSucceedAttacks(),
                userInputVars.getNumOfAttack(),
                actionDice.getDiceThrowsCritical().size(),
                damageDice.getCriticalDmg()
                );

        //Display all not critical success throws is any in response
        if (actionDice.getDiceThrowsNormal().size() != 0) {
            dndResponse += "Your normal throws (without masteryClass): \n" +
                    actionDice.getDiceThrowsNormal() + "\n\n";
        }

        //Display all critical throws is any in response
        if (actionDice.getDiceThrowsCritical().size() != 0) {
            dndResponse += "Your critical throws (without masteryClass): \n" +
                    actionDice.getDiceThrowsCritical();

        }

        return dndResponse;
    }
}
