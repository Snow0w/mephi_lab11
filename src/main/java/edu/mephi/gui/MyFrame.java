package edu.mephi.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.mephi.classes.Food;
import edu.mephi.classes.Human;
import edu.mephi.classes.MyException;

public class MyFrame extends JFrame implements ActionListener{
    private static final int HEIGHT = 120;
    private static final int WIDTH = 600;
    private JPanel  panelTop;
    private JPanel  panelCenter;
    private JPanel  panelDown;
    private JComboBox<Human>   boxHuman;
    private JComboBox<Food>   boxFood;
    private JLabel      labelDci;
    private JLabel      labelCurrent;
    private JLabel      labelOver;
    private JTextField      textDci;
    private JTextField      textCurrent;
    private JTextField      textOver;
    private JButton         buttonClear;
    private JButton         buttonAdd;

    public MyFrame(String name) {
        super(name);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());


        panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(1, 2));
        panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(2, 3));
        panelDown = new JPanel();
        panelDown.setLayout(new GridLayout(1, 2));

        boxHuman = new JComboBox<Human>();
        buttonClear = new JButton("Очистить");
        boxHuman.setRenderer(new ComboBoxListRendererCustom());
        panelTop.add(boxHuman);
        panelTop.add(buttonClear);

        labelDci = new JLabel("DCI");
        labelCurrent = new JLabel("Calory");
        labelOver = new JLabel("Overeat?");
        textDci = new JTextField();
        textCurrent = new JTextField();
        textOver = new JTextField();
        textOver.setEditable(false);
        textDci.setEditable(false);
        textCurrent.setEditable(false);
        panelCenter.add(labelDci);
        panelCenter.add(labelCurrent);
        panelCenter.add(labelOver);
        panelCenter.add(textDci);
        panelCenter.add(textCurrent);
        panelCenter.add(textOver);


        boxFood = new JComboBox<Food>();
        buttonAdd = new JButton("Накормить");
        boxFood.setRenderer(new ComboBoxListRendererCustom());
        panelDown.add(boxFood);
        panelDown.add(buttonAdd);

        boxHuman.addActionListener(this);
        buttonAdd.addActionListener(this);
        buttonClear.addActionListener(this);

        this.add(panelTop, BorderLayout.NORTH);
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelDown, BorderLayout.SOUTH);
    }

    public void addHumanBox(ArrayList<Human> list) {
        for(int i = 0; i < list.size(); i++) {
            boxHuman.addItem(list.get(i));
        }
    }

    public void addFoodBox(ArrayList<Food> list) {
        for(int i = 0; i < list.size(); i++) {
            boxFood.addItem(list.get(i));
        }
    }

    private void showHuman(Human human) {
        double dci = human.calc_dci();
        double eat = human.getDayEatenCalory();

        textDci.setText(String.valueOf(dci));
        textCurrent.setText(String.valueOf(eat));
        if (eat <= dci)
            textOver.setText("No");
        else
            textOver.setText("Yes");
    }

    private void addFoodToHuman(Human human, Food food) {
        Double num = 0.0;

        System.out.println("hello");
        String input = JOptionPane.showInputDialog(this, "Grams: ", "100");
        try {
            num = Double.parseDouble(input);
            if (num < 0)
                throw new MyException("Not positive!");
        } catch (MyException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Hell, no", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Not a number", "Hell, no", JOptionPane.ERROR_MESSAGE);
        }
        human.addDayEatenCalory(food.calc_calory(num));
    }

	@Override
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boxHuman) {
            Human human = (Human) boxHuman.getSelectedItem();
            showHuman(human);
        }
        if (e.getSource() == buttonClear) {
            Human human = (Human) boxHuman.getSelectedItem();
            human.setDayEatenCalory(0);
            showHuman(human);
        }
        if (e.getSource() == buttonAdd) {
            Human human = (Human) boxHuman.getSelectedItem();
            Food food = (Food) boxFood.getSelectedItem();
            System.out.println(human + " " + food);
            addFoodToHuman(human, food);
            showHuman(human);
        }
	}
}
