package application;

import com.google.protobuf.MessageLite;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProtobufProvider implements MessageBodyReader<Object>, MessageBodyWriter<Object> {
    private final Map<Class<?>, Method> parseMethods = new ConcurrentHashMap<>();


    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return MessageLite.class.isAssignableFrom((Class<?>) type) && isSupportedMediaType(mediaType);
    }

    @Override
    public Object readFrom(Class<Object> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
        final Method parseMethod = getParseMethod((Class<?>) type);
        try {
            return parseMethod.invoke(null, inputStream);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e); // normally shouldn't happen
        }    }

    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return MessageLite.class.isAssignableFrom((Class<?>) type) && isSupportedMediaType(mediaType);
    }

    @Override
    public long getSize(Object o, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        final MessageLite message = (MessageLite) o;
        return message.getSerializedSize();
    }

    @Override
    public void writeTo(Object o, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        writeTo((MessageLite) o, outputStream);
    }

    protected boolean isSupportedMediaType(MediaType mediaType) {
        return mediaType == null || ProtobufMediaType.MEDIA_TYPE.isCompatible(mediaType);
    }

    protected Method getParseMethod(Class<?> clazz) {
        // short circuit
        final Method m = parseMethods.get(clazz);
        if (m != null) {
            return m;
        }

        // find method and put it to the map
        try {
            final Method method = clazz.getMethod(getParseFromInputStreamMethodName(), InputStream.class);

            // save to cache
            parseMethods.put(clazz, method);

            // return
            return method;
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("No method parseFrom for class=" + clazz);
        }
    }

    protected void writeTo(@Nullable MessageLite message, @Nonnull OutputStream entityStream) throws IOException {
        if (message == null) {
            return;
        }

        message.writeTo(entityStream);
    }

    @Nonnull
    protected String getParseFromInputStreamMethodName() {
        return "parseFrom";
    }
}