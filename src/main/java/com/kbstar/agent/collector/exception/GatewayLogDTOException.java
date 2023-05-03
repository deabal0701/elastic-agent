package com.kbstar.agent.collector.exception;

public class GatewayLogDTOException extends RuntimeException {

    public GatewayLogDTOException(String message) {
        super(message);
    }

    public GatewayLogDTOException(String message, Throwable cause) {
        super(message, cause);
    }
}