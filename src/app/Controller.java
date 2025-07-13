package app;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.util.*;

// Each item will have its own position (class).
// Every position will have name, image, price, quantity

public class Controller {
    // Declare all images, labels, buttons, and textArea for GUI elements
    @FXML private ImageView item1, item2, item3, item4, item5, item6, item7, item8; // represents each menu item (grilled cheese, coke, water, sprite, rootbeer)
    @FXML private Label item1Quantity, item2Quantity, item3Quantity, item4Quantity, item5Quantity, item6Quantity, item7Quantity, item8Quantity;
    @FXML private Button buyGrilledCheese, buyWater, buyCoke, buySprite, buyRootBeer, clearAll, quitProgram;
    @FXML private TextArea totalPriceText;

    private AdminController adminController; // Reference to AdminController
    List<Position> positions = new ArrayList<>(); // array holds all the positions for every item
    double total = 0.0;  // variable to store total which is also displayed bottom left
    double totalRevenue; // varibale to hold total revenue (used for admin panel)
    private int[] itemsSold = new int[8]; // counts how many items have been sold
    private ImageView[] totalImages; // stores all images 

    // helps set the controller class to an instance of the adminController 
    // this is used to form a connection between the two controllers
    // this is helpful to keep both of the controllers synchronized in cases where I need one thing from controller class to go to the admin class
    // for example, the totalPrice is updated in both the admin controller and controller class and appears the same when updated for both classes
    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    // getter for positions arrayList
    public List<Position> getPositions() {
        return positions;
    }

    // getter for total
    public double getTotal() {
        return total;
    }

    // getter for total revenue
    public double getTotalRevenue() {
        return totalRevenue;
    }

    // getter for itemsSold
    public int[] getItemsSold() {
        return itemsSold;
    }

    // Class to represent each position in the GUI. Each position must have the item name, price, initial quantity, current quantity, and quantity label
    public static class Position {
        String item;
        double price;
        int initialQuantity;
        int currentQuantity; 
        Label quantityLabel;

        // Constructor to set all items and different methods for each postition such as reset and updateLabel
        Position(String item, double price, int currentQuantity, Label quantityLabel) {
            this.item = item;
            this.price = price;
            this.initialQuantity = currentQuantity;
            this.currentQuantity = currentQuantity;
            this.quantityLabel = quantityLabel;
            updateLabel(); // set label when the constructor is called
        }

        // Getter for price
        String getItem() {
            return item;
        }

        // Getter for price 
        double getPrice() {
            return price;
        }

        double getInitialQuantity() {
            return initialQuantity;
        }

        // Getter for currentQuantity
        int getCurrentQuantity() {
            return currentQuantity;
        }

        // Setter for currentQuantity. Also update label when currentQuantity gets updated
        void setCurrentQuantity(int currentQuantity) {
            this.currentQuantity = currentQuantity;
            updateLabel(); 
        }

        // Reset the quantity to initial value for a specific position
        void resetQuantity() {
            setCurrentQuantity(initialQuantity); 
        }

        // Update the label for a specific position
        void updateLabel() {
            quantityLabel.setText("Qty: " + currentQuantity);
        }
    }

    @FXML
    private void initialize() { // initialize method will run the code once everything is loaded. this method will store each position in the positions array and initialize all images
        totalImages = new ImageView[] {item1, item2, item3, item4, item5, item6, item7, item8}; // array to hold all images
        Label[] totalLabels = {item1Quantity, item2Quantity, item3Quantity, item4Quantity, item5Quantity, item6Quantity, item7Quantity, item8Quantity}; // array to hold all quantity labels

        // Define the items directly in the loop
        String[] items = {"Grilled Cheese", "Grilled Cheese", "Water", "Coke", "Sprite", "Root Beer", "Coke", "Sprite"};
        int[] quantities = {32, 32, 16, 16, 16, 16, 16, 16};
        double[] prices = {2.50, 2.50, 1.50, 1.50, 1.50, 1.50, 1.50, 1.50};
        String[] imageFiles = {"grilled_cheese.png", "grilled_cheese.png", "water.png", "coke.png", "sprite.png", "root_beer.png", "coke.png", "sprite.png"};

        for (int i = 0; i < items.length; i++) { // for each item assign the correct name, price, quantity and image
            String item = items[i];
            double price = prices[i];
            int qty = quantities[i];
            String file = imageFiles[i];

            Position pos = new Position(item, price, qty, totalLabels[i]); // create new instance of position class and call the constructor to set all items
            positions.add(pos); // add each position into the positions array

            // Load image from the Picture directory
            totalImages[i].setImage(new Image(new File("Pictures", file).toURI().toString()));
           
        }
        updateTotal(); // makes sure current total is set to 0.0
        checkStock(); // sets all items to correct stock 
        for(int i = 0; i < itemsSold.length; i++) {
            itemsSold[i] = 0; // set items sold to 0 for the admin panel
        }
    }

    // method to exit the program
    @FXML
    private void quitButton() {
        Platform.exit();
    }

    // method to buy grilled cheese. works by calling the buyItem function which takes the button and the name of the item
    @FXML
    private void buyGrilledCheeseButton() {
        buyItems(buyGrilledCheese, "Grilled Cheese");
    }

    // buy water button
    @FXML
    private void buyWaterButton() {
        buyItems(buyWater, "Water");
    }
    // buy coke button
    @FXML
    private void buyCokeButton() {
        buyItems(buyCoke, "Coke");
    }
    // buy sprite button
    @FXML
    private void buySpriteButton() {
        buyItems(buySprite, "Sprite");
    }
    // buy rootbeer button
    @FXML
    private void buyRootBeerButton() {
        buyItems(buyRootBeer, "Root Beer");
    }

    // clear button. this method makes the total to 0 and updates calls the updateTotal method which sets the amount to 0
    @FXML
    private void clearAllButton() {
        total = 0.0;
        updateTotal();
        for (Position pos : positions) { // for every item in the array, reset the quantity
            pos.resetQuantity();
        }
        checkStock();
    }

    public void updateTotal() {
        totalPriceText.setText("TOTAL:\n$" + String.format("%.2f", total));
        if(adminController != null) { // handle case where adminController is null
            adminController.syncTotalRevenue(totalRevenue); // passes current revenue from the controller to the adminController so the adminController can see the totalRevenue
        }
    }

    // function checks if the item is in stock or not and if it is not, disable the button
    public void checkStock() {
        boolean grilledCheeseInStock = false;
        boolean spriteInStock = false;
        boolean cokeInStock = false;
        boolean rootBeerInStock = false;
        boolean waterInStock = false;

        for (Position pos : positions) { // iterate through all the positions and check if the items has a quantity greater than 0
            if (pos.item.equals("Grilled Cheese") && pos.getCurrentQuantity() > 0) {
                grilledCheeseInStock = true;
            }
            if (pos.item.equals("Sprite") && pos.getCurrentQuantity() > 0) {
                spriteInStock = true;
            }
            if (pos.item.equals("Coke") && pos.getCurrentQuantity() > 0) {
                cokeInStock = true;
            }
            if (pos.item.equals("Root Beer") && pos.getCurrentQuantity() > 0) {
                rootBeerInStock = true;
            }
            if (pos.item.equals("Water") && pos.getCurrentQuantity() > 0) {
                waterInStock = true;
            }
        }

        // if the grilled cheese is in stock, set the disable button to false
        if (grilledCheeseInStock) {
            buyGrilledCheese.setDisable(false); 
        } else {
            buyGrilledCheese.setDisable(true); // if the grilled cheese is not in stock, set the disable button to true
        }

        // same for all buttons
        if (spriteInStock) {
            buySprite.setDisable(false);
        } else {
            buySprite.setDisable(true);
        }

        if (cokeInStock) {
            buyCoke.setDisable(false);
        } else {
            buyCoke.setDisable(true);
        }

        if (rootBeerInStock) {
            buyRootBeer.setDisable(false);
        } else {
            buyRootBeer.setDisable(true);
        }

        if (waterInStock) {
            buyWater.setDisable(false);
        } else {
            buyWater.setDisable(true);
        }
    }

    private void buyItems(Button itemButton, String nameOfItem) {
        Position greatestQuantityPos = null; // keep track of the item with the highest stock
        int slotIndex = -1;
        for(int i = 0; i < positions.size(); i++) { // iterate through all positions
            Position pos = positions.get(i); // get the current position
            if (pos.item.equals(nameOfItem) && pos.getCurrentQuantity() > 0) { // if the current position is greater than 0 and it equals the name of the passed in value
                if (greatestQuantityPos == null || pos.getCurrentQuantity() > greatestQuantityPos.getCurrentQuantity()) { // if current pos is greater
                    greatestQuantityPos = pos; // make the greatestQuantityPos equal to currentPos
                    slotIndex = i;
                }
            }
        }

        if (greatestQuantityPos == null) {
            itemButton.setDisable(true); // disable button if no position was found
            return;
        }

        updateTotal(); // ensures total is same

        // change total, totalRevenue and itemsSold
        greatestQuantityPos.setCurrentQuantity(greatestQuantityPos.getCurrentQuantity() - 1); // reduce by 1 per click
        total = total + greatestQuantityPos.price; // increase the total variable
        totalRevenue = totalRevenue + greatestQuantityPos.price;
        itemsSold[slotIndex]++; // increase the itemSold for whatever was sold
        updateTotal(); // update the label to the new price

        boolean outOfStock = true; // track if out of stock for all positions
        for (Position pos : positions) {
            if (pos.item.equals(nameOfItem) && pos.getCurrentQuantity() > 0) { // if there is still some items in stock, set the boolean to false and break out of the loop
                outOfStock = false; 
                break;
            }
        }

        if(adminController != null) {
            adminController.changeItemsSold(slotIndex, itemsSold[slotIndex]); // update the admin panel when there is any sale
        }

        if (outOfStock) { // if the item is out of stock, disable the button
            itemButton.setDisable(true);
        }
    }

    public void changeSlotPic(int slotIndex, String newItem) {
        itemsSold[slotIndex] = 0; // make sure the new slot is initalized to 0
        positions.get(slotIndex).item = newItem;  // Update the item in the Position object
        totalImages[slotIndex].setImage(new Image(new File("Pictures", newItem.toLowerCase().replace(" ", "_") + ".png").toURI().toString())); // replace space from name with _ because picture names have _
        checkStock();
    }
}