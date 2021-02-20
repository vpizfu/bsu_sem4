package com.company;

import com.company.DAO.DAO;
import com.company.DAO.Tour;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Main driver. Used to test DAO behaviour.
 */
public class Main {

    public static void main(String[] args) {
        // write your code here

        BasicConfigurator.configure();

        Logger logger = Logger.getLogger("MainLogger");

        logger.info("App started");

        DAO controller = null;
        try {
            controller = new DAO();
        } catch (SQLException throwables) {
            logger.info("Failed to establish db connection. \n" + throwables.getMessage());
            return;
        }

        var scanner = new Scanner(System.in);

        while (true) {

            scanner.nextLine();

            System.out.println("Choose option\n " +
                    "1) All clients info.\n" +
                    "2) All tours info.\n" +
                    "3) Tours for specified client info.\n" +
                    "4) Hot tours of specified type.\n" +
                    "5) Book a tour for a client.\n" +
                    "6) Update discount.\n" +
                    "7) Exit.");

            int option = scanner.nextInt();

            switch (option) {
                case 1: {
                    var clients = controller.getAllClients();

                    System.out.println("\nAll clients: ");

                    for (var client : clients
                    ) {
                        System.out.println("id: " + client.getId() + "\n "
                                + "name: " + client.getName() + "\n" +
                                "discount: " + client.getDiscount());
                    }

                    scanner.nextLine();
                    break;
                }
                case 2: {
                    var allTours = controller.getAllTours();

                    System.out.println("\nAll tours: ");

                    for (var tour : allTours
                    ) {
                        System.out.println("\nid: " + tour.getId() + "\n "
                                + "location: " + tour.getLocation() + "\n" +
                                "price: " + tour.getPrice() + "\n"
                                + "start: " + tour.getStartDate() + "\n"
                                + "end: " + tour.getEndDate() + "\n"
                                + "is hot: " + tour.isHot() + "\n"
                                + "type: " + tour.getType());
                    }
                    scanner.nextLine();
                    break;
                }
                case 3: {
                    System.out.println("Insert client id: ");

                    int clientId = new Scanner(System.in).nextInt();

                    var allToursForClient = controller.getAllTours(clientId);

                    System.out.println("\nAll tours for client " + clientId + " :");

                    for (var tour : allToursForClient
                    ) {
                        System.out.println("\nid: " + tour.getId() + "\n "
                                + "location: " + tour.getLocation() + "\n" +
                                "price: " + tour.getPrice() + "\n"
                                + "start: " + tour.getStartDate() + "\n"
                                + "end: " + tour.getEndDate() + "\n"
                                + "is hot: " + tour.isHot() + "\n"
                                + "type: " + tour.getType());
                    }
                    scanner.nextLine();
                    break;
                }
                case 4: {

                    System.out.println(" 1:Sport \n 2:All inclusive\n");

                    int typeNumber = scanner.nextInt();

                    if (typeNumber != 1 && typeNumber != 2) {
                        System.out.println("Invalid option");
                        new Scanner(System.in).nextLine();
                        break;
                    }

                    Tour.type type = Tour.type.allInclusive;

                    if (typeNumber == 1) {
                        type = Tour.type.sport;
                    } else {
                        type = Tour.type.allInclusive;
                    }

                    var allHotToursOfType = controller.getHotTourInfo(type);

                    System.out.println("\nAll hot all inclusive tours : ");

                    for (var tour : allHotToursOfType
                    ) {
                        System.out.println("\nid: " + tour.getId() + "\n "
                                + "location: " + tour.getLocation() + "\n" +
                                "price: " + tour.getPrice() + "\n"
                                + "start: " + tour.getStartDate() + "\n"
                                + "end: " + tour.getEndDate());
                    }

                    scanner.nextLine();
                    break;
                }
                case 5: {

                    System.out.println("Insert tour id: ");

                    int tourId = scanner.nextInt();

                    System.out.println("Insert client id: ");

                    int clientId = scanner.nextInt();

                    boolean result = controller.bookTour(tourId, clientId);

                    System.out.println(result ? "Booked successfully" : "Failed to book");

                    scanner.nextLine();

                    break;
                }
                case 6: {
                    System.out.println("Insert discount value: ");

                    int discountValue = scanner.nextInt();

                    System.out.println("Insert orders count: ");

                    int ordersCount = scanner.nextInt();

                    boolean result = controller.setDiscount(ordersCount, discountValue);

                    System.out.println(result ? "Updated successfully" : "Failed to update");

                    scanner.nextLine();

                    break;
                }
                case 7: {
                    return;
                }
            }
        }

    }
}
