package application;

import javax.ws.rs.core.MediaType;

public final class ProtobufMediaType {
    private ProtobufMediaType() {}

    public static final String MIME = "application/x-protobuf";

    public static final MediaType MEDIA_TYPE = new MediaType("application", "x-protobuf");
}