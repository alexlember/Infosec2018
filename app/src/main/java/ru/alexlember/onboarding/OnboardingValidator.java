package ru.alexlember.onboarding;

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
               // && isCompanyValid
                //&& isPositionValid TODO решили, что это поле необязательно
                && isEmailValid
                && isBoxChecked;
    }
}
