package com.solncev.fx.chat.view;

import java.util.ArrayList;
import java.util.Arrays;

import static com.solncev.fx.chat.view.BaseView.getChatApplication;

public class Commands {
    String command;
    String input;
    String START_PHRASE = "ok, let's go!";
    String EXIT_PHRASE = "good bye";
    String HELP_PHRASE = "use command \"/start\" to start work with bot, \"/exit\" to finish, \"/weather *city*\" to get a weather in choosen city and \"/currency *currency name*\" to get a currency";
    String DEFAULT_PHRASE = "incorrect command";
    public String chooseCommand(String input) throws Exception {
        command = new ArrayList<String>(Arrays.asList(input.split(" "))).get(0);
        switch (command) {
            case ("/start"):
                return START_PHRASE;
            case ("/exit"):
                return EXIT_PHRASE;
            case ("/help"):
                return HELP_PHRASE;
            case ("/weather"):
                this.input = new ArrayList<String>(Arrays.asList(input.split(" "))).get(1);
                return new Weather().getWeather(this.input);
            case ("/currency"):
                this.input = new ArrayList<String>(Arrays.asList(input.split(" "))).get(1);
                return new Currency().getCurrency(this.input);
            case ("/chat"):
                getChatApplication().start(getChatApplication().getStage());
                return "";
            default:
                return DEFAULT_PHRASE;
        }
    }
}
