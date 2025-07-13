PROJECT INFORMATION

This project is a GUI for a food truck. This application features a main
food truck interface for customers to order food and an admin panel for 
managers to handle inventory or see their total revenue. 


Project Sturcture


app/AdminController.java - Controller for the admin panel
app/AdminView.fxml - UI for admin panel
app/Controller.java - Controller for customer interface
app/MainView.fxml - UI for customer panel
app/App.java - Main application that runs the whole program.
app/styles.css - Provides the formatting for both the AdminView.fxml and MainView.fxml files


CUSTOMER INTERFACE

Customers can purchase a variety of drinks and the main food (grilled cheese).
Allows the customer to see images of what the food and drinks look like and also allows them to see the price of every item.
Dispenes from the slot with the highest current quantity of the item.
If the item is out of stock, the customer is not able to purchase the item.
Allows the customer to see real time quantities of the food or drink.
Shows the customers total. 
Features a reset button ro allow the customer to cancel their current selection without completing the purchase.


ADMIN INTERFACE

Allows the manager to refill drinks or food.
Allows the manager reassign the least popular drink slot with the most popular one to increase sales.
Shows the total revenue generated. 
Shows the manager the total number of items sold per slot. 


REQUIREMENTS TO Run

JDK 17 or newer 
JavaFX SDK 24

HOW TO RUN

Clone the repository.
Ensure JavaFX is working in your environment.
Go into the JavaFXProject folder.
Run this command to compile the code: javac --module-path lib --add-modules javafx.controls,javafx.fxml -d bin src/app/*.java
Run this command to run the program: java --module-path lib --add-modules javafx.controls,javafx.fxml -cp bin app.App

ADDITIONAL INFORMATION

The bin folder contains the complied code (.class) files and the src files contains all the human readable source code
(.java files). In order to run this program the program files must be the same in both the bin and src files
or else there will be an error.