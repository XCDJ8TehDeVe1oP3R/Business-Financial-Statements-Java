class ProfitLossStatement {
    private final double revenue, costOfSales, grossProfit, 
    expensesAndOverheads, operationProfit, interest, 
    profitBeforeTax, tax, annualProfit, dividends, retainedProfit;

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

    public double getRev() {
        return revenue;
    }

    public double getCOS() {
        return costOfSales;
    }
    public double getExsAndOs() {
        return expensesAndOverheads;
    }
    public double getIntrst() {
        return interest;
    }
    public double getTax() {
        return tax;
    }
    public double getDivs() {
        return dividends;
    }
    public double getGProf() {
        return grossProfit;
    }
    public double getOProf() {
        return operationProfit;
    }
    public double getProfBefTax() {
        return profitBeforeTax;
    }
    public double getAProf() {
        return annualProfit;
    }

    public double getRProf() {
        return retainedProfit;
    }
}