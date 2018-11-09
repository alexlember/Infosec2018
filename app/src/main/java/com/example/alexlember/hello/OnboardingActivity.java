package com.example.alexlember.hello;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.*;
import com.example.alexlember.hello.onboarding.OnboardingValidator;
import com.example.alexlember.hello.onboarding.TextWatcherAdapter;
import com.example.alexlember.hello.onboarding.controller.OnboardingController;
import com.example.alexlember.hello.onboarding.controller.SendMailTask;

public class OnboardingActivity extends AppCompatActivity {

    TextInputLayout nameEditLayout, companyEditLayout, positionEditLayout, emailEditLayout;
    EditText nameEditText, companyEditText, positionEditText, emailEditText;
    Button acceptButton;
    CheckBox acceptCheckbox;
  //  CheckBox wantToReceiveCheckbox;
    ImageView backgroundImageView;

    OnboardingValidator validator = new OnboardingValidator();
    OnboardingController controller = new OnboardingController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        nameEditLayout = findViewById(R.id.nameEditLayout);
        companyEditLayout = findViewById(R.id.companyEditLayout);
        positionEditLayout = findViewById(R.id.positionEditLayout);
        emailEditLayout = findViewById(R.id.emailEditLayout);

        nameEditText = findViewById(R.id.nameEditText);
        companyEditText = findViewById(R.id.companyEditText);
        positionEditText = findViewById(R.id.positionEditText);
        emailEditText = findViewById(R.id.emailEditText);

        acceptCheckbox = findViewById(R.id.acceptCheckbox);
        //wantToReceiveCheckbox = findViewById(R.id.wantToReceiveCheckbox); TODO решили, что пока не нужно поле
        acceptButton = findViewById(R.id.acceptButton);
        backgroundImageView = findViewById(R.id.backgroundImageView);

        Drawable background = backgroundImageView.getDrawable();
        background.setAlpha(70);

        updateButtonEnabled();

        TextWatcher nameTextWatcher = new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                validator.setNameValid(isNameValid());
                updateButtonEnabled();
            }
        };
        View.OnFocusChangeListener nameOnFocusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validator.setNameValid(isNameValid());
                    updateButtonEnabled();
                }
            }
        };
        nameEditText.addTextChangedListener(nameTextWatcher);
        nameEditText.setOnFocusChangeListener(nameOnFocusChangeListener);

        TextWatcher companyTextWatcher = new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                validator.setCompanyValid(isCompanyValid());
                updateButtonEnabled();
            }
        };
        View.OnFocusChangeListener companyOnFocusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validator.setCompanyValid(isCompanyValid());
                    updateButtonEnabled();
                }
            }
        };
        companyEditText.addTextChangedListener(companyTextWatcher);
        companyEditText.setOnFocusChangeListener(companyOnFocusChangeListener);

        TextWatcher positionTextWatcher = new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                validator.setPositionValid(isPositionValid());
                updateButtonEnabled();
            }
        };
        View.OnFocusChangeListener positionOnFocusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validator.setPositionValid(isPositionValid());
                    updateButtonEnabled();
                }
            }
        };
        positionEditText.addTextChangedListener(positionTextWatcher);
        positionEditText.setOnFocusChangeListener(positionOnFocusChangeListener);

        TextWatcher emailTextWatcher = new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                validator.setEmailValid(isEmailValid());
                updateButtonEnabled();
            }
        };
        View.OnFocusChangeListener emailOnFocusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validator.setEmailValid(isEmailValid());
                    updateButtonEnabled();
                }
            }
        };
        emailEditText.addTextChangedListener(emailTextWatcher);
        emailEditText.setOnFocusChangeListener(emailOnFocusChangeListener);


        acceptCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               validator.setBoxChecked(isChecked);
               updateButtonEnabled();
           }
       });

//        wantToReceiveCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//           @Override
//           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//               controller.getMessage().setInterestedInDemoMaterials(isChecked);
//           }
//       });


        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();
            }
        });

    }

    private boolean isNameValid() {

        String nameInput = nameEditText.getText().toString().trim();

        if (nameInput.isEmpty()) {
            nameEditLayout.setError("Введите ФИО");
            return false;
        } else {
            nameEditLayout.setError(null);
            controller.getMessage().setName(nameInput);
            return true;
        }
    }

    private boolean isCompanyValid() {

        String companyInput = companyEditText.getText().toString().trim();

        if (companyInput.isEmpty()) {
            companyEditLayout.setError("Введите компанию");
            return false;
        } else {
            companyEditLayout.setError(null);
            controller.getMessage().setCompany(companyInput);
            return true;
        }
    }

    private boolean isPositionValid() {

        String positionInput = positionEditText.getText().toString().trim();

//        if (positionInput.isEmpty()) {
//            positionEditLayout.setError("Введите должность");
//            return false;
//        } else {
            positionEditLayout.setError(null);
            controller.getMessage().setPosition(positionInput.isEmpty() ? null : positionInput);
            return true;
      //  }
    }

    private boolean isEmailValid() {

        String emailInput = emailEditText.getText().toString().trim();

        if (emailInput.isEmpty()) {
            emailEditLayout.setError("Введите email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            emailEditLayout.setError("Введите корректный email");
            return false;
        } else {
            emailEditLayout.setError(null);
            controller.getMessage().setEmail(emailInput);
            return true;
        }
    }

    private void changeActivity() {
        controller.sendEmailWithDataMessage(OnboardingActivity.this);
        Intent intent = new Intent(OnboardingActivity.this, QuizzActivity.class);
        OnboardingActivity.this.startActivity(intent);
        killAllListeners();
    }

    private void updateButtonEnabled() {
        boolean isValid = validator.isFormValid();
        acceptButton.setEnabled(isValid);
    }

    private void killAllListeners() {
        nameEditText.addTextChangedListener(null);
        nameEditText.setOnFocusChangeListener(null);

        companyEditText.addTextChangedListener(null);
        companyEditText.setOnFocusChangeListener(null);

        positionEditText.addTextChangedListener(null);
        positionEditText.setOnFocusChangeListener(null);

        emailEditText.addTextChangedListener(null);
        emailEditText.setOnFocusChangeListener(null);

        acceptCheckbox.setOnCheckedChangeListener(null);
        acceptButton.setOnClickListener(null);
    }
}
