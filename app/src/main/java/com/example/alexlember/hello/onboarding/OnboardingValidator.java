package com.example.alexlember.hello.onboarding;

import lombok.Setter;

@Setter
public class OnboardingValidator {

    private boolean isNameValid;
    private boolean isCompanyValid;
    private boolean isPositionValid;
    private boolean isEmailValid;
    private boolean isBoxChecked;

    public boolean isFormValid() {
        return isNameValid
                && isCompanyValid
                && isPositionValid
                && isEmailValid
                && isBoxChecked;
    }
}
