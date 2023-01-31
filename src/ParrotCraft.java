//package assignment2_000123456;
/*
*This code displays a dialog that welcomes the user to the Parrot Craft game using JOptionPane.

*Additionally, the user can choose to feed, hit, or play with a parrot in this simulation of a straightforward parrot game.

*The user's activities have an impact on the parrot's state, including its health, tameness, quantity of hearts, and crackercrumbs.

*The user can interact with the parrot by calling several methods from the Parrot class in the main method of the ParrotCraft class.

*Additionally, the user is always free to leave the game.
*/

/**
 *
 * @author User
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.*;
import javax.imageio.ImageIO;

//Implementation of the Parrot Class
class Parrot {
    //Parrot's name
    private String name;
    //Parrot's health
    private int health;
    //Parrot's number of hearts that represents its health
    private int hearts;
    // amount of crackercrumbs in its stomach in kilograms
    private double crumbs;
    // parrot is tamed or untamed 
    private boolean tamed;
    // parrot is dead or alive 
    private boolean dead;
    // parrot is flying or sitting
    private boolean flying;
    
    // constructor for parrot class
    public Parrot() {
        name = "Rufus";
        health = 3;
        crumbs = 0.1;
        tamed = false;
        flying = true;
    }
    
    //Feed method which increases the parrot's hearts and crackercrumbs
    public void feed(double amount) {
        this.crumbs += amount;
        this.hearts++;
        if (this.hearts > 3) {
            this.hearts = 3;
        }
        // Checking if the parrot is getting sick
        if (this.crumbs >= 2.5) {
            this.hearts -= 2;
            if (this.hearts <= 0) {
                this.dead = true;
            }
        }
        // Checking if the parrot gets tamed
        double chance = 20 * this.crumbs;
        if (chance >= Math.random() * 100) {
            this.tamed = true;
        }
    }
    
    //This Method makes the parrot to sit
    public void sit() {
        if (tamed) {
            flying = false;
        }
    }

    //This Method is used to make the parrot fly
    public void fly() {
        if (tamed) {
            flying = true;
        }
    }
    
    //Method to enable one parrot play with another parrot and untame them both
    public void play(Parrot parrot) {
        if (tamed && parrot.isTamed()) {
            tamed = false;
            parrot.untame();
        }
    }

    //Method for hitting the parrot
    public void hit() {
        health--;
        if (health <= 0) {
            health = 0;
        }
        tamed = false;
    }

    //This Method checks if the parrot is dead or alive
    public boolean isDead() {
        return health <= 0;
    }
    
    //Method to get the number of crackercrumbs the parrot has in its stomach
    public double getCrumbs() {
    return this.crumbs;
    }
    
    //Method to get the number of hearts
    public int getHearts(){
        return this.hearts;
    }
    
    //Method to return the number of hearts
    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    //Method to get the name for the parrots
    public String getName() {
    return name;
    }

    //Method to return the name of the parrots
    public void setName(String name) {
        this.name = name;
    }
    
    //Method to return the amount of crackercrumbs the parrot has in its stomach
    public void setCrumbs(double crumbs) {
        this.crumbs = crumbs;
    }

    //This Method is used to untame the parrot
    public void untame() {
        tamed = false;
    }

    //Method to check if the parrot is flying or sitting
    public boolean isFlying(){
        return flying;
    }
    //Method to check if the parrot is tamed or untamed
    public boolean isTamed() {
        return tamed;
    }
    
    //Method that returns a full report for specific parrot
    public String toString() {
    String status = "";
    if (this.isDead()) {
        status = "DEAD";
    } else {
        status = "Alive";
    }
    if (this.tamed) {
        status += " Tamed ";
    } else {
        status += " Untamed ";
    }
    if (this.flying) {
        status += "Flying ";
    } else {
        status += "Sitting ";
    }

    return this.name + ": " + status + this.crumbs + "kg crackercrumbs, " + this.health + " hearts";
    }
}

//Implementation of the ParrotCraft class
public class ParrotCraft {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws Exception{
    //Create a new parrot name Rufus
    Parrot parrot1 = new Parrot();
    parrot1.setName("Rufus");
    parrot1.setCrumbs(2.1);
    //Hit the parrot Rufus
    parrot1.hit();
    parrot1.hit();
    parrot1.hit();
    //Create a new parrot name Melvin
    Parrot parrot2 = new Parrot();
    parrot2.setName("Melvin");
    parrot2.setCrumbs(0.6);
    parrot2.setHearts(2);
    parrot2.fly();
    //Create a new parrot name Maxwell
    Parrot parrot3 = new Parrot();
    parrot3.setName("Maxwell");
    parrot3.setCrumbs(1.0);
    //Feed the parrot Maxwell
    parrot3.feed(1);
    //Make the parrot Maxwell to sit
    parrot3.sit();
    
    
    /*Changing the JoptionPane background color
     *UIManager UI=new UIManager();
     *UI.put("OptionPane.background",Color.BLUE);
     *UI.put("Panel.background", Color.orange);
    */
     
     
     //Display dialog that shows welcome to parrotcraft world message to users
    //Displaying an image using url
    Image image = ImageIO.read(new URL("https://media.tenor.com/J_0i11cOZmcAAAAj/bird.gif"));
    JLabel label = new JLabel(new ImageIcon(image));
    JPanel panel = new JPanel(new GridBagLayout());
    
    JPanel textPanel = new JPanel(new GridLayout(2,4));
    textPanel.setBackground(Color.orange);
    textPanel.add(new JLabel("WELCOME TO PARROTCRAFT WORLD!!"));
    //Displaying an image in the textpanel
    textPanel.add(label);
    
    //Adding label of the Dialog
    JPanel panel2 = new JPanel(new BorderLayout());
    panel2.add(textPanel);
    panel2.add(panel,BorderLayout.EAST);
    JOptionPane.showMessageDialog(null,panel2,"ParrotCraft",JOptionPane.DEFAULT_OPTION);
    
    //JOptionPane.showMessageDialog(null, "WELCOME TO"+"\n"+"PARROTCRAFT WORLD!!");
    Scanner scan = new Scanner(System.in);
    OUTER:
    
    //Show the details of the parrot using while loop
    while (true) {
    System.out.println("1. Tamed DEAD Parrot " + parrot1.getName() + ": " + parrot1.getCrumbs() + "kg crackercrumbs, " + parrot1.getHearts() + " hearts");
    System.out.println("2. Untamed Parrot " + parrot2.getName() + ": " + parrot2.getCrumbs() + "kg crackercrumbs, " + parrot2.getHearts() + " hearts, " + (parrot2.isFlying() ? "flying" : "sitting"));
    System.out.println("3. Tamed Parrot " + parrot3.getName() + ": " + parrot3.getCrumbs() + "kg carckercrumbs, " + parrot3.getHearts() + " hearts, " + (parrot3.isFlying() ? "flying" : "sitting"));
    System.out.println("1. Feed 2. Command 3. Play 4. Hit 5. Quit");
    System.out.print("Choice: ");
    
    //Initialize the variable choice that will helo us to make selection of differents commands
    int choice = scan.nextInt();
    
    //Using Switch case statements
    switch (choice) {
    //When user chooses option 1 that is feeding the parrot
        case 1:
            {
                System.out.print("Which parrot? ");
                int parrotChoice = scan.nextInt();
                if (parrotChoice == 1 && !parrot1.isDead()) {
                    System.out.print("How much? ");
                    double amount = scan.nextDouble();
                    parrot1.feed(amount);
                    if (parrot1.isTamed()) {
                        // Display the action done in a dialog box
                        JOptionPane.showMessageDialog(null,"You tamed " + parrot1.getName() + "!");
                        System.out.println("You tamed " + parrot1.getName() + "!");
                    }
                    System.out.println(parrot1.toString());
                } else if (parrotChoice == 2 && !parrot2.isDead()) {
                    System.out.print("How much? ");
                    double amount = scan.nextDouble();
                        parrot2.feed(amount);
                        if (parrot2.isTamed()) {
                            // Display the action done in a dialog box
                            JOptionPane.showMessageDialog(null,"You tamed " + parrot2.getName() + "!");
                            System.out.println("You tamed " + parrot2.getName() + "!");
                        }
                    System.out.println(parrot2.toString());
                } else if (parrotChoice == 3 && !parrot3.isDead()) {
                        System.out.print("How much? ");
                        double amount = scan.nextDouble();
                        parrot3.feed(amount);
                        if (parrot3.isTamed()) {
                            // Display the action done in a dialog box
                            JOptionPane.showMessageDialog(null,"You tamed " + parrot3.getName() + "!");
                            System.out.println("You tamed " + parrot3.getName() + "!");
                        }
                    System.out.println(parrot3.toString());
                } else {
                System.out.println("It's stone dead");
                }
              break;
            }
            //When user chooses option 2 that is Commanding the parrot to fly or sit
            case 2:
                    {
                        System.out.print("Which parrot? ");
                        int parrotChoice = scan.nextInt();
                        if (parrotChoice == 1 && !parrot1.isDead()) {
                            System.out.print("Fly or Sit? ");
                            String command = scan.next();
                            if (command.equalsIgnoreCase("sit")) {
                                parrot1.sit();
                            } else if (command.equalsIgnoreCase("fly")) {
                                parrot1.fly();
                            } else {
                                System.out.println("Invalid command");
                            }
                        } else if (parrotChoice == 2 && !parrot2.isDead()) {
                            System.out.print("Fly or Sit? ");
                            String command = scan.next();
                            if (command.equalsIgnoreCase("sit")) {
                                parrot2.sit();
                            } else if (command.equalsIgnoreCase("fly")) {
                                parrot2.fly();
                            } else {
                                System.out.println("Invalid command");
                            }
                        } else if (parrotChoice == 3 && !parrot3.isDead()) {
                            System.out.print("Fly or Sit? ");
                            String command = scan.next();
                            if (command.equalsIgnoreCase("sit")) {
                                parrot3.sit();
                            } else if (command.equalsIgnoreCase("fly")) {
                                parrot3.fly();
                            } else {
                                System.out.println("Invalid command");
                            }
                        } else {
                            System.out.println("It's stone dead");
                        }
                      break;
                    }
            //When user chooses option 3 that is one parrot to play with another parrot
                    case 3:
                    {
                    System.out.print("Which parrot? ");
                    int parrotChoice1 = scan.nextInt();
                    System.out.print("Which parrot to play with? ");
                    int parrotChoice2 = scan.nextInt();
                    if (parrotChoice1 == 1 && parrotChoice2 == 2 && !parrot1.isDead() && !parrot2.isDead()) {
                        parrot1.play(parrot2);
                    } else if (parrotChoice1 == 1 && parrotChoice2 == 3 && !parrot1.isDead() && !parrot3.isDead()) {
                        parrot1.play(parrot3);
                    } else if (parrotChoice1 == 2 && parrotChoice2 == 1 && !parrot2.isDead() && !parrot1.isDead()) {
                        parrot2.play(parrot1);
                    } else if (parrotChoice1 == 2 && parrotChoice2 == 3 && !parrot2.isDead() && !parrot3.isDead()) {
                        parrot2.play(parrot3);
                    } else if (parrotChoice1 == 3 && parrotChoice2 == 1 && !parrot3.isDead() && !parrot1.isDead()) {
                        parrot3.play(parrot1);
                    } else if (parrotChoice1 == 3 && parrotChoice2 == 2 && !parrot3.isDead() && !parrot2.isDead()) {
                        parrot3.play(parrot2);
                    } else {
                        System.out.println("It's stone dead");
                    }
                    break;
                    }
            //When user chooses option 4 that is to Hit the parrot
                    case 4:
                    {
                        System.out.print("Which parrot? ");
                        int parrotChoice = scan.nextInt();
                        if (parrotChoice == 1 && !parrot1.isDead()) {
                            parrot1.hit();
                            JOptionPane.showMessageDialog(null,"Ouch!");
                            System.out.println("Ouch!");
                        } else if (parrotChoice == 2 && !parrot2.isDead()) {
                            parrot2.hit();
                            JOptionPane.showMessageDialog(null,"Ouch!");
                            System.out.println("Ouch!");
                        } else if (parrotChoice == 3 && !parrot3.isDead()) {
                            parrot3.hit();
                            JOptionPane.showMessageDialog(null,"Ouch!");
                            System.out.println("Ouch!");
                        } else {
                            System.out.println("It's stone dead");
                        }
                        break;
                    }
            //When user chooses option 5 that is to Exit the game
                                    case 5:
                    break OUTER;
                default:
                    System.out.println("Invalid choice");
                    break;
                }
            }
        }  
    }
