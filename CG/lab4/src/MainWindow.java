import org.omg.CORBA.INTERNAL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    private int WIDTH = 1100;
    private int HEIGHT = 800;

    private DrawPanel drawingPanel = new DrawPanel();

    private JTextField x1Input = new JTextField(21);
    private JTextField y1Input = new JTextField(21);
    private JTextField x2Input = new JTextField(21);
    private JTextField y2Input = new JTextField(21);
    private JTextField radiusInput = new JTextField(21);

    private JButton stepByStepButton;
    private JButton DADButton;
    private JButton brezenkhemButton;
    private JButton brezenkhemCircleButton;
    private JButton clearButton;

    private final double PANEL_SCALE_W = 0.7;
    private final double PANEL_SCALE_H = 1.0;

    private Algorithm algorithm = new Algorithm();

    public MainWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(null);

        drawingPanel.setBounds((int)((1 - PANEL_SCALE_W) * WIDTH), 0, (int)(PANEL_SCALE_W * WIDTH), (int)(PANEL_SCALE_H * HEIGHT));
        add(drawingPanel);


        Font labelFont = new Font("Arial", Font.PLAIN, 20);

        JLabel timeLabel = new JLabel("time:");
        timeLabel.setFont(labelFont);
        timeLabel.setBounds(170, 10, 150, 40);
        add(timeLabel);

        JLabel x1Label = new JLabel("x_1: ");
        x1Label.setFont(labelFont);
        x1Label.setBounds(15, 15, 50, 40);
        add(x1Label);

        x1Input.setBounds(70, 15, 100, 40);
        x1Input.setFont(labelFont);
        add(x1Input);

        JLabel y1Label = new JLabel("y_1: ");
        y1Label.setFont(labelFont);
        y1Label.setBounds(15, 50, 50, 40);
        add(y1Label);

        y1Input.setBounds(70, 50, 100, 40);
        y1Input.setFont(labelFont);
        add(y1Input);


        JLabel x2Label = new JLabel("x_2: ");
        x2Label.setFont(labelFont);
        x2Label.setBounds(15, 110, 50, 40);
        add(x2Label);

        x2Input.setBounds(70, 110, 100, 40);
        x2Input.setFont(labelFont);
        add(x2Input);


        JLabel y2Label = new JLabel("y_2: ");
        y2Label.setFont(labelFont);
        y2Label.setBounds(15, 150, 50, 40);
        add(y2Label);

        y2Input.setBounds(70, 150, 100, 40);
        y2Input.setFont(labelFont);
        add(y2Input);

        JLabel radiusLabel = new JLabel("r-d: ");
        radiusLabel.setFont(labelFont);
        radiusLabel.setBounds(15, 200, 60, 40);
        add(radiusLabel);

        radiusInput.setBounds(70, 200, 100, 40);
        radiusInput.setFont(labelFont);
        add(radiusInput);


        stepByStepButton = new JButton("st by st");
        stepByStepButton.setBounds(15, 250, 250, 40);
        stepByStepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
                try {
                    x1 = Integer.parseInt(x1Input.getText());
                    y1 = Integer.parseInt(y1Input.getText());
                    x2 = Integer.parseInt(x2Input.getText());
                    y2 = Integer.parseInt(y2Input.getText());
                } catch (Exception ex) {

                }
                drawingPanel.drawPoints(algorithm.stepByStep(x1, y1, x2, y2));
                timeLabel.setText("Result: "  + algorithm.lastTime / 1000 + " ms");
            }
        });
        add(stepByStepButton);

        DADButton = new JButton("DAD");
        DADButton.setBounds(15, 300, 250, 40);
        DADButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
                try {
                    x1 = Integer.parseInt(x1Input.getText());
                    y1 = Integer.parseInt(y1Input.getText());
                    x2 = Integer.parseInt(x2Input.getText());
                    y2 = Integer.parseInt(y2Input.getText());
                } catch (Exception ex) {

                }
                drawingPanel.drawPoints(algorithm.cda(x1, y1, x2, y2));
                timeLabel.setText("Result: "  + algorithm.lastTime / 1000  + " ms");

            }
        });
        add(DADButton);

        brezenkhemButton = new JButton("brezenkhem");
        brezenkhemButton.setBounds(15, 350, 250, 40);
        brezenkhemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
                try {
                    x1 = Integer.parseInt(x1Input.getText());
                    y1 = Integer.parseInt(y1Input.getText());
                    x2 = Integer.parseInt(x2Input.getText());
                    y2 = Integer.parseInt(y2Input.getText());
                } catch (Exception ex) {

                }
                drawingPanel.drawPoints(algorithm.brezenkhem(x1, y1, x2, y2));
                timeLabel.setText("Result: "  + algorithm.lastTime / 1000  + " ms");

            }
        });
        add(brezenkhemButton);

        brezenkhemCircleButton = new JButton("bezenkhem c-le");
        brezenkhemCircleButton.setBounds(15, 400, 250, 40);
        brezenkhemCircleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x1 = 0, y1 = 0, r = 0;
                try {
                    x1 = Integer.parseInt(x1Input.getText());
                    y1 = Integer.parseInt(y1Input.getText());
                    r = Integer.parseInt(radiusInput.getText());
                } catch (Exception ex) {

                }
                drawingPanel.drawPoints(algorithm.brezenkhemCircle(x1, y1, r));
                timeLabel.setText("Result: "  + algorithm.lastTime / 1000  + " ms");
            }
        });
        add(brezenkhemCircleButton);

        clearButton = new JButton("Delete");
        clearButton.setBounds(15, 500, 250, 40);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.drawPoints(new ArrayList<Point>());
            }
        });

        add(clearButton);

        setResizable(false);
    }



    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.repaint();
    }
}
