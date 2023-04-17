import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;




public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot = new Bot();                  //We moved this line out of the register method, to access it later
        botsApi.registerBot(bot);
        // bot.sendText(672678141L, "Hello World!");  //The L just turns the Integer into a Long
    }


    @Override
    public String getBotUsername() {
        return "Boring_bot";
    }

    @Override
    public String getBotToken() {
        return "5909996984:AAG-igYi_WWVRXfov5gbUOWuSgu5d9i76a8";
    }



    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();
        var id = user.getId();


        //sendText(id, msg.getText());

        if(msg!=null && msg.hasText()){

            switch(msg.getText()) {
                case "/start":
                    sendText(id, "Добро пожаловать в наш Boring_bot \nВведите свой вариант (1-7) и аргуметы в таком порядке: вариант a b c d x y n");
                    break;
                default:
                    sendText(update.getMessage().getChatId(), "...");

                    String message = update.getMessage().getText();
                    String[] messageArray = message.split(" ");
                    int v = Integer.parseInt(messageArray[0]);
                    double a = Double.parseDouble(messageArray[1]);
                    double b = Double.parseDouble(messageArray[2]);
                    double c = Double.parseDouble(messageArray[3]);
                    double d = Double.parseDouble(messageArray[4]);
                    double x = Double.parseDouble(messageArray[5]);
                    double y = Double.parseDouble(messageArray[6]);
                    double n = Double.parseDouble(messageArray[7]);
                    double result = 0;

                    switch (v) {
                        case 1:
                            result = ((5*(Math.pow(a,n*x)))/(b+c)-(Math.pow(Math.abs(Math.cos(Math.pow(x,3))),1/2)));
                            break;
                        case 2:
                            result = (((Math.abs(x-y))/(Math.pow(1+2*x,a)))-Math.exp(Math.pow(1+n,1/2)));
                            break;
                        case 3:
                            result = (Math.pow(a+a*x+a*Math.pow(Math.abs(Math.sin(x)),1/3),1/2));
                            break;
                        case 4:
                            result = (Math.log10(Math.abs(Math.pow(a,7)))+Math.atan(Math.pow(x,2))+(Math.PI/Math.pow(Math.abs(a+x),1/2)));
                            break;
                        case 5:
                            result = (Math.pow(((Math.pow(a+b,2))/(c+d))+Math.exp(Math.pow(x+1,1/2)),1/5));
                            break;
                        case 6:
                            result = (Math.exp((2*Math.sin(4*x)+Math.pow(Math.cos(Math.pow(x,2)),2))/3*x));
                            break;
                        case 7:
                            result = (1/4*((1+Math.pow(x,2))/(1-x)+(1/2*Math.tan(x))));
                            break;
                        default:
                            sendText(update.getMessage().getChatId(), "Некорректный номер варианта.");
                            return;
                    }
                    //String response = String.format("Номер варианта: "+v+"\nАргументы: a= "+a+", b= "+b+", c= "+c+", d= "+d+", x= "+x+", y= "+y+", n= "+n+".\nРезультат= "+result);
                    sendText(id, "Номер варианта: " + v + "\nАргументы: a= " + a + ", b= " + b + ", c= " + c + ", d= " + d + ", x= " + x + ", y= " + y + ", n= " + n + ".\nРезультат= " + result);
            }
        }
    }


    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

}
