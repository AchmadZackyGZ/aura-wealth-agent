package id.wealthypeople.wealthagent.services;

import id.wealthypeople.wealthagent.dtos.StockChartData;
import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarketService {

    @Value("${twelvedata.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    //  DTO Internal dengan Lombok untuk memetakan JSON Twelve Data dengan aman
    @Data
    public static class TwelveDataResponse {
        private List<TwelveDataValue> values;
    }

    @Data
    public static class TwelveDataValue {
        private String datetime;
        private String close;
    }

    public List<StockChartData> getStockData(String symbol) {
        // Tembak API Twelve Data (Ambil data harian, 7 hari ke belakang)
        String url = "https://api.twelvedata.com/time_series?symbol=" + symbol + "&interval=1day&outputsize=7&apikey=" + apiKey;
        
        TwelveDataResponse response = restTemplate.getForObject(url, TwelveDataResponse.class);

        List<StockChartData> chartData = new ArrayList<>();
        
        if (response != null && response.getValues() != null) {
            List<TwelveDataValue> values = response.getValues();
            // Data dari Twelve Data urutannya dari terbaru ke terlama. 
            // Kita balik (reverse) agar di grafik Next.js arahnya dari kiri (lama) ke kanan (baru).
            for (int i = values.size() - 1; i >= 0; i--) {
                TwelveDataValue day = values.get(i);
                chartData.add(new StockChartData(day.getDatetime(), Double.parseDouble(day.getClose())));
            }
        }
        return chartData;
    }
}