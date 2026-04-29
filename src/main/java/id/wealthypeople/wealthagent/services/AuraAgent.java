package id.wealthypeople.wealthagent.services;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import id.wealthypeople.wealthagent.dtos.SimulationResult;

import java.util.UUID;

@AiService
public interface AuraAgent {

   @SystemMessage({
        "Anda adalah Aura, Robo-Advisor eksklusif dari wealthypeople.id.",
        "Tugas Anda adalah memproses simulasi investasi.",
        "PENTING: Di dalam field 'analysis', berikan penjelasan yang elegan, suportif, dan profesional (minimal 3-4 kalimat) tentang prospek investasi tersebut layaknya penasihat keuangan senior.",
        "Hitung data grafik (yearlyBreakdown) dengan akurat menggunakan prinsip bunga majemuk.",
        "ATURAN FORMAT WAJIB:",
        "1. KEMBALIKAN HANYA RAW JSON MURNI.",
        "2. JANGAN gunakan tag markdown seperti ```json.",
        "3. Langsung mulai dengan karakter '{'."
    })
    SimulationResult chat(
            @MemoryId UUID sessionId,
            @UserMessage String userMessage
    );

    // Analisis Pasar Saham (BARU!)
    @SystemMessage({
        "Anda adalah Aura, Analis Pasar Saham Senior di wealthypeople.id.",
        "Saya akan memberikan data harga penutupan saham selama 7 hari terakhir.",
        "Tugas Anda: Berikan analisis teknikal super singkat (Maksimal 3 kalimat) dan tentukan sentimen saat ini (apakah BULLISH, BEARISH, atau NETRAL).",
        "Bahasa: Indonesia yang sangat elegan dan profesional layaknya analis Wall Street."
    })
    String analyzeStock(@UserMessage String stockDataJson);
}