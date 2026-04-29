package id.wealthypeople.wealthagent.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ChatResponse {
    private UUID sessionId;
    private String reply;
}