package clientPackage;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Client trying to connect...");
            socket = new Socket("localhost", 1234);
            System.out.println("Client connected successfully!");

            // Create readers/writers for text communication
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Get operation from user with validation
            String operation;
            while (true) {
                System.out.print("Enter operation (format: number operator number) or 'quit' to exit: ");
                operation = scanner.nextLine().trim();

                if (operation.equalsIgnoreCase("quit")) {
                    break;
                }

                // Validate input before sending
                if (isValidOperation(operation)) {
                    // Send operation to server
                    out.println(operation);

                    // Read and display result from server
                    String response = in.readLine();
                    System.out.println("Server response: " + response);
                } else {
                    System.out.println("Invalid! (Use Spaces example: (5 + 5))");
                    System.out.println("Valid operators: +, -, *, /");
                }

                System.out.println(); // Empty line for better readability
            }

        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Validation method as required in the activity
    private static boolean isValidOperation(String operation) {
        // Remove extra spaces
        operation = operation.trim().replaceAll("\\s+", " ");
        String[] parts = operation.split(" ");

        // Check if we have exactly 3 parts
        if (parts.length != 3) {
            return false;
        }

        // Check if first and third parts are numbers
        try {
            Double.parseDouble(parts[0]);
            Double.parseDouble(parts[2]);
        } catch (NumberFormatException e) {
            return false;
        }

        // Check if operator is valid
        String operator = parts[1];
        return operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/");
    }
}
