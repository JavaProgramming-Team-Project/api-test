package ui;

import javax.swing.*;
import java.awt.*;

public class MainHomeGui extends JFrame {
    private Container ct;
    private JPanel panel;

    public MainHomeGui() {
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("메인 화면");
        panel.add(label);

        setContentPane(panel);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 500);
        setTitle("Main");
    }
}
