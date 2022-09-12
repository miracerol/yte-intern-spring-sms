package yte.intern.sms.academician.controller.requests;

public record UpdatePasswordRequest(
        String password
) {
    public String getPassword() {
        return password;
    }
}
