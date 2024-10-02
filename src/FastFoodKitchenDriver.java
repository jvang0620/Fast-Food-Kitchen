package src;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FastFoodKitchenDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // FastFoodKitchen Instance
        FastFoodKitchen kitchen = new FastFoodKitchen();

        Scanner sc = new Scanner(System.in);

        try {

            while (kitchen.getNumOrdersPending() != 0) {

                // see what the user wants to do
                System.out.println("Please select from the following menu of options, by typing a number:");
                System.out.println("\t 1. Order food");
                System.out.println("\t 2. Cancel last order");
                System.out.println("\t 3. Show number of orders currently pending");
                System.out.println("\t 4. Complete order");
                System.out.println("\t 5. Check on order");
                System.out.println("\t 6. Cancel order");
                System.out.println("\t 7. Exit (End Of Day and Incomplete Orders Reports will be generate)");

                int num = sc.nextInt();

                switch (num) {
                    case 1: // Order Food

                        try {

                            System.out.println("How many hamburgers do you want?");
                            int ham = sc.nextInt();
                            System.out.println("How many cheeseburgers do you want?");
                            int cheese = sc.nextInt();
                            System.out.println("How many veggieburgers do you want?");
                            int veggie = sc.nextInt();
                            System.out.println("How many sodas do you want?");
                            int sodas = sc.nextInt();
                            System.out.println("Is your order to go? (Y/N)");
                            char letter = sc.next().charAt(0);
                            boolean TOGO = false;
                            if (letter == 'Y' || letter == 'y') {
                                TOGO = true;
                            }

                            int orderNum = kitchen.addOrder(ham, cheese, veggie, sodas, TOGO);
                            System.out.println("Thank you. Your order number is " + orderNum);
                            System.out.println();
                            break;

                        } catch (InputMismatchException e) {
                            System.out.println();
                            System.out.println("Caught InputMismatchException. Please enter vaild entry");
                        } catch (Exception e) {
                            System.out.println();
                            System.out.println(
                                    "Caught Exception. There may be a possible error that occur during user input.");
                        }

                    case 2: // Cancel last order
                        boolean ready = kitchen.cancelLastOrder();
                        if (ready) {
                            System.out.println("Thank you. The last order has been canceled");
                        } else {
                            System.out.println("Sorry. There are no orders to cancel.");
                        }
                        System.out.println();
                        break;

                    case 3: // Show number of orders currently pending
                        System.out.println("There are " + kitchen.getNumOrdersPending() + " pending orders");
                        break;

                    case 4: // Complete order

                        try {
                            System.out.println("Enter order number to complete?");
                            int order = sc.nextInt();
                            kitchen.completeSpecificOrder(order);
                            System.out.println("Your order is ready. Thank you!");

                        } catch (InputMismatchException e) {
                            System.out.println();
                            System.out.println("Caught InputMismatchException. Please enter vaild entry");
                        } catch (Exception e) {
                            System.out.println();
                            System.out.println(
                                    "Caught Exception. There may be a possible error that occur during user input.");
                        }
                        break;

                    case 5: // Check on order

                        try {
                            System.out.println("What is your order number?");
                            int order2 = sc.nextInt();
                            ready = kitchen.isOrderDone(order2);
                            if (ready) {
                                System.out.println("Sorry, no order with this number was found.");
                            } else {
                                System.out.println("No, it's not ready, but it should be up soon. Sorry for the wait.");
                            }
                            System.out.println();

                        } catch (InputMismatchException e) {
                            System.out.println();
                            System.out.println("Caught InputMismatchException. Please enter vaild entry");
                        } catch (Exception e) {
                            System.out.println();
                            System.out.println(
                                    "Caught Exception. There may be a possible error that occur during user input.");
                        }
                        break;

                    case 6: // Cancel order

                        try {

                            System.out.println("What is your order number?");
                            int order3 = sc.nextInt();
                            boolean cancel = kitchen.cancelOrder(order3);
                            if (cancel) {
                                System.out.println("Your order has been successfully cancelled ");
                            } else {
                                System.out.println("Sorry, we can’t find your order number in the system");
                            }
                            System.out.println();

                        } catch (InputMismatchException e) {
                            System.out.println();
                            System.out.println("Caught InputMismatchException. Please enter vaild entry");
                        } catch (Exception e) {
                            System.out.println();
                            System.out.println(
                                    "Caught Exception. There may be a possible error that occur during user input.");
                        }
                        break;

                    case 7: // Exit program and generate end of day report, and csv file of incompleted
                            // orders

                        // Generate an end of day report
                        kitchen.endOfDayReportGenerator();

                        // Generate an Incomplete Order .csv file
                        kitchen.incompleteCSVFileGenerator();

                        System.out.println();
                        System.out.println("End of day report and Incomplete orders report have been generated.");

                        System.exit(0);
                        break;
                    default: // If user enter invaild number
                        System.out.println("Sorry, but you need to enter a 1, 2, 3, 4, 5, 6, or a 7");

                } // end switch

            } // end while loop

        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("Caught InputMismatchException. Please enter vaild entry");
        } catch (Exception e) {
            System.out.println();
            System.out.println("Caught Exception. There may be a possible error that occur during user input.");
        }

    } // end main

}// end class
