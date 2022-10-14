package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.Vector;

public class Calculator {
        // initialize the window
        JFrame frame = new JFrame("Calculator");

        // operation num 1, 2, signal, result
        String str1 = "0";
        String str2 = "0";
        String signal = "+";
        String result = "";

        // state
        int k1 = 1; // the direction of input 1:str1 | 2:str2
        int k2 = 1; // count the signal
        int k3 = 1; // whether str1 can be cleared
        int k4 = 1; // whether str2 can eb cleared
        int k5 = 1; // whether '.' can input

        // like a register, record whether press signal key constantly
        JButton store;

        // store operation signal
        @SuppressWarnings("rawtypes")
        Vector vt = new Vector(20, 10);

        // show the result
        JTextField result_TextField = new JTextField(result, 20);
        // some basic buttons
        JButton clear_Button = new JButton("Clear");
        JButton button0 = new JButton("0");
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton button_point = new JButton(".");
        JButton button_add = new JButton("+");
        JButton button_minus = new JButton("-");
        JButton button_mul = new JButton("*");
        JButton button_div = new JButton("/");
        // the button of "equal", which means calculate the result
        JButton button_equal = new JButton("=");

        public Calculator(){
                // set the location
                frame.setLocation(300, 200);
                // set the window can't be resize
                frame.setResizable(false);
                // initial the operation panel
                JPanel pan1 = new JPanel();
                // set the GridLayout
                pan1.setLayout(new GridLayout(4, 4, 5, 5));
                // fill the panel with buttons
                pan1.add(button7);
                pan1.add(button8);
                pan1.add(button9);
                pan1.add(button_div);
                pan1.add(button4);
                pan1.add(button5);
                pan1.add(button6);
                pan1.add(button_mul);
                pan1.add(button1);
                pan1.add(button2);
                pan1.add(button3);
                pan1.add(button_minus);
                pan1.add(button0);
                pan1.add(button_point);
                pan1.add(button_equal);
                pan1.add(button_add);

                // initial the clear and result panel
                JPanel pan2 = new JPanel();
                pan2.setLayout(new BorderLayout());
                // left
                pan2.add(result_TextField, BorderLayout.WEST);
                // right
                pan2.add(clear_Button, BorderLayout.EAST);

                // add panel to frame
                frame.getContentPane().setLayout(new BorderLayout());
                frame.getContentPane().add(pan2, BorderLayout.NORTH);
                frame.getContentPane().add(pan1, BorderLayout.SOUTH);

                frame.pack();
                frame.setVisible(true);

                // activation
                class Listener implements ActionListener{
                        @Override
                        public void actionPerformed(ActionEvent e){
                                // get the source from event
                                String ss1 = ((JButton)e.getSource()).getText();
                                store = (JButton)e.getSource();
                                vt.add(store);
                                if(k1 == 1){
                                        if(k3 == 1){
                                                str1 = "";
                                                k5 = 1;
                                        }
                                        str1 += ss1;
                                        k3 += 1;
                                        result_TextField.setText(str1);
                                } else if(k1 == 2){
                                        if(k4 == 1){
                                                str2 = "";
                                                k5 = 1;
                                        }
                                        str2 += ss1;
                                        k4 += 1;
                                        result_TextField.setText(str2);
                                }
                        }
                }

                class Listener_Point implements ActionListener{
                        @Override
                        public void actionPerformed(ActionEvent e){
                                store = (JButton) e.getSource();
                                vt.add(store);
                                if(k5 == 1){
                                        String ss2 = ((JButton)e.getSource()).getText();
                                        if(k1 == 1){
                                                if(k3 == 1){
                                                        str1 = "";
                                                        k5 = 1;
                                                }
                                                str1 += ss2;
                                                k3 += 1;
                                                result_TextField.setText(str1);
                                        } else if(k1 == 2){
                                                if(k4 == 1){
                                                        str2 = "";
                                                        k5 = 1;
                                                }
                                                str2 += ss2;
                                                k4 += 1;
                                                result_TextField.setText(str2);
                                        }
                                }
                        }
                }

                class Listener_Signal implements ActionListener{
                        public void actionPerformed(ActionEvent e){
                                String ss2 = ((JButton)e.getSource()).getText();
                                store = (JButton) e.getSource();
                                vt.add(store);
                                if(k2 == 1){
                                        k1 = 2;
                                        k5 = 1;
                                        signal = ss2;
                                        k2 += 1;
                                }else {
                                        int a = vt.size();
                                        JButton c =(JButton) vt.get(a - 2);
                                        if(!(c.getText().equals("+")) && !(c.getText().equals("-")) && !(c.getText().equals("*")) && !(c.getText().equals("/"))){
                                                cal();
                                                str1 = result;
                                                k1 = 2;
                                                k5 = 1;
                                                k4 = 1;
                                                signal = ss2;
                                        }
                                        k2 += 1;
                                }
                        }
                }

                class Listener_Equal implements ActionListener{
                        @Override
                        public void actionPerformed(ActionEvent e){
                                store = (JButton) e.getSource();
                                vt.add(store);
                                cal();
                                k1 = 1;
                                k2 = 1;
                                k3 = 1;
                                k4 = 1;
                                str1 = result;
                        }
                }

                class Listener_Clear implements ActionListener{
                        @Override
                        public void actionPerformed(ActionEvent e){
                                store = (JButton) e.getSource();
                                vt.add(store);
                                k5 = 1;
                                k2 = 1;
                                k1 = 1;
                                k3 = 1;
                                k4 = 1;
                                str1 = "0";
                                str2 = "0";
                                signal = "";
                                result = "";
                                result_TextField.setText(result);
                                vt.clear();
                        }
                }

                // listener logical
                // listen equal
                Listener_Equal jt_equal = new Listener_Equal();
                button_equal.addActionListener(jt_equal);
                // listen num
                Listener jt = new Listener();
                button0.addActionListener(jt);
                button1.addActionListener(jt);
                button2.addActionListener(jt);
                button3.addActionListener(jt);
                button4.addActionListener(jt);
                button5.addActionListener(jt);
                button6.addActionListener(jt);
                button7.addActionListener(jt);
                button8.addActionListener(jt);
                button9.addActionListener(jt);
                // listen signal
                Listener_Signal jt_signal = new Listener_Signal();
                button_add.addActionListener(jt_signal);
                button_minus.addActionListener(jt_signal);
                button_mul.addActionListener(jt_signal);
                button_div.addActionListener(jt_signal);
                // listen clear
                Listener_Clear jt_clear = new Listener_Clear();
                clear_Button.addActionListener(jt_clear);
                // listen point
                Listener_Point jt_point = new Listener_Point();
                button_point.addActionListener(jt_point);
                frame.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e){
                                System.exit(0);
                        }
                });
                try {
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                } catch(Exception e) {
                        e.printStackTrace();
                }
        }

        public void cal(){
                double a2, b2;
                double result2 = 0;
                String c = signal;
                if(c.equals(""))
                        result_TextField.setText("Please input operator");
                else{
                        if(str1.equals("."))
                                str1 = "0.0";
                        if(str2.equals("."))
                                str2 = "0.0";
                        a2 = Double.parseDouble(str1);
                        b2 = Double.parseDouble(str2);
                        if(c.equals("+"))
                                result2 = a2 + b2;
                        if(c.equals("-"))
                                result2 = a2 - b2;
                        if(c.equals("*")) {
                                BigDecimal m1 = new BigDecimal(Double.toString(a2));
                                BigDecimal m2 = new BigDecimal(Double.toString(b2));
                                result2 = m1.multiply(m2).doubleValue();
                        }
                        if(c.equals("/")) {
                                if (b2 == 0)
                                        result2 = 0;
                                else
                                        result2 = a2 / b2;
                        }
                        result = ((new Double(result2)).toString());
                        result_TextField.setText(result);
                }


        }


}



