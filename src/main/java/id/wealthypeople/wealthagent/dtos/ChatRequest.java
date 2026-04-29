package id.wealthypeople.wealthagent.dtos;

import lombok.Data;
import java.util.UUID;

@Data
public class ChatRequest {
    private UUID sessionId; 
    private String message;
}