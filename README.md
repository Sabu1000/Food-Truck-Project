# Food Truck Project 🚚🍔

A JavaFX-based GUI application for a food truck business featuring both customer and admin interfaces.

## 📋 Project Description

This project is a comprehensive food truck management system with:
- **Customer Interface**: Interactive GUI for customers to order food and drinks
- **Admin Panel**: Management interface for inventory control and sales tracking

## 🎯 Features

### Customer Interface
- Purchase variety of drinks (Water, Coke, Sprite, Root Beer) and food (Grilled Cheese)
- View images and prices of all items
- Real-time quantity tracking
- Automatic stock management (dispenses from highest quantity slot)
- Button disabling when items are out of stock
- Running total display
- Reset button to cancel current selection

### Admin Interface
- Refill drinks and food inventory
- Reassign unpopular item slots with popular ones
- View total revenue generated
- Track items sold per slot
- Real-time synchronization with customer interface

## 📁 Project Structure

```
src/app/
├── App.java              # Main application entry point
├── Controller.java       # Customer interface controller
├── AdminController.java  # Admin panel controller
├── MainView.fxml         # Customer UI layout
├── AdminView.fxml        # Admin UI layout
└── styles.css           # CSS styling for both interfaces

Pictures/                 # Food and drink images
├── grilled_cheese.png
├── water.png
├── coke.png
├── sprite.png
└── root_beer.png

bin/                     # Compiled Java classes
```

## 🔧 Requirements

- **Java Development Kit (JDK)**: Version 17 or newer
- **JavaFX SDK**: Version 24 or newer
- **Operating System**: Windows, macOS, or Linux

## 🚀 Installation & Setup

### 1. Install Java JDK
Download and install JDK 17+ from:
- [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
- [OpenJDK](https://adoptium.net/)

### 2. Install JavaFX SDK
Download JavaFX SDK from [OpenJFX](https://openjfx.io/) and extract it to:
- Windows: `C:\javafx-sdk-24.0.1\`
- macOS/Linux: `/usr/local/javafx-sdk-24.0.1/`

### 3. Clone the Repository
```bash
git clone https://github.com/Sabu1000/Food-Truck-Project.git
cd Food-Truck-Project
```

## 🎮 How to Run

### Method 1: Command Line (Windows)

1. **Compile the project:**
```powershell
javac --module-path "C:\javafx-sdk-24.0.1\lib" --add-modules javafx.controls,javafx.fxml -d bin src/app/*.java
```

2. **Run the application:**
```powershell
java --module-path "C:\javafx-sdk-24.0.1\lib" --add-modules javafx.controls,javafx.fxml -cp bin app.App
```

### Method 2: Command Line (macOS/Linux)

1. **Compile the project:**
```bash
javac --module-path "/usr/local/javafx-sdk-24.0.1/lib" --add-modules javafx.controls,javafx.fxml -d bin src/app/*.java
```

2. **Run the application:**
```bash
java --module-path "/usr/local/javafx-sdk-24.0.1/lib" --add-modules javafx.controls,javafx.fxml -cp bin app.App
```

### Method 3: Using an IDE

1. **IntelliJ IDEA** (Recommended):
   - Import the project
   - Configure JavaFX SDK in Project Settings
   - Run `App.java`

2. **Eclipse**:
   - Import as existing project
   - Add JavaFX libraries to build path
   - Run `App.java`

3. **VS Code**:
   - Install Java Extension Pack
   - Configure JavaFX in launch configuration
   - Run `App.java`

## 🎯 Usage Instructions

### Customer Interface
1. **Browse Items**: View available food and drinks with images and prices
2. **Purchase Items**: Click buy buttons to add items to your order
3. **View Total**: Monitor your running total in the bottom section
4. **Reset Order**: Use "Clear All" to cancel current selection
5. **Exit**: Click "Quit" to close the application

### Admin Interface
1. **Restock Items**: Use refill buttons to add inventory
2. **Monitor Sales**: View items sold per slot
3. **Track Revenue**: See total revenue generated
4. **Reassign Slots**: Move popular items to less popular slots

## 🐛 Troubleshooting

### Common Issues

**"Module not found" error:**
- Ensure JavaFX path is correct in the command
- Verify JavaFX is properly extracted

**"Class not found" error:**
- Make sure you compiled the code first
- Check that `bin` directory contains `.class` files

**Images not loading:**
- Verify `Pictures` folder is in the project root
- Ensure image files have correct names and extensions

**JavaFX warnings:**
- These are normal in newer Java versions and don't affect functionality

## 🛠️ Development

### Adding New Features
1. Modify the appropriate controller (`Controller.java` or `AdminController.java`)
2. Update FXML files if UI changes are needed
3. Add new images to `Pictures` folder if required
4. Recompile and test

### Code Structure
- **MVC Pattern**: Model-View-Controller architecture
- **Position Class**: Represents each item slot with quantity and price
- **Synchronized Controllers**: Admin and customer interfaces stay in sync

## 📝 License

This project is for educational purposes.

## 👥 Contributors

- [Sabu1000](https://github.com/Sabu1000)

## 📞 Support

If you encounter any issues:
1. Check the troubleshooting section
2. Verify all requirements are met
3. Ensure correct JavaFX path configuration

---
**Happy Food Trucking!** 🚚✨
