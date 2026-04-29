package id.wealthypeople.wealthagent.controllers;

import id.wealthypeople.wealthagent.dtos.ApiResponse;
import id.wealthypeople.wealthagent.dtos.ChatRequest;
import id.wealthypeople.wealthagent.dtos.ChatResponse;
import id.wealthypeople.wealthagent.services.AuraAgent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final AuraAgent auraAgent;

    public ChatController(AuraAgent auraAgent) {
        this.auraAgent = auraAgent;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ChatResponse>> chat(@RequestBody ChatRequest request) {
        
        // 1. Cek apakah ini chat baru atau lanjutan dari sesi sebelumnya
        UUID currentSessionId = request.getSessionId();
        if (currentSessionId == null) {
            currentSessionId = UUID.randomUUID(); // Buat ID sesi baru
        }

        // 2. Tembak pesan ke AI Agent (Aura)
        String aiReply = auraAgent.chat(currentSessionId, request.getMessage());

        // 3. Siapkan objek balasan (Session ID & Teks/JSON dari AI)
        ChatResponse chatResponse = new ChatResponse(currentSessionId, aiReply);

        // 4. Bungkus menggunakan ApiResponse agar formatnya seragam dengan GlobalExceptionHandler
        ApiResponse<ChatResponse> response = ApiResponse.<ChatResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil mendapatkan respons dari Aura")
                .data(chatResponse)
                .build();

        return ResponseEntity.ok(response);
    }
}