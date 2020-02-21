import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {
    ExchangeRate exchangeRate = new ExchangeRate();

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();

        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());

        String text = update.getMessage().getText();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

            try{
                sendMessage.setText(getMessage(text));
                execute(sendMessage);
            } catch (TelegramApiException e){
                e.printStackTrace();
            }
        }

    @Override
    public String getBotUsername() {
        return "@ExchangeRatebyAidosBot";
    }

    @Override
    public String getBotToken() {
        return "801340152:AAHypmtreQ_tUYPro6R-boUAGAvV8D7JGIc";
    }

    public String getMessage(String msg){
        ArrayList keyboard = new ArrayList();
        KeyboardRow keyboardRow = new KeyboardRow();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

            keyboard.clear();
            keyboardRow.clear();
            keyboardRow.add("USD");
            keyboardRow.add("EUR");
            keyboardRow.add("RUB");
            keyboard.add(keyboardRow);
            replyKeyboardMarkup.setKeyboard(keyboard);

        if (msg.equals("USD")){
            return getInfoExchangeRateUSD();
        }
        if (msg.equals("EUR")){
            return getInfoExchangeRateEUR() ;
        }
        if (msg.equals("RUB")){
            return getInfoExchangeRateRUB() ;
        }
        return msg;
    }

    public String getInfoExchangeRateUSD(){
        Response info = exchangeRate.getCurrency("USD");
        return  info.getDate_insert()+ "\n 1 USD = " + info.getKZTResponse() + " KZT";
    }
    public String getInfoExchangeRateEUR(){
        Response info = exchangeRate.getCurrency("EUR");
        return info.getDate_insert()+"\n 1 EUR = "+ info.getKZTResponse() + " KZT";
    }
    public String getInfoExchangeRateRUB(){
        Response info = exchangeRate.getCurrency("RUB");
        return info.getDate_insert()+"\n 1 RUB = "+ info.getKZTResponse() + " KZT";
    }
}
