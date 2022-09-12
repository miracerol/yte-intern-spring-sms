package yte.intern.sms.assistant.controller.requests;

public record UpdatePasswordRequest(
        String password
) {
    public String getPassword() {
        return password;
    }
}
