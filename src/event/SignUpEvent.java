package event;

import api.MemberApi;
import api.SignUpApi;
import entity.Member;
import ui.MainHomeGui;

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

    JFrame signUpFrame;

    public SignUpEvent(JTextField id, JTextField password, JTextField name, JTextField phone, JTextField age, JFrame frame) {
        idField = id;
        passwordField = password;
        nameField = name;
        phoneField = phone;
        ageField = age;
        signUpFrame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(idField.getText().isBlank() || passwordField.getText().isBlank() || nameField.getText().isBlank()
        || phoneField.getText().isBlank() || ageField.getText().isBlank()){
            System.out.println("모든 항목을 입력하세요!");
        }
        else {
            member = new Member(idField.getText(), passwordField.getText(), nameField.getText(),
                    phoneField.getText(), Integer.parseInt(ageField.getText()));
            MemberApi.signUp(member);

            signUpFrame.setVisible(false);
            new MainHomeGui();
        }
    }
}
