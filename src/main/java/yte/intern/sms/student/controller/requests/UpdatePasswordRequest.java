package yte.intern.sms.student.controller.requests;

public record UpdatePasswordRequest(
        String password
) {
    public String getPassword() {
        return password;
    }
}
