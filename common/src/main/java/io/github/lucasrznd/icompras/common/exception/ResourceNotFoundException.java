package io.github.lucasrznd.icompras.common.exception;

public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException(Class<?> resourceType, Object id) {
        super("resource.not.found", resourceType.getSimpleName(), id);
    }
}
