import java.io.*;
import java.time.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {
            String businessName = readBusinessName(s);
            String folderName = (businessName.toUpperCase().replaceAll("\\s", "")) + "_FinancialStatements";
            String baseName = businessName.replaceAll("\\s", "").toLowerCase();
            String fileName1 = folderName + File.separator + baseName + "_PLS.txt";
            String fileName2 = folderName + File.separator + baseName + "_FPS.txt";
            LocalDate date = promptDate(s, "Enter the publication date");
            createFolder(folderName);
            ProfitLossStatement pls = readProfitLoss(s);
            FinancialPositionStatement fps = readFinancialPosition(s, pls);

            FinancialRecord[] rec1 = pls.plsRec();
            FinancialRecord[] rec2 = fps.fpsRec();

            createFiles(fileName1, fileName2);
            writeFiles(fileName1, fileName2, businessName, rec1, rec2, date);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    private static String readBusinessName(Scanner s) {
        System.out.print("Business Name: ");
        String name = s.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Business name cannot be empty. Please enter again: ");
            name = s.nextLine().trim();
        }
        return name;
    }

    public static LocalDate promptDate(Scanner scanner, String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = null;

        while (date == null) {
            System.out.print(message + " (dd/MM/yyyy): ");
            String input = scanner.nextLine();

            try {
                date = LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("❌ Invalid format! Please use dd/MM/yyyy (e.g. 05/07/2025)");
            }
        }

        return date;
    }

    private static ProfitLossStatement readProfitLoss(Scanner s) {
        System.out.println("\nInput the following for your Profit/Loss Statement:");
        double revenue = promptDouble(s, "Revenue: ");
        double cos = promptDouble(s, "Cost Of Sales: ");
        double expenses = promptDouble(s, "Expenses & Overheads: ");
        double interest = promptDouble(s, "Interest: ");
        double tax = promptDouble(s, "Tax: ");
        double dividends = promptDouble(s, "Dividends: ");
        return new ProfitLossStatement(revenue, cos, expenses, interest, tax, dividends);
    }

    private static FinancialPositionStatement readFinancialPosition(Scanner s, ProfitLossStatement pls) {
        System.out.println("\nInput the following for your Statement of Financial Position:");
        double vehicles = promptDouble(s, "Vehicles: ");
        double land = promptDouble(s, "Land: ");
        double equipment = promptDouble(s, "Equipment: ");
        double intangibles = promptDouble(s, "Intangible Assets: ");
        double inventories = promptDouble(s, "Inventories: ");
        double receivables = promptDouble(s, "Trade Receivables: ");
        double cash = promptDouble(s, "Cash: ");
        double tradePayables = promptDouble(s, "Trade Payables: ");
        double shortTermLoans = promptDouble(s, "Short-term Loans: ");
        double bankLoans = promptDouble(s, "Long-term (Bank) Loans: ");
        double shareCap = promptDouble(s, "Shareholders' Capital: ");
        return new FinancialPositionStatement(
                pls, vehicles, land, equipment, intangibles,
                inventories, receivables, cash, tradePayables,
                shortTermLoans, bankLoans, shareCap
        );
    }

    private static double promptDouble(Scanner s, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (s.hasNextDouble()) {
                double val = s.nextDouble();
                s.nextLine();
                return val;
            } else {
                String bad = s.nextLine();
                System.out.println("Invalid number: '" + bad + "'. Please enter a valid numeric value.");
            }
        }
    }

    private static void createFolder(String folderName) {
        File folder = new File(folderName);
        if (folder.exists()) {
            if (!folder.isDirectory()) {
                throw new IllegalStateException("A non-directory exists with the same name: " + folderName);
            }
            System.out.println("Folder already exists: " + folderName);
        } else {
            if (folder.mkdirs()) {
                System.out.println("Created folder: " + folderName);
            } else {
                System.err.println("Failed to create folder: " + folderName);
            }
        }
    }

    private static void createFiles(String file1, String file2) {
        try {
            File f1 = new File(file1);
            File f2 = new File(file2);
            boolean created1 = f1.createNewFile();
            boolean created2 = f2.createNewFile();
            if (created1) System.out.println("Created: " + file1);
            if (created2) System.out.println("Created: " + file2);
            if (!created1 && !created2) System.out.println("Both files already exist.");
        } catch (IOException e) {
            System.err.println("File creation error: " + e.getMessage());
        }
    }

    private static void writeFiles(String file1, String file2, String businessName, FinancialRecord[] rec1, FinancialRecord[] rec2, LocalDate date) {
        try (BufferedWriter br1 = new BufferedWriter(new FileWriter(file1)); BufferedWriter br2 = new BufferedWriter(new FileWriter(file2))) {

            br1.write("PLS RECORD OF " + businessName.toUpperCase());
            br1.newLine();
            br1.write("Published: " + date.toString());
            br1.newLine();
            for (FinancialRecord r : rec1) {
                if (Double.isNaN(r.value())) {
                    br1.write(r.field());
                } else {
                    br1.write(r.field() + ": " + r.value());
                }
                br1.newLine();
            }

            br2.write("FPS RECORD OF " + businessName.toUpperCase());
            br2.newLine();
            br2.write("Published: " + date.toString());
            br2.newLine();
            for (FinancialRecord r : rec2) {
                if (Double.isNaN(r.value())) {
                    br2.write(r.field());
                } else {
                    br2.write(r.field() + ": " + r.value());
                }
                br2.newLine();
            }
        } catch (IOException e) {
            System.err.println("Write error: " + e.getMessage());
        }
    }
}