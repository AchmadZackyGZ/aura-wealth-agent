package id.wealthypeople.wealthagent.dtos;

import java.util.List;

public record SimulationResult(
    String analysis,
    double initialInvestment,
    double monthlyContribution,
    int years,
    double expectedReturnRate,
    List<YearlyData> yearlyBreakdown
) {
    public record YearlyData(int year, double balance) {}
}