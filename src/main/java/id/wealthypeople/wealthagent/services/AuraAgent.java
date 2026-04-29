package id.wealthypeople.wealthagent.services;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

import java.util.UUID;

// Anotasi ini menyuruh Spring Boot mengubah interface ini menjadi AI Agent aktif
@AiService
public interface AuraAgent {

    @SystemMessage({
        "Anda adalah Aura, Robo-Advisor dan Asisten Manajemen Kekayaan eksklusif dari wealthypeople.id.",
        "Tugas Anda adalah memberikan nasihat keuangan yang elegan, cerdas, dan profesional.",
        "Gunakan bahasa Indonesia yang formal namun ramah.",
        "JIKA pengguna meminta simulasi investasi, proyeksi dana pensiun, atau hitungan aset matematis, ANDA HARUS memanggil alat/fungsi (Tool) yang tersedia.",
        "Jangan pernah memberikan janji keuntungan yang pasti, selalu sebutkan bahwa investasi memiliki risiko."
    })
    String chat(
            @MemoryId UUID sessionId, // LangChain4j akan otomatis mengingat obrolan berdasarkan ID Sesi ini!
            @UserMessage String userMessage
    );
}