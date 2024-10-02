package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

/**
 *
 * ITSC 1213
 * University of North Carolina at Charlotte
 */

public class FastFoodKitchen {

    // Private Instance variables
    private ArrayList<BurgerOrder> orderList = new ArrayList();
    private ArrayList<BurgerOrder> purchasedOrderList = new ArrayList(); // added this for last project
    private ArrayList<BurgerOrder> completedOrderList = new ArrayList(); // added this for last project

    private static int nextOrderNum = 1;

    /**
     * Constructor with imported .csv file. Attributes from .csv file are imported
     * and initialize as objects
     * and stored into ArrayList 'orderList'. The getNextOrdernum() &
     * incrementNextOrderNum() methods follows afterward
     */
    FastFoodKitchen() {

        // Create Path object call 'pathToFile' with with "burgerOrder-1.csv' file
        Path pathToFile = Paths.get(
                "C:\\ITSC_1213_Intro-to_Comp_2\\NetBeansProject\\VangJaiProject3\\FastFoodKitchenProject3\\burgerOrders-1.csv");

        try {
            // Create BufferedReader object call 'br' with File and with 'pathToFile' as an
            // arugment.
            BufferedReader bufferReader = Files.newBufferedReader(pathToFile);

            // Read the first line since we want to skip the header of the .csv fild
            bufferReader.readLine();

            // Initialize second line to variable 'row'
            String row = bufferReader.readLine();

            // Iterate through each row in the .csv fille and if the rows are not 'null'
            while (row != null) {

                // Split each row by a "," and inititalize it to a Array String call
                // 'attributes'
                String[] attributes = row.split(",");
                // Create a BurgerOrder object and call on the method 'getOneBurger" with
                // 'attributes' as it's parameters
                BurgerOrder burgerObject = getOneBurger(attributes);
                // Add the new burger object into ArrayList 'orderlist'.
                orderList.add(burgerObject);
                // Also added the new burger object into ArrayList 'purchasedOrderList'
                purchasedOrderList.add(burgerObject); // added this
                // Get the next order number
                getNextOrderNum();
                // Incrememnt the next order number by one
                incrementNextOrderNum();
                // initialize the next line in the .csv file to 'row' and repeat the progess
                // again
                row = bufferReader.readLine();
            }
            // Close br
            bufferReader.close();
        } catch (IOException e) {
            System.out.println("Caught IOException when closing output stream. Try again.");
        }
    }

    public static int getNextOrderNum() {
        return nextOrderNum;
    }

    private void incrementNextOrderNum() {
        nextOrderNum++;
    }

    public int addOrder(int ham, int cheese, int veggie, int soda, boolean toGo) {
        int orderNum = getNextOrderNum();
        orderList.add(new BurgerOrder(ham, cheese, veggie, soda, toGo, orderNum));
        purchasedOrderList.add(new BurgerOrder(ham, cheese, veggie, soda, toGo, orderNum)); // added this for last
                                                                                            // project
        incrementNextOrderNum();
        orderCallOut(orderList.get(orderList.size() - 1));
        return orderNum;

    }

    public boolean isOrderDone(int orderID) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderNum() == orderID) {
                return false;
            }
        }
        return true;
    }

    public boolean cancelOrder(int orderID) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderNum() == orderID) {
                orderList.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getNumOrdersPending() {
        return orderList.size();
    }

    public boolean cancelLastOrder() {

        if (!orderList.isEmpty()) { // same as if (orderList.size() > 0)
            orderList.remove(orderList.size() - 1);
            return true;
        }

        return false;
    }

    private void orderCallOut(BurgerOrder order) {
        if (order.getNumCheeseburgers() > 0) {
            System.out.println("You have " + order.getNumHamburger() + " hamburgers");
        }
        if (order.getNumCheeseburgers() > 0) {
            System.out.println("You have " + order.getNumCheeseburgers() + " cheeseburgers");
        }
        if (order.getNumVeggieburgers() > 0) {
            System.out.println("You have " + order.getNumVeggieburgers() + " veggieburgers");
        }
        if (order.getNumSodas() > 0) {
            System.out.println("You have " + order.getNumSodas() + " sodas");
        }

    }

    // Modified Method
    public void completeSpecificOrder(int orderID) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderNum() == orderID) {
                System.out.println("Order number " + orderID + " is done!");
                if (orderList.get(i).isOrderToGo()) {
                    orderCallOut(orderList.get(i));
                }
                // Add order at index 'i' of ArrayList 'orderList' to ArrayList
                // 'completedOrderList'
                completedOrderList.add(orderList.get(i)); // added code for project 3
                orderList.remove(i);
            }
        }
    }

    public void completeNextOrder() {
        int nextOrder = orderList.get(0).getOrderNum();
        completeSpecificOrder(nextOrder);

    }

    // Part 2
    public ArrayList<BurgerOrder> getOrderList() {
        return orderList;
    }

    public int findOrderSeq(int whatWeAreLookingFor) {
        for (int j = 0; j < orderList.size(); j++) {
            if (orderList.get(j).getOrderNum() == whatWeAreLookingFor) {
                return j;
            }
        }
        return -1;
    }

    // public int findOrderBin(int whatWeAreLookingFor) {
    // int left = 0;
    // int right = orderList.size() - 1;
    // while (left <= right) {
    // int middle = (left + right) / 2;
    // if (whatWeAreLookingFor < orderList.get(middle).getOrderNum()) {
    // right = middle - 1;
    // } else if (whatWeAreLookingFor > orderList.get(middle).getOrderNum()) {
    // left = middle + 1;
    // } else {
    // return middle;
    // }
    // }
    // return -1;
    // }

    public int findOrderBin(int orderID) {
        int left = 0;
        int right = orderList.size() - 1;
        while (left <= right) {
            int middle = ((left + right) / 2);
            if (orderID < orderList.get(middle).getOrderNum()) {
                right = middle - 1;
            } else if (orderID > orderList.get(middle).getOrderNum()) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;

    }

    public void selectionSort() {
        for (int i = 0; i < orderList.size() - 1; i++) {
            int minIndex = i;
            for (int k = i + 1; k < orderList.size(); k++) {
                if (orderList.get(minIndex).getTotalBurgers() > orderList.get(k).getTotalBurgers()) {
                    minIndex = k;
                }
            }
            BurgerOrder temp = orderList.get(i);
            orderList.set(i, orderList.get(minIndex));
            orderList.set(minIndex, temp);
        }
    }

    public void insertionSort() {
        for (int j = 1; j < orderList.size(); j++) {
            BurgerOrder temp = orderList.get(j);
            int possibleIndex = j;
            while (possibleIndex > 0 && temp.getTotalBurgers() < orderList.get(possibleIndex - 1).getTotalBurgers()) {
                orderList.set(possibleIndex, orderList.get(possibleIndex - 1));
                possibleIndex--;
            }
            orderList.set(possibleIndex, temp);
        }
    }

    // public void selectionSort() { //weird method!
    //
    // for (int j = 0; j < orderList.size() - 1; j++) {
    // int minIndex = j;
    // for (int k = j + 1; k < orderList.size(); k++) {
    //
    // if (orderList.get(minIndex).getTotalBurgers() >
    // orderList.get(j).getTotalBurgers()){
    // minIndex = k;
    // }
    // }
    // BurgerOrder temp = orderList.get(j);
    // orderList.set(j, orderList.get(minIndex));
    // orderList.set(minIndex, temp);
    //
    // }
    // }

    // /**
    // * Display burger order detail
    // */
    // public void displayBurgePurchasedDetail() {
    // System.out.println("Name: " + firstName + " " + lastName);
    // System.out.println("ID: " + id);
    // }

    /**
     * This method create one burger object from the .csv file
     * 
     * @param attributes
     * @return burgerObject
     */
    public static BurgerOrder getOneBurger(String[] attributes) {

        // Initialize variables to element according to their indexs
        int orderId = Integer.parseInt(attributes[0]);
        int numHam = Integer.parseInt(attributes[1]);
        int numChess = Integer.parseInt(attributes[2]);
        int numVeg = Integer.parseInt(attributes[3]);
        int numSod = Integer.parseInt(attributes[4]);
        boolean toGo = Boolean.parseBoolean(attributes[5]);

        // Create burgerObject with variables
        BurgerOrder burgerObject = new BurgerOrder(numHam, numChess, numVeg, numSod, toGo, orderId);

        return burgerObject;
    }

    /**
     * This method generate a 'EndOfDayReport.txt"
     */
    public void endOfDayReportGenerator() {
        try {
            // Create fileOutputStream object with String arugment of absolut path and file
            // name: "C:\\ITSC1213\\NetBeansProject\\FastFoodKitchen_Project3\\".
            // Note: The FileOutputStream is what will create a file and direct the output
            FileOutputStream fileOutputStream = new FileOutputStream(
                    "C:\\ITSC1213\\NetBeansProject\\FastFoodKitchenProject3\\EndOfDayReport.txt");

            // Create PrintWrtier object that takes argument 'fileOutputStream'
            // Note: PrintWriter will allow us to print Strings into our newly created file.
            PrintWriter outFS = new PrintWriter(fileOutputStream);

            // ******************************************************************
            // Print title of report
            outFS.println("End Of The Day Report");
            outFS.println();

            // ******************************************************************
            // Print out onto 'EndOfDayReport.txt' the header 'Comeplete Orders's
            outFS.println("Completed Orders:");

            // Print out completed Orders List. These orders are in the ArrayList
            // 'completedOrderList'
            // Iterate through ArrayList 'completedOrderList' and set each element to the
            // temporary varaible 'ordersCompletedList'
            for (BurgerOrder ordersCompletedList : completedOrderList) {

                // Print out onto 'EndOfDayReport.txt'.
                outFS.println("BurgerOrder #" + ordersCompletedList.getOrderNum() + ": numHamburgers: "
                        + ordersCompletedList.getNumHamburger()
                        + ", numCheeseburgers: " + ordersCompletedList.getNumCheeseburgers() + ", numVeggieburgers: "
                        + ordersCompletedList.getNumVeggieburgers()
                        + ", numSodas: " + ordersCompletedList.getNumSodas() + ", orderToGo: "
                        + ordersCompletedList.isOrderToGo());
            }
            outFS.println();
            // ******************************************************************

            // ******************************************************************
            // Print out onto 'EndOfDayReport.txt' the header 'Incomeplete Orders'
            outFS.println("Incomplete Orders:");

            // Print out orders that are still in ArrayList. These are the incomplete orders
            // Iterate through ArrayList 'orderList' and set each element to the temporary
            // varaible 'incompletedOrdersList'
            for (BurgerOrder incompletedOrdersList : orderList) {

                // Print out onto 'EndOfDayReport.txt'.
                outFS.println("BurgerOrder #" + incompletedOrdersList.getOrderNum() + ": numHamburgers: "
                        + incompletedOrdersList.getNumHamburger()
                        + ", numCheeseburgers: " + incompletedOrdersList.getNumCheeseburgers() + ", numVeggieburgers: "
                        + incompletedOrdersList.getNumVeggieburgers() + ", numSodas: "
                        + incompletedOrdersList.getNumSodas()
                        + ", orderToGo: " + incompletedOrdersList.isOrderToGo());
            }
            outFS.println();
            // ******************************************************************

            // ******************************************************************
            // Print out onto 'EndOfDayReport.txt' the header 'Orders Placed'.
            outFS.println("Orders Placed:");

            // Print out orders placed. These orders are in the ArrayList
            // 'purchasedOrderList'
            // Iterate through ArrayList 'purchasedOrderList' and set each element to the
            // temporary varaible 'purchasedList'
            for (BurgerOrder purchasedList : purchasedOrderList) {

                // if element in the arrayList ordernumber is equal to or greater than 1
                if (purchasedList.getOrderNum() >= 1) {

                    // Print out onto 'EndOfDayReport.txt'.
                    outFS.println("BurgerOrder #" + purchasedList.getOrderNum() + ": numHamburgers: "
                            + purchasedList.getNumHamburger()
                            + ", numCheeseburgers: " + purchasedList.getNumCheeseburgers() + ", numVeggieburgers: "
                            + purchasedList.getNumVeggieburgers()
                            + ", numSodas: " + purchasedList.getNumSodas() + ", orderToGo: "
                            + purchasedList.isOrderToGo());
                }
            }
            outFS.println();
            // ******************************************************************

            // ******************************************************************
            // Print out onto 'EndOfDayReport.txt' the header 'Total Items Sold'
            outFS.println("Total Items Sold:");

            // Initalize variables to zero
            int totalHamburgersSold = 0;
            int totalChesseBurgersSold = 0;
            int totalVeggieburgersSold = 0;
            int totalSodasSold = 0;

            // Iterate through ArraryList 'purchasedOrderList' and set each element to the
            // temporary varaible called 'purchasedList'
            for (BurgerOrder purchaseList : purchasedOrderList) {

                // While Iteration, add each element's items to varaibles
                totalHamburgersSold += purchaseList.getNumHamburger();
                totalChesseBurgersSold += purchaseList.getNumCheeseburgers();
                totalVeggieburgersSold += purchaseList.getNumVeggieburgers();
                totalSodasSold += purchaseList.getNumSodas();
            }

            // Print out onto 'EndOfDayReport.txt' the result after iteration
            outFS.println("Hamburgers sold:" + totalHamburgersSold);
            outFS.println("Chesseburgers sold:" + totalChesseBurgersSold);
            outFS.println("Veggieburgers sold:" + totalVeggieburgersSold);
            outFS.println("Sodas sold:" + totalSodasSold);
            // ******************************************************************

            // Close outFS
            outFS.close();

            // Close fileOutputStream
            fileOutputStream.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Caught FileNotFoundException for studentAndProfessorData.txt. Try again making sure "
                    + "the file name and path are correct ");
        } catch (IOException ex) {
            System.out.println("Caught IOException when closing output stream. Try again.");
        }
    }

    /**
     * This method generate a 'burgerOrder2.csv" file
     */
    public void incompleteCSVFileGenerator() {

        try {
            // Create String with 'fileName' variable, intializing it to 'burgerOrder2.csv"
            // 'burgerOrder2.csv' is the file we will write to
            String fileName = "burgerOrder2.csv";

            // Create Filewriter object with 'writer' as the varaible. 'fileName' is passed
            // into FileWriter parameters.
            FileWriter writer = new FileWriter(fileName);

            // Write header in 'burgerOrder2.csv' file
            writer.write("orderId" + ",");
            writer.write("numHamburgers" + ",");
            writer.write("numCheeseburgers" + ",");
            writer.write("numVeggieburgers" + ",");
            writer.write("numSodas" + ",");
            writer.write("toGo" + ",");

            // Skip line
            writer.write("\n");

            // Iterate through ArrayList 'orderList' and set each element to the temporary
            // varaible called 'list'
            // Elements in this arrayList are the incomplete orders
            for (BurgerOrder list : orderList) {

                // write onto 'burgerOrder2.csv' file
                // The "," will skip to the next block in the cvs.file
                writer.write(list.getOrderNum() + ",");
                writer.write(list.getNumHamburger() + ",");
                writer.write(list.getNumCheeseburgers() + ",");
                writer.write(list.getNumVeggieburgers() + ",");
                writer.write(list.getNumSodas() + ",");

                // Used the String.vauleof() method because 'boolean' needs to be converted into
                // a String in order to be exported to csv.file
                writer.write(String.valueOf(list.isOrderToGo())); // don't nedd "," because it's the last write method.

                // Skip to bottom line after iteration
                writer.write("\n");
            }
            // close wrtier
            writer.close();

        } catch (IOException e) {
            System.out.println("Caught IOException when closing output stream. Try again.");
        }
    }

} // end of class
