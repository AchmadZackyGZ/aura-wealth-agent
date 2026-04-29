package id.wealthypeople.wealthagent.controllers;

import id.wealthypeople.wealthagent.dtos.ApiResponse;
import id.wealthypeople.wealthagent.dtos.ChatRequest;
import id.wealthypeople.wealthagent.dtos.SimulationResult;
import id.wealthypeople.wealthagent.services.AuraAgent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final AuraAgent auraAgent;

    public ChatController(AuraAgent auraAgent) {
        this.auraAgent = auraAgent;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> chat(@RequestBody ChatRequest request) {
        
        UUID currentSessionId = request.getSessionId();
        if (currentSessionId == null) {
            currentSessionId = UUID.randomUUID(); 
        }

        // 1. Tembak ke AI. LangChain4j akan memaksa AI mengembalikan Object SimulationResult!
        SimulationResult aiResult = auraAgent.chat(currentSessionId, request.getMessage());

        // 2. Kita bungkus sessionId dan hasil JSON AI ke dalam satu Map
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("sessionId", currentSessionId);
        responseData.put("reply", aiResult); // "reply" sekarang berisi Object JSON, BUKAN teks panjang!

        // 3. Kembalikan ke Frontend
        ApiResponse<Map<String, Object>> response = ApiResponse.<Map<String, Object>>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil mendapatkan respons grafik dari Aura")
                .data(responseData)
                .build();

        return ResponseEntity.ok(response);
    }
}