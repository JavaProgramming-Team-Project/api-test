package ui;

import event.SignUpEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainGui extends JFrame {
    private Container ct;

    public MainGui() {
        ct = getContentPane();

        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel phonePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel idLabel = new JLabel("아이디");
        JLabel passwordLabel = new JLabel("비밀번호");
        JLabel nameLabel = new JLabel("이름");
        JLabel phoneLabel = new JLabel("휴대폰 번호");
        JLabel ageLabel = new JLabel("나이");

        JTextField idField = new JTextField(13);
        JTextField passwordField = new JTextField(13);
        JTextField nameField = new JTextField(13);
        JTextField phoneField = new JTextField(13);
        JTextField ageField = new JTextField(13);

        JButton signUpBtn = new JButton("가입하기");

        idPanel.add(idLabel);
        idPanel.add(idField);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        namePanel.add(nameLabel);
        namePanel.add(nameField);

        phonePanel.add(phoneLabel);
        phonePanel.add(phoneField);

        agePanel.add(ageLabel);
        agePanel.add(ageField);

        buttonPanel.add(signUpBtn);

        ct.setLayout(new GridLayout(6, 1, 0, 0));

        ct.add(idPanel);
        ct.add(passwordPanel);
        ct.add(namePanel);
        ct.add(phonePanel);
        ct.add(agePanel);
        ct.add(buttonPanel);

        signUpBtn.addActionListener(new SignUpEvent(idField, passwordField, nameField,
                phoneField, ageField));

        setVisible(true);
        setSize(300, 500);
        setTitle("Sign Up");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new MainGui();
    }
}
