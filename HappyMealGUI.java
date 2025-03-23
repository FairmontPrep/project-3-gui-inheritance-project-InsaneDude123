import javax.swing.*;

import java.awt.*;

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

// Abstract parent class: happy meal outline

abstract class HappyMeal extends JPanel {

    private BufferedImage happyMealOutlineImage;
    protected JLabel baseLabel;

    public HappyMeal() {

        loadImage();

        baseLabel = new JLabel("Look!");
        baseLabel.setFont(new Font("Arial", Font.BOLD, 12));
        baseLabel.setForeground(Color.BLACK);
        baseLabel.setBounds(20, 20, 200, 20); // Position the label
        add(baseLabel);

    }

    protected abstract void loadImage();

    protected void setHappyMealOutlineImage(String filePath) {

        try {

            happyMealOutlineImage = ImageIO.read(new File("happyMealOutline.png"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    protected BufferedImage getHappyMealOutlineImage() {

        return happyMealOutlineImage;

    }

    

    @Override

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (happyMealOutlineImage != null) {

            g.drawImage(happyMealOutlineImage, 0, 0, this);

        }

    }

}

// Child class: Adds nuggets

class HappyMealWithNuggets extends HappyMeal {

    private BufferedImage nuggetsImage;

    public HappyMealWithNuggets() {

        super();

        baseLabel.setText(baseLabel.getText() + " Your Happy Meal");

    }

    @Override

    protected void loadImage() {

        setHappyMealOutlineImage("happyMealOutline.png");

        try {

            nuggetsImage = ImageIO.read(new File("happyMealNuggets.png"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @Override

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (nuggetsImage != null) {

            g.drawImage(nuggetsImage, 0, 0, this);

        }

    }

}

// Grandchild class: Adds fries

class HappyMealWithFries extends HappyMealWithNuggets {

    protected BufferedImage friesImage;

    public HappyMealWithFries() {

        super();

        baseLabel.setText(baseLabel.getText() + " came with");

    }

    @Override

    protected void loadImage() {

        super.loadImage();

        try {

            friesImage = ImageIO.read(new File("happyMealFries.png"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @Override

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (friesImage != null) {

            g.drawImage(friesImage, 0, 0, this);

        }

    }

}

// Great-grandchild class: Adds milk instead of coke

class HappyMealWithDrink extends HappyMealWithFries {

    private BufferedImage drinkImage;

    private String s;

    public HappyMealWithDrink() {

        super();

        baseLabel.setText(baseLabel.getText() + s);

    }

    @Override

    protected void loadImage() {

        super.loadImage();

        try {

            Random random = new Random();
            int n = random.nextInt(2)+1;

            if (n == 1){
                drinkImage = ImageIO.read(new File("happyMealMilk.png"));
                s = " milk!";
            }else{
                drinkImage = ImageIO.read(new File("happyMealCoke.png"));
                s = " coke!";
            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @Override

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (drinkImage != null) {

            g.drawImage(drinkImage, 0, 0, this);

        }

    }

}

// Main class to display GUI

public class HappyMealGUI {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Happy Meal GUI");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setSize(300, 300);

            

            // Demonstrating polymorphism

            HappyMeal meal = new HappyMealWithDrink();

            frame.add(meal);

            frame.setVisible(true);

        });

    }

}