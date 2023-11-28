package com.solncev.fx.chat.view;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;

public class ChatView extends BaseView {

    private TextArea input;
    private TextArea conversation;
    private AnchorPane pane;

    private final EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.ENTER) {
                String username = getChatApplication().getUserConfig().getUsername();
                String message = input.getText();

                // send message
                getChatApplication().getChatClient().sendMessage(username + ": " + message);
                conversation.appendText("bot: " + "write \"/help\" to get an instruction to use the bot" + "\n");
                try {
                    conversation.appendText("bot: " + new Commands().chooseCommand(message) + "\n");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                input.clear();
                event.consume();
            }
        }
    };

    public void appendMessage(String message) {
        if (message != null) {
            conversation.appendText(message);
        }
    }

    @Override
    public Parent getView() {
        if (pane == null) {
            createView();
        }
        return pane;
    }

    private void createView() {
        pane = new AnchorPane();

        conversation = new TextArea();
        conversation.setEditable(false);
        conversation.setWrapText(true);

        AnchorPane.setTopAnchor(conversation, 10.0);
        AnchorPane.setLeftAnchor(conversation, 10.0);
        AnchorPane.setRightAnchor(conversation, 10.0);

        input = new TextArea();
        input.setMaxHeight(50);

        AnchorPane.setBottomAnchor(input, 10.0);
        AnchorPane.setLeftAnchor(input, 10.0);
        AnchorPane.setRightAnchor(input, 10.0);

        input.addEventHandler(KEY_PRESSED, eventHandler);
        pane.getChildren().addAll(input, conversation);
    }
}
