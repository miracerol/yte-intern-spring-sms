package yte.intern.sms.common.response;

public record SecurityRecord(
        ResponseType responseType,
        String message,
        String isAuthority
) {
}
