package edu.mephi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.mephi.classes.Food;
import edu.mephi.classes.Human;
import edu.mephi.gui.MyFrame;

public class Lab11 {

    private static boolean fillFoodArray(ArrayList<Food> food, String fileName) {
        String text;
        try {
            text = readFoodFile(fileName);
        } catch (Exception e) {
            return false;
        }
        String[] lines = text.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] words = lines[i].split(";");
            try {
                food.add(new Food(words[0], Double.parseDouble(words[1]), Double.parseDouble(words[2]), Double.parseDouble(words[3])));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong input file format!\n BREAK");
                return false;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input file format!\n BREAK");
                return false;
            }
        }
        return true;
    }
    private static String readFoodFile(String fileName)
            throws Exception {
        FileInputStream   fis = null;
        String test = "";
        fis = new FileInputStream(fileName);
        InputStreamReader r = new InputStreamReader(fis, "cp1251");
        StringBuilder sb = new StringBuilder();
        int ch = r.read();
        while(ch >= 0) {
            sb.append((char) ch);
            ch = r.read();
        }
        test = sb.toString();
        fis.close();
        r.close();
        return test;
    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame("App");
        ArrayList<Food> food = new ArrayList<>();
        ArrayList<Human> human = new ArrayList<>();

        human.add(new Human("Nick", 182.0, 89.0, 25, true, 3));
        human.add(new Human("Emma", 164.0, 48.0, 23, false, 1));
        human.add(new Human("Sam", 191.0, 93.0, 30, true, 2));
        human.add(new Human("Elena", 165.0, 50.0, 35, false, 4));

        if (!fillFoodArray(food,  "/Users/kirill/java/11lab/food.csv"))
            return;

        frame.addHumanBox(human);
        frame.addFoodBox(food);
        frame.setVisible(true);
    }
}
