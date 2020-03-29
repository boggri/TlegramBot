import dnd_damager.DndDamager;
import interfaceReplier.CustomBotReplier;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bot extends TelegramLongPollingBot {
    private static final Logger log = Logger.getLogger("Log");
    private static Bot bot;
    private static final String TOKEN;

    //Get token from file if on Windows. Else get token from environment variable
    static {
        String tempToken = null;
        if (System.getProperty("os.name").contains("Windows")) {
            File file = new File("src\\main\\resources\\token.txt");
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                if ((tempToken = br.readLine()) != null) {
                    tempToken = tempToken.trim();
                }
                br.close();
            } catch (FileNotFoundException e) {
                System.err.println("token.txt file is not found!");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("token.txt file can't be read!");
                e.printStackTrace();
            }
        } else {
            tempToken = System.getenv("TOKEN");
        }
        TOKEN = tempToken;
    }

    public static Bot getBot() {
        if (Bot.bot == null) {
            Bot.bot = new Bot();
        }
        return bot;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String botAnswer = null;
            String message = update.getMessage().getText();

            //If match pattern get five integers
            if (message.matches("\\d+\\s(\\d+\\s)?\\d+\\s\\d+\\s\\d+\\s[-]?[01]\\s\\d+\\s\\d+")) {

                CustomBotReplier customBotReplier = new DndDamager(message);
                botAnswer = customBotReplier.start();
                sendMsg(update.getMessage().getChatId().toString(), customBotReplier.start());

            } else {
                botAnswer = botGreeting(update) + "\nYou made some mistake in input! Please repeat the query.";
                sendMsg(update.getMessage().getChatId().toString(), botAnswer);
            }
//            botAnswer = botGreeting(update) + message;
//            sendMsg(update.getMessage().getChatId().toString(), botAnswer);


            ////LOG
            String userFirstName = update.getMessage().getChat().getFirstName();
            String userLastName = update.getMessage().getChat().getLastName();
            String userUsername = update.getMessage().getChat().getUserName();
            String userId = update.getMessage().getChat().getId().toString();
            String userMessageText = update.getMessage().getText();

            log(userFirstName, userLastName, userUsername, userId, userMessageText, "");
        }

    }

    private String botGreeting(Update update) {
        String firstName = update.getMessage().getChat().getFirstName();
        String lastName = update.getMessage().getChat().getLastName();
        String username = update.getMessage().getChat().getUserName();

        if (firstName == null) { firstName = ""; }
        if (lastName == null) { lastName = ""; }
        if (username == null) { username = ""; }

        return String.format("Welcome %s %s %s!!! \n" +
                "Enter: numOfAttacks, numberOfDmgDices(optional), damageDice, dmgModifier, masteryClass, isResist(-1 or 0 or 1), critValue, armorClass\n" +
                "e.g. (3 2 6 2 3 -1 20 18)", firstName, lastName, username);
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public String getBotUsername() {
        return "BoggriBot";
    }

    public synchronized void sendMsg(String chatId, String message) {
        SendMessage sendMessage = new SendMessage()
                .enableMarkdown(false)
                .setChatId(chatId)
                .setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.log(Level.SEVERE, "Exception: ", e);
            //e.printStackTrace();
        }
    }

    private void log(String firstName, String lastName, String username, String userId, String txt, String botAnswer) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.print(dateFormat.format(date) + " ---> ");
        System.out.print("Message from " + firstName + " " + lastName + " " + username + ". (id = " + userId + ") \n Text - " + txt);
        //System.out.println("Bot answer: \n Text - " + botAnswer);
    }
}
