package kapur.model;

/**
 * WebSocketMessage record is not redundant to TickerPrice.java
 * to be able to receive specifically any websocket data
 * may morph into more specific immutable api data receptor later
 */
public record WebSocketMessage(
        MessageType type,
        Object data,
        String tags
)
{}
