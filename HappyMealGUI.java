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

    public HappyMeal() {

        loadImage();

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

class HappyMealWithMilk extends HappyMealWithFries {

    private BufferedImage milkImage;

    public HappyMealWithMilk() {

        super();

    }

    @Override

    protected void loadImage() {

        super.loadImage();

        try {

            milkImage = ImageIO.read(new File("happyMealMilk.png"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @Override

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (milkImage != null) {

            g.drawImage(milkImage, 0, 0, this);

        }

    }

}

//second great-grandchild class: Add coke instead of milk

class HappyMealWithCoke extends HappyMealWithFries {

    private BufferedImage cokeImage;

    public HappyMealWithCoke() {

        super();

    }

    @Override

    protected void loadImage() {

        super.loadImage();

        try {

            cokeImage = ImageIO.read(new File("happyMealCoke.png"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @Override

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (cokeImage != null) {

            g.drawImage(cokeImage, 0, 0, this);

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

            HappyMeal meal;

            Random random = new Random();
            int n = random.nextInt(2)+1;

            if (n == 1){
                meal = new HappyMealWithCoke();
                System.out.println("You got Coke!");
            }else{
                meal = new HappyMealWithMilk();
                System.out.println("You got Milk!");
            }

            frame.add(meal);

            frame.setVisible(true);

        });

    }

}