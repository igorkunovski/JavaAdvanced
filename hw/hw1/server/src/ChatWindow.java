import log.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatWindow extends JFrame {
    static private final int WINDOW_HEIGHT = 440;
    static private final int WINDOW_WIDTH = 520;
    static private final int WINDOW_POS_X = 300;
    static private final int WINDOW_POS_Y = 100;
    static private final String WINDOW_NAME = "Server chat";


    JLabel label = new JLabel("Message to server: ");
    JTextField textInput = new JTextField();
    JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
    JButton buttonConnect = new JButton("Send");
    JButton buttonExit = new JButton("Exit");

    JPanel grid = new JPanel(new GridLayout(4,1));

    ChatWindow(String login){
        //свойства окна
        setTitle(WINDOW_NAME);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);

        JTextArea textOutput = new JTextArea(new Log().download(login));

        textOutput.setEditable(false);
        textOutput.setBackground(Color.GRAY);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textOutput);

        grid.add(scrollPane);
        grid.add(label);
        grid.add(textInput);

        buttonsPanel.add(buttonConnect);
        buttonsPanel.add(buttonExit);

        grid.add(buttonsPanel);

        add(grid);
        setVisible(true);

        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                textOutput.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss"))
                        + " " + login + " : " + textInput.getText() + "\n");
                textInput.setText("");
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Log().save(login, textOutput.getText());
                ChatWindow.super.dispose();
            }
        });
    }
}
