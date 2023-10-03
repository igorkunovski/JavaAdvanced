// – Создать окно клиента чата.
// Окно должно содержать JtextField для ввода логина, пароля, IP-адреса сервера, порта подключения к серверу,
// область ввода сообщений, JTextArea область просмотра сообщений чата и
// JButton подключения к серверу и отправки сообщения в чат.
// Желательно сразу сгруппировать компоненты, относящиеся к серверу сгруппировать
// на JPanel сверху экрана, а компоненты, относящиеся к отправке сообщения –на JPanel снизу.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConnectWindow extends JFrame implements KeyListener{

    static private final int WINDOW_HEIGHT = 240;
    static private final int WINDOW_WIDTH = 420;
    static private final int WINDOW_POS_X = 400;
    static private final int WINDOW_POS_Y = 300;
    static private final String WINDOW_NAME = "Server connection";

    JLabel loginLabel = new JLabel("Login: ");
    JTextField loginField = new JTextField();
    JLabel passwordLabel = new JLabel("Password: ");
    JPasswordField passwordField = new JPasswordField();
    JLabel serverLabel = new JLabel("Server data: ");
    JTextField serverField = new JTextField();
    JButton buttonConnect = new JButton("Connect");
    JButton buttonExit = new JButton("Exit");
    JPanel fieldsPanel = new JPanel(new GridLayout(3, 3));
    JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
    JPanel gridMain = new JPanel(new GridLayout(2, 1));


    ConnectWindow() {

        //свойства окна
        setTitle(WINDOW_NAME);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        fieldsPanel.add(loginLabel);
        fieldsPanel.add(loginField);
        fieldsPanel.add(passwordLabel);
        fieldsPanel.add(passwordField);
        fieldsPanel.add(serverLabel);
        fieldsPanel.add(serverField);

        buttonsPanel.add(buttonConnect);
        buttonsPanel.add(buttonExit);

        gridMain.add(fieldsPanel);
        gridMain.add(buttonsPanel, BorderLayout.SOUTH);

        add(gridMain);

        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                clearTextFields();
                new ChatWindow(login);
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
        addKeyListener(this);
    }

    private void clearTextFields(){
        loginField.setText("");
        passwordField.setText("");
        serverField.setText("");
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String login = loginField.getText();
            new ChatWindow(login);
            System.out.println("ENTER");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
