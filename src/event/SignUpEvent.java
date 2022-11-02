package event;

import api.SignUpApi;
import entity.Member;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpEvent implements ActionListener {
    Member member;
    JTextField idField;
    JTextField passwordField;
    JTextField nameField;
    JTextField phoneField;
    JTextField ageField;

    public SignUpEvent(JTextField id, JTextField password, JTextField name, JTextField phone, JTextField age) {
        idField = id;
        passwordField = password;
        nameField = name;
        phoneField = phone;
        ageField = age;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        member = new Member(1L, idField.getText(), passwordField.getText(), nameField.getText(),
                phoneField.getText(), Integer.parseInt(ageField.getText()));
        new SignUpApi(member);
    }
}
