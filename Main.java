import java.io.*;
import java.util.Scanner;

public class Main {
    public static Scanner s = new Scanner(System.in);
    public static ProfitLossStatement profitLossStatement;
    public static FinancialPositionStatement financialPositionStatement;
    public static void main(String[] args) {
        
    }

    public static void initFolder(String folderName) {
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdirs();                 // create folder (and parents) if missing
            System.out.println("Created folder: " + folderName);
        } else {
            System.out.println("Folder already exists: " + folderName);
        }
    }

    public static void init(ProfitLossStatement pls, FinancialPositionStatement fps) {
    }

    public static void writeFiles() {

    }
}