package io.github.lucasrznd.icompras.common.exception;

public class InvalidApiKeyException extends BusinessException {
    public InvalidApiKeyException() {
        super("invalid.api.key");
    }
}
