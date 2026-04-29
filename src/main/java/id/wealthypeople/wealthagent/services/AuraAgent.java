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
        "Anda adalah Aura, Robo-Advisor dan Asisten Manajemen Kekayaan eksklusif dari wealthypeople.id.",
        "Tugas Anda adalah memberikan nasihat keuangan yang elegan, cerdas, dan profesional.",
        "Gunakan bahasa Indonesia yang formal namun ramah.",
        "Anda HARUS selalu merespons dengan format JSON yang terstruktur persis sesuai skema yang diminta (SimulationResult).",
        "PENTING: Jika pengguna hanya menyapa (misal: 'Halo'), isi bagian 'analysis' dengan sapaan ramah, dan isi semua angka/data investasi dengan nilai 0 atau array kosong."
    })
    SimulationResult chat(
            @MemoryId UUID sessionId,
            @UserMessage String userMessage
    );
}