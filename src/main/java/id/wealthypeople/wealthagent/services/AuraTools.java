package id.wealthypeople.wealthagent.services;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class AuraTools {

    @Tool("Menghitung simulasi investasi bulanan dan mengembalikan data JSON untuk dirender menjadi grafik Recharts di Frontend")
    public String generateInvestmentChart(
            double initialInvestment,
            double monthlyContribution,
            int years,
            double expectedAnnualReturnRate,
            String riskProfile
    ) {
        // 🔥 MAGIC HAPPENS HERE 🔥
        // Saat user meminta simulasi, AI akan memanggil method ini secara otomatis.
        // Kita mencetak output dalam format JSON murni agar mudah ditangkap oleh fetch() di Next.js
        
        return String.format(
            "{ \"uiType\": \"investmentChart\", \"data\": { \"initialInvestment\": %.0f, \"monthlyContribution\": %.0f, \"years\": %d, \"expectedReturnRate\": %.2f, \"riskProfile\": \"%s\" } }",
            initialInvestment, monthlyContribution, years, expectedAnnualReturnRate, riskProfile
        );
    }
}