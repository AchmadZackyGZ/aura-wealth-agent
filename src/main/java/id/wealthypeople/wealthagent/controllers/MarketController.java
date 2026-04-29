package id.wealthypeople.wealthagent.controllers;

import id.wealthypeople.wealthagent.dtos.ApiResponse;
import id.wealthypeople.wealthagent.dtos.MarketAnalysisResponse;
import id.wealthypeople.wealthagent.dtos.StockChartData;
import id.wealthypeople.wealthagent.services.AuraAgent;
import id.wealthypeople.wealthagent.services.MarketService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/market")
@RequiredArgsConstructor
public class MarketController {

    private final MarketService marketService;
    private final AuraAgent auraAgent;

    @GetMapping("/{symbol}")
    public ResponseEntity<ApiResponse<MarketAnalysisResponse>> getMarketTrend(@PathVariable String symbol) {
        
        // 1. Sedot data riwayat harga saham asli (Real-time)
        List<StockChartData> chart = marketService.getStockData(symbol);

        // 2. Siapkan data mentah ini untuk dibaca oleh AI
        String dataForAi = "Simbol Saham: " + symbol + "\nData Harga Penutupan 7 Hari Terakhir: " + chart.toString();

        // 3. Suruh Aura menganalisis dan meramal trennya
        String aiSentiment = auraAgent.analyzeStock(dataForAi);

        // 4. Bungkus grafik dan opini AI menjadi satu paket rapi
        MarketAnalysisResponse responseData = new MarketAnalysisResponse(symbol, chart, aiSentiment);

        ApiResponse<MarketAnalysisResponse> response = ApiResponse.<MarketAnalysisResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil mengambil data pasar dan sentimen Aura")
                .data(responseData)
                .build();

        return ResponseEntity.ok(response);
    }
}