# Fast-Food-Kitchen

This project implements a simple Burger Ordering System that reads order data from a CSV file, processes the orders, and generates summary reports at the end of the day. The system uses Java's object-oriented principles and file handling capabilities to manage and manipulate burger orders effectively.

## Features

1. **CSV File Parsing**: Reads burger orders from a CSV file to initialize the order list.
2. **Order Management**: Handles different types of orders including hamburgers, cheeseburgers, veggieburgers, and sodas.
3. **Report Generation**: Generates an `EndOfDayReport.txt` file that summarizes completed and incomplete orders along with total items sold.
4. **CSV File Export**: Creates an `incompleteOrders.csv` file listing incomplete orders at the end of the day.

## Method Descriptions

### `getOneBurger(String[] attributes)`

Creates a single `BurgerOrder` object from a CSV row.

- **Parameters**: `String[] attributes` - An array of attributes where each element represents an order attribute (e.g., `orderId`, `numHam`, `numCheese`, etc.).
- **Returns**: A `BurgerOrder` object initialized with the attributes.

### `endOfDayReportGenerator()`

Generates an `EndOfDayReport.txt` file with the following sections:

- **Completed Orders**: Lists all completed orders.
- **Incomplete Orders**: Lists all incomplete orders.
- **Orders Placed**: Lists all orders that were placed.
- **Total Items Sold**: Shows the total number of hamburgers, cheeseburgers, veggieburgers, and sodas sold.

  #### Example Output:

  ```
  End Of The Day Report

  Completed Orders: BurgerOrder #1: numHamburgers: 2, numCheeseburgers: 3, numVeggieburgers: 0, numSodas: 1, orderToGo: false

  Incomplete Orders: BurgerOrder #4: numHamburgers: 1, numCheeseburgers: 0, numVeggieburgers: 2, numSodas: 2, orderToGo: true

  Total Items Sold: Hamburgers sold: 10 Cheeseburgers sold: 5 Veggieburgers sold: 4 Sodas sold: 8
  ```

### `incompleteCSVFileGenerator()`

Generates a `burgerOrder2.csv` file containing all incomplete orders at the end of the day.

- **CSV Headers**: `orderId`, `numHamburgers`, `numCheeseburgers`, `numVeggieburgers`, `numSodas`, `toGo`
- **Details**: Each row represents an incomplete order with the order ID and the quantity of each item type.

  #### Example CSV Output:

  ```
  orderId,numHamburgers,numCheeseburgers,numVeggieburgers,numSodas,toGo 4,1,0,2,2,true
  ```

## Setup Instructions

1. Clone the repository or download the source files.
2. Ensure you have Java installed (JDK 8 or higher recommended).
3. Compile the source files using your IDE or run:
   ```
   javac BurgerOrderingSystem.java
   ```
4. Run the program:
   ```
   java BurgerOrderingSystem
   ```

## File Structure

- **`BurgerOrderingSystem.java`**: The main file containing the order processing logic.
- **`EndOfDayReport.txt`**: Generated at the end of the program execution, summarizing the day's order details.
- **`burgerOrder2.csv`**: Generated at the end of the program execution, listing all incomplete orders.

## Error Handling

The program includes basic error handling for file operations such as:

- **FileNotFoundException**: Catches and reports issues if the specified file is not found.
- **IOException**: Catches and reports any I/O issues when closing streams or performing file operations.

## Future Enhancements

- Implement a graphical user interface using JavaFX or Swing for better user interaction.
- Extend the system to support more food items and drink types.
- Add more detailed order statistics to the report.

## Contributors

- **Jai Vang** - Developer

## License

This project is licensed under the MIT License.
