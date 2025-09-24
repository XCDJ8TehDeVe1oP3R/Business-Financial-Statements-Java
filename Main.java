import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        String businessName, trimmed, spaceRemoved, file;
        try(Scanner s = new Scanner(System.in)){
            System.out.println("Enter Business Name");
            businessName = s.next();
            trimmed = businessName.trim();
            spaceRemoved = trimmed.replaceAll("\\s", "");
            file = spaceRemoved.toLowerCase() + "_PLS.txt";    
        } 

        initPLS(plsRec);
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
    public static ProfitLossStatement pls;
    public static PLSRecord[] plsRec;

    public static void initPLS(PLSRecord[] rec) {
        try(Scanner sc = new Scanner(System.in)){
            System.out.println("Output the following:");
            System.out.println(fields[0].toLowerCase());
            double r = sc.nextDouble();
            System.out.println(fields[1].toLowerCase());
            double cos = sc.nextDouble();
            System.out.println(fields[3].toLowerCase());
            double eo = sc.nextDouble();
            System.out.println(fields[5].toLowerCase());
            double in = sc.nextDouble();
            System.out.println(fields[7].toLowerCase());
            double tax = sc.nextDouble();
            System.out.println("and " + fields[9].toLowerCase());
            double div = sc.nextDouble();
            pls = new ProfitLossStatement(r, cos, eo, in, tax, div);
        }   

        rec = new PLSRecord[] {
            new PLSRecord(fields[0], pls.getRev()),
            new PLSRecord(fields[1], pls.getCOS()),
            new PLSRecord(fields[2], pls.getGProf()),
            new PLSRecord(fields[3], pls.getExsAndOs()),
            new PLSRecord(fields[4], pls.getOProf()),
            new PLSRecord(fields[5], pls.getIntrst()),
            new PLSRecord(fields[6], pls.getProfBefTax()),
            new PLSRecord(fields[7], pls.getTax()),
            new PLSRecord(fields[8], pls.getAProf()),
            new PLSRecord(fields[9], pls.getDivs()),
            new PLSRecord(fields[0], pls.getRProf())
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
            System.err.println("An error has occured");
            e.printStackTrace();
        }
    }

    public static void writeFile(String businessName, String fileName, PLSRecord[] rec){
        try (BufferedWriter br = new BufferedWriter(new FileWriter(fileName))){
            br.write("PLS RECORD FOR " + businessName.toUpperCase());
            br.newLine();
            for(int x = 0; x < rec.length; x++) {
                br.write(rec[x].field() + ":  " + rec[x].value());
                br.newLine();
            }
            br.close();
        } catch(IOException e) {
            System.err.println();
            e.printStackTrace();
        }
    }

}