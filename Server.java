package serverPackage;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(1234);
            System.out.println("Server waiting for client connection...");

            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("Client connected!");

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String operation = in.readLine();
                System.out.println("Received operation: " + operation);

                try {
                    String result = calculateOperation(operation);
                    out.println("Result: " + result);
                    System.out.println("Sent result: " + result);
                } catch (IllegalArgumentException e) {
                    out.println("Error: " + e.getMessage());
                    System.out.println("Calculation error: " + e.getMessage());
                } catch (ArithmeticException e) {
                    out.println("Error: " + e.getMessage());
                    System.out.println("Arithmetic error: " + e.getMessage());
                }

                clientSocket.close();
                System.out.println("Client disconnected. Waiting for new connection...\n");
            }

        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String calculateOperation(String operation) {
        try {
            operation = operation.trim().replaceAll("\\s+", " ");
            String[] parts = operation.split(" ");

            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid format. Use: number operator number (e.g., 5 + 3)");
            }

            double num1 = Double.parseDouble(parts[0]);
            double num2 = Double.parseDouble(parts[2]);
            String operator = parts[1];

            double result;
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        throw new ArithmeticException("Division by zero is not allowed");
                    }
                    result = num1 / num2;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator. Use +, -, *, or /");
            }

            return String.format("%.2f", result);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid numbers in operation");
        }
    }
}
