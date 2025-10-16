

class FinancialPositionStatement {
    private final double totalAssets, ncAssets, vehicles, land, equipment,
    intangibles, cAssets, inventories, tradeReceivables, cash,
    totalLiabilities, totalLiabilitiesAndEquity, cLiabilities, tradePayables, stLoans,
    bankLoans, totalEquity, shareCap, RER;
    private final ProfitLossStatement pls;
    private final String[] fieldsFPS = {
        "ASSETS",
        "Non-current(fixed) assets:",
        "Vehicles",
        "Land",
        "Equipment",
        "intangible assets",
        "Current(variable) assets:",
        "Inventories",
        "Trade receivables",
        "Cash",
        "TOTAL ASSETS",
        "EQUITY & LIABILITY",
        "Current liabilities:",
        "Trade payables",
        "Short-term loans",
        "Non-Current liabilities:",
        "Bank(long-term) loans",
        "TOTAL LIABILITIES",
        "Shareholders' equity:",
        "Share capital",
        "Retained earnings reserve",
        "Equity",
        "TOTAL EQUITY & LIABILITIES"
    };

    public FinancialPositionStatement(ProfitLossStatement pls, double vehicles, double land, double equipment,
    double intangibles,double inventories, double tradeReceivables, double cash, double tradePayables, double stLoans,
    double bankLoans, double shareCap){
        this.pls = pls;
        this.vehicles =vehicles;
        this.land = land;
        this.equipment = equipment;
        this.intangibles = intangibles;
        this.ncAssets = vehicles + land + equipment + intangibles;
        this.tradeReceivables = tradeReceivables;
        this.inventories = inventories;
        this.cash = cash;
        this.cAssets = tradeReceivables + inventories + cash;
        this.totalAssets = this.ncAssets + this.cAssets;
        this.stLoans = stLoans;
        this.tradePayables = tradePayables;
        this.cLiabilities = stLoans + tradePayables;
        this.bankLoans = bankLoans;
        this.totalLiabilities = this.cLiabilities + bankLoans;
        this.shareCap = shareCap;
        this.RER = pls.getRProf();
        this.totalEquity = this.RER + shareCap;
        this.totalLiabilitiesAndEquity = totalEquity + totalLiabilities;
    }

    public FinancialRecord[] fpsRec() {
        return new FinancialRecord[] {
            new FinancialRecord(fieldsFPS[0], Double.NaN),
            new FinancialRecord(fieldsFPS[1], Double.NaN),
            new FinancialRecord(fieldsFPS[2], vehicles),
            new FinancialRecord(fieldsFPS[3], land),
            new FinancialRecord(fieldsFPS[4], equipment),
            new FinancialRecord(fieldsFPS[5], intangibles),
            new FinancialRecord(fieldsFPS[6], Double.NaN),
            new FinancialRecord(fieldsFPS[7], inventories),
            new FinancialRecord(fieldsFPS[8], tradeReceivables),
            new FinancialRecord(fieldsFPS[9], cash),
            new FinancialRecord(fieldsFPS[10], totalAssets),
            new FinancialRecord(fieldsFPS[11], Double.NaN),
            new FinancialRecord(fieldsFPS[12], Double.NaN),
            new FinancialRecord(fieldsFPS[13], tradePayables),
            new FinancialRecord(fieldsFPS[14], stLoans),
            new FinancialRecord(fieldsFPS[15], Double.NaN),
            new FinancialRecord(fieldsFPS[16], bankLoans),
            new FinancialRecord(fieldsFPS[17], totalLiabilities),
            new FinancialRecord(fieldsFPS[18], Double.NaN),
            new FinancialRecord(fieldsFPS[19], shareCap),
            new FinancialRecord(fieldsFPS[20], RER),
            new FinancialRecord(fieldsFPS[21], totalEquity),
            new FinancialRecord(fieldsFPS[22], totalLiabilitiesAndEquity)
        };
    }


    public double getVehicles() {return vehicles;}
    public double getLand() {return land;}
    public double getEquip() {return equipment;}
    public double getIntangibles() {return intangibles;}
    public double getNCAssets() {return ncAssets;}
    public double getPayables() {return tradePayables;}
    public double getRecievables() {return tradeReceivables;}
    public double getInventories() {return inventories;}
    public double getCash() {return cash;}
    public double getCAssets() {return cAssets;}
    public double getTotalAssets() {return totalAssets;}
    public double getSTLoans() {return stLoans;}
    public double getBankLoans() {return bankLoans;}
    public double getCLiabs() {return cLiabilities;}
    public double getTotalLiabs() {return totalLiabilities;}
    public double getShareCap() {return shareCap;}
    public double getRER() {return RER;}
    public double getTotalEquity() {return totalEquity;}
    public double getTEquityAndLiabs() {return totalLiabilitiesAndEquity;}
    public boolean balances() {return totalAssets == totalLiabilitiesAndEquity;}
    public ProfitLossStatement getPls() {return pls;}

}