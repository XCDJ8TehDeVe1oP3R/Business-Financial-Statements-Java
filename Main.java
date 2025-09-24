import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String trimmed, spaceRemoved, file;
        
        System.out.println("Enter Business Name");
        businessName = s.nextLine();
        trimmed = businessName.trim();
        spaceRemoved = trimmed.replaceAll("\\s", "");
        file = "BusinessFile/"+spaceRemoved.toLowerCase() + "_PLS.txt";
        plsRec = initPLS();
        initFile(file);
        writeFile(businessName, file, plsRec);
    }
    
    public static String[] fields ={
        "Revenue",
        "Cost Of Sales",
        "Gross Profit",
        "Overheads and Expenses",
        "Operations Profit",
        "Interest",
        "Profit Before Tax",
        "Tax",
        "Profit After Tax(Annual Profit)",
        "Dividends",
        "Retained Profit"
    };

    public static Scanner s = new Scanner(System.in);
    public static String businessName;
    public static FinancialRecord[] plsRec = new FinancialRecord[11];

    public static FinancialRecord[] initPLS() {
        System.out.println("Output the following:");
        System.out.println(fields[0].toLowerCase());
        double r = s.nextDouble();
        System.out.println(fields[1].toLowerCase());
        double cos = s.nextDouble();
        System.out.println(fields[3].toLowerCase());
        double eo = s.nextDouble();
        System.out.println(fields[5].toLowerCase());
        double in = s.nextDouble();
        System.out.println(fields[7].toLowerCase());
        double tax = s.nextDouble();
        System.out.println("and " + fields[9].toLowerCase());
        double div = s.nextDouble();
        ProfitLossStatement pls = new ProfitLossStatement(r, cos, eo, in, tax, div);

        return new FinancialRecord[] {
            new FinancialRecord(fields[0], pls.getRev()),
            new FinancialRecord(fields[1], pls.getCOS()),
            new FinancialRecord(fields[2], pls.getGProf()),
            new FinancialRecord(fields[3], pls.getExsAndOs()),
            new FinancialRecord(fields[4], pls.getOProf()),
            new FinancialRecord(fields[5], pls.getIntrst()),
            new FinancialRecord(fields[6], pls.getProfBefTax()),
            new FinancialRecord(fields[7], pls.getTax()),
            new FinancialRecord(fields[8], pls.getAProf()),
            new FinancialRecord(fields[9], pls.getDivs()),
            new FinancialRecord(fields[10], pls.getRProf())
        };
    }

    public static void initFile(String fileName) {
        try {
           File file = new File(fileName);
            if(file.createNewFile()) {
                System.out.println("File with filename " + fileName + " has been created");
            } else {
                System.out.println("File with filename "  + fileName + " already exists");
            } 
        } catch (IOException e) {
            System.err.println("An error has occurred: " + e);
            
        }
    }

    public static void writeFile(String businessName, String fileName, FinancialRecord[] rec){
        try (BufferedWriter br = new BufferedWriter(new FileWriter(fileName))){
            br.write("PLS RECORD FOR " + businessName.toUpperCase());
            br.newLine();
            for (FinancialRecord rec1 : rec) {
                br.write(rec1.field() + ":  " + rec1.value());
                br.newLine();
            }
            br.close();
        } catch(IOException e) {
            System.err.println("An error has occurred: " + e);
        }
    }

}