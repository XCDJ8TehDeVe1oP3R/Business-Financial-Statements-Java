class ProfitLossStatement {
    private final double revenue, costOfSales, grossProfit, 
    expensesAndOverheads, operationProfit, interest, 
    profitBeforeTax, tax, annualProfit, dividends, retainedProfit;
    private final String[] fieldsPLS = {
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

    public ProfitLossStatement(double revenue, double costOfSales, 
    double expensesAndOverheads, double interest, double tax, double dividends) {
        this.revenue = revenue;
        this.costOfSales = costOfSales;
        this.expensesAndOverheads = expensesAndOverheads;
        this.interest = interest;
        this.tax = tax;
        this.dividends = dividends;

        this.grossProfit = revenue - costOfSales;
        this.operationProfit = this.grossProfit - expensesAndOverheads;
        this.profitBeforeTax = this.operationProfit - interest;
        this.annualProfit = this.profitBeforeTax - tax;
        this.retainedProfit = this.annualProfit - dividends;
    }

    public FinancialRecord[] plsRec() {
        return new FinancialRecord[] {
            new FinancialRecord(fieldsPLS[0], revenue),
            new FinancialRecord(fieldsPLS[1], costOfSales),
            new FinancialRecord(fieldsPLS[2], grossProfit),
            new FinancialRecord(fieldsPLS[3], expensesAndOverheads),
            new FinancialRecord(fieldsPLS[4], operationProfit),
            new FinancialRecord(fieldsPLS[5], interest),
            new FinancialRecord(fieldsPLS[6], profitBeforeTax),
            new FinancialRecord(fieldsPLS[7], tax),
            new FinancialRecord(fieldsPLS[8], annualProfit),
            new FinancialRecord(fieldsPLS[9], dividends),
            new FinancialRecord(fieldsPLS[10], retainedProfit)
        };
    }

    public double getRev() { return revenue;}

    public double getCOS() {return costOfSales;}

    public double getExsAndOs() {return expensesAndOverheads;}

    public double getIntrst() {return interest;}

    public double getTax() {return tax;}

    public double getDivs() {return dividends;}

    public double getGProf() {return grossProfit;}

    public double getOProf() {return operationProfit;}

    public double getProfBefTax() {return profitBeforeTax;}

    public double getAProf() {return annualProfit;}
        

    public double getRProf() {return retainedProfit;}
}