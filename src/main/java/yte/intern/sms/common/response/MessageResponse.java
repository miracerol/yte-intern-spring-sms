package yte.intern.sms.common.response;

public record MessageResponse(
        ResponseType responseType,
        String message
) {

}
