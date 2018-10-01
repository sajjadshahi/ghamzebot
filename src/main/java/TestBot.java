import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAJJAD on 9/29/2018.
 */
public class TestBot extends TelegramLongPollingBot {

    private String createTestText(Update update){
        return "سلام " + update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName() + "\uD83D\uDC4B\uD83C\uDFFB\n" +
                "\nپیام شما:\n" + update.getMessage().getText() + "\n";
    }
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            // Create the keyboard (list of keyboard rows)
            List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
            // Create a keyboard row
            KeyboardRow row = new KeyboardRow();
            // Set each button, you can also use KeyboardButton objects if you need something else than text
            row.add("Row 1 Button 1");
            row.add("Row 1 Button 2");
            row.add("Row 1 Button 3");
            // Add the first row to the keyboard
            keyboard.add(row);
            // Create another keyboard row
            row = new KeyboardRow();
            // Set each button for the second line
            row.add("Row 2 Button 1");
            row.add("Row 2 Button 2");
            row.add("Row 2 Button 3");
            // Add the second row to the keyboard
            keyboard.add(row);
            // Set the keyboard to the markup
            keyboardMarkup.setKeyboard(keyboard);
            SendMessage message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText(createTestText(update))
                    .setReplyMarkup(keyboardMarkup)
                    .setReplyToMessageId(update.getMessage().getMessageId());
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return "MyBot";
    }

    public String getBotToken() {
        return "607294637:AAHaH3yCkFG00HCyQpkLO9QTLsWfomJy0bE";
    }
}
