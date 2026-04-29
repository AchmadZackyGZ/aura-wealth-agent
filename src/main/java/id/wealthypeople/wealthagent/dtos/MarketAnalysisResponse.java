package id.wealthypeople.wealthagent.dtos;

import java.util.List;

public record MarketAnalysisResponse(
    String symbol,
    List<StockChartData> chart,
    String aiSentiment
) {
    
}