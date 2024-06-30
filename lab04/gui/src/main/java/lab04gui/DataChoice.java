//package lab04gui;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class DataChoice extends JFrame implements ActionListener {
//    private JPanel panel1;
//    private JTextField textField1;
//    private JButton confirmButton;
//    private JButton exitButton;
//
//    DataChoice(){
//        setContentPane(panel1);
//        setVisible(true);
//        confirmButton.addActionListener(this);
//        exitButton.addActionListener(this);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        pack();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource().equals(confirmButton)){
////            new DataChart(Integer.parseInt(textField1.getText()),);
//            this.dispose();
//        }
//        if(e.getSource().equals(exitButton)){
//            new MainFrame();
//            this.dispose();
//        }
//    }
//}
