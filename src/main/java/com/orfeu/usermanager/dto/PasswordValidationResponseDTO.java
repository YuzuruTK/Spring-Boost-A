package com.orfeu.usermanager.dto;

public class PasswordValidationResponseDTO {

    private boolean valid;

    public PasswordValidationResponseDTO() {
    }

    public PasswordValidationResponseDTO(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
