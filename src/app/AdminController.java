package app;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class AdminController {
    // initalize all elements from fxml file
    @FXML private Label qty0, qty1, qty2, qty3, qty4, qty5, qty6, qty7;
    @FXML private Label mainTotalSalesText;
    @FXML private Label sold0, sold1, sold2, sold3, sold4, sold5, sold6, sold7;

    private Label[] quantityLabels;
    private Label[] soldLabels;

    private Controller mainController; // refrence to the mainController
    private List<Controller.Position> positions; // from conrtoller
    private double totalRevenue = 0.0;

    // function initalizes all the data from controller to adminController. Makes it able to view each position, totalRevenue and the mainController
    public void setDataForAdminController(Controller mainController, List<Controller.Position> positions, double totalRevenue) {
        this.positions     = positions;
        this.totalRevenue  = totalRevenue; // Initialize totalRevenue with the current total from the main view
        this.mainController = mainController;
        this.quantityLabels = new Label[]{qty0, qty1, qty2, qty3, qty4, qty5, qty6, qty7};
        this.soldLabels = new Label[]{sold0, sold1, sold2, sold3, sold4, sold5, sold6, sold7};
        updateAllQtyLabels();
       
    }

    // function to change the number of items sold in the admin panel (updates single label)
    public void changeItemsSold(int posIdx, int posCount) {
        if (posIdx == 0) {
            sold0.setText("# Sold: " + posCount);
        } else if (posIdx == 1) {
            sold1.setText("# Sold: " + posCount);
        } else if (posIdx == 2) {
            sold2.setText("# Sold: " + posCount);
        } else if (posIdx == 3) {
            sold3.setText("# Sold: " + posCount);
        } else if (posIdx == 4) {
            sold4.setText("# Sold: " + posCount);
        } else if (posIdx == 5) {
            sold5.setText("# Sold: " + posCount);
        } else if (posIdx == 6) {
            sold6.setText("# Sold: " + posCount);
        } else if (posIdx == 7) {
            sold7.setText("# Sold: " + posCount);
        } 
    }
    
    //function to updates all sold count labels at once
    public void updateAllSoldLabels(int[] itemsSold) {
        if (itemsSold.length >= 1) {
            sold0.setText("# Sold: " + itemsSold[0]);
        }
        if (itemsSold.length >= 2) { 
            sold1.setText("# Sold: " + itemsSold[1]);
        }
        if (itemsSold.length >= 3) { 
            sold2.setText("# Sold: " + itemsSold[2]);
        }
        if (itemsSold.length >= 4) {
            sold3.setText("# Sold: " + itemsSold[3]);
        }
        if (itemsSold.length >= 5) {
            sold4.setText("# Sold: " + itemsSold[4]);
        }
        if (itemsSold.length >= 6) {
            sold5.setText("# Sold: " + itemsSold[5]);
        }
        if (itemsSold.length >= 7) {
            sold6.setText("# Sold: " + itemsSold[6]);
        }
        if (itemsSold.length >= 8) {
            sold7.setText("# Sold: " + itemsSold[7]);
        }
    }

    // call the refillSlot function on each item
    @FXML
    public void reFill0(ActionEvent mainEvent) {
        refillSlot(0);
    }
    @FXML
    public void reFill1(ActionEvent mainEvent) {
        refillSlot(1);
    }
    @FXML
    public void reFill2(ActionEvent mainEvent) {
        refillSlot(2);
    }
    @FXML
    public void reFill3(ActionEvent mainEvent) {
        refillSlot(3);
    }
    @FXML
    public void reFill4(ActionEvent mainEvent) {
        refillSlot(4);
    }
    @FXML
    public void reFill5(ActionEvent mainEvent) {
        refillSlot(5);
    }
    @FXML
    public void reFill6(ActionEvent mainEvent) {
        refillSlot(6);
    }
    @FXML
    public void reFill7(ActionEvent mainEvent) {
        refillSlot(7);
    }

    
    // function to refill each slot. makes sure the grilled cheese is <= 32 and drinks are <= 16
    private void refillSlot(int posIdx) {
        Controller.Position pos = positions.get(posIdx);
        int maxItems;
        if(pos.item.equals("Grilled Cheese")) {
            maxItems = 32;
        } else {
            maxItems = 16;
        }

        // as long as the currentQuantity is less than maxItems
        if (pos.getCurrentQuantity() < maxItems) {
            int currMainQuantity = pos.getCurrentQuantity() + 1;
            pos.setCurrentQuantity(currMainQuantity); // add one to the currMainQuantity
            quantityLabels[posIdx].setText("Qty: " + pos.getCurrentQuantity()); // change quantity
            
            // update total and check the stock
            mainController.updateTotal();
            mainController.checkStock();
        }
    }


    @FXML
    public void reassignPosition(ActionEvent event) {

    Map<String, Integer> salesPerDrink = new HashMap<>(); // stores the name of drink and all sales of drink
    Map<String, List<Integer>> positionsOfDrinks = new HashMap<>(); // store the name of drink and the position it is located in
    int n = positions.size(); // size of the positions array
    String mostPopularDrink = null;
    String leastPopularDrink = null;
    int leastSales = 1000; // used to see which drink has the least amount of sales generated
    int maxSales = -1; // used to see which drink has the most amount of sales generated

    // go through each position and get each item
    for(int i = 0; i < n; i++) {
        Controller.Position pos = positions.get(i);
        if(!pos.item.equals("Grilled Cheese")) { // as long as the item is not grilled cheese
            String mainTxt = soldLabels[i].getText(); // get the label at the index
            int mainSales = Integer.parseInt(mainTxt.replace("# Sold: ", "")); // replace '# Sold' and get just the number

            if(salesPerDrink.containsKey(pos.item)) { // if the map contains current key
                int currentSales = salesPerDrink.get(pos.item); // get the current total sales
                int mainTotal = currentSales + mainSales; // calculate new total sales
                salesPerDrink.put(pos.item, mainTotal); // update map with new total
            } else {
                salesPerDrink.put(pos.item, mainSales);
            }
        }

        if(!positionsOfDrinks.containsKey(pos.item)) {
            positionsOfDrinks.put(pos.item, new ArrayList<>());
        }
        positionsOfDrinks.get(pos.item).add(i);
    }

        for(String drink: salesPerDrink.keySet()) { // iterate through each drink
            int sales = salesPerDrink.get(drink); // get the total sales count

            if(sales > maxSales) { // if the current sales are greater than the highest sales, reassign
                mostPopularDrink = drink;
                maxSales = sales;
            }

            if(sales < leastSales) { // if the current sales are lower than the lowest sales, reassign
                leastPopularDrink = drink;
                leastSales = sales;
            }
        }

        // check if the drink can be reassigned. if not send message
        if(mostPopularDrink == null || leastPopularDrink == null || leastPopularDrink.equals(mostPopularDrink) || positionsOfDrinks.get(leastPopularDrink) == null || positionsOfDrinks.get(leastPopularDrink).isEmpty()) {
            System.out.println("Cannot reassign position.");
            return;
        }

        int newReassignPos = positionsOfDrinks.get(leastPopularDrink).get(0); // get the index of the first slot with least popular drink

        // replace the item
        Controller.Position pos = positions.get(newReassignPos);
        pos.item = mostPopularDrink;
        pos.setCurrentQuantity(16);
        quantityLabels[newReassignPos].setText("Qty: 16"); // update quantityLabel
        soldLabels[newReassignPos].setText("# Sold: 0"); // update soldLabels

        // make sure main controller is updated.
        if(mainController != null) {
            mainController.changeSlotPic(newReassignPos, mostPopularDrink); // call function to change picture of slot
            mainController.updateTotal();
            mainController.checkStock();
        }

    }

    // function ensures that revenue for both controller and admin are same 
    public void syncTotalRevenue(double total) {
        this.totalRevenue = total;
        mainTotalSalesText.setText(String.format("$%.2f", totalRevenue));
    }

    // go through each position and set the qty to current quantity
    private void updateAllQtyLabels() {
        for (int i = 0; i < positions.size() && i < quantityLabels.length; i++) {
            Controller.Position pos = positions.get(i); // gets the position and displays that positions current quantity
            int currMainQty = pos.getCurrentQuantity();
            quantityLabels[i].setText("Qty: " + currMainQty);
        }
    }
}