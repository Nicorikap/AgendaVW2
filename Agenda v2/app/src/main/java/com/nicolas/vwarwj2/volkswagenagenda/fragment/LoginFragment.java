package com.nicolas.vwarwj2.volkswagenagenda.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nicolas.vwarwj2.volkswagenagenda.InicioActivity;
import com.nicolas.vwarwj2.volkswagenagenda.R;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout
        // for this fragment
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        final TextInputLayout usernameTextInput = view.findViewById(R.id.username_text_input);
        final TextInputEditText usernameEditText = view.findViewById(R.id.username_edit_text);
        passwordTextInput.setPasswordVisibilityToggleEnabled(true);
        final MaterialButton nextButton = view.findViewById(R.id.next_button);
        nextButton.setEnabled(false);
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nextButton.setEnabled(false);

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (TextUtils.isEmpty(passwordEditText.getText())&&TextUtils.isEmpty(usernameEditText.getText())){
                    nextButton.setEnabled(false);
                }
                else{
                    nextButton.setEnabled(true);
                    nextButton.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(passwordEditText.getText())||TextUtils.isEmpty(usernameEditText.getText())){
                    nextButton.setEnabled(false);
                }
                else{
                    nextButton.setEnabled(true);
                    nextButton.setError(null);
                }
            }
        });
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (TextUtils.isEmpty(passwordEditText.getText())&&TextUtils.isEmpty(usernameEditText.getText())){
                    nextButton.setEnabled(false);
                }
                else{
                    nextButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(passwordEditText.getText())||TextUtils.isEmpty(usernameEditText.getText())){
                    nextButton.setEnabled(false);
                }
                else{
                    nextButton.setEnabled(true);
                    nextButton.setError(null);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isPasswordValid(passwordEditText.getText())||!isUsernameValid(usernameEditText.getText()) ) {
                    passwordEditText.getText().clear();
                    usernameEditText.getText().clear();
                    nextButton.setError(getString(R.string.wrong));
                    Toast.makeText(getContext(), R.string.wrong, Toast.LENGTH_SHORT).show();
                } else {
                    if(isPasswordValid(passwordEditText.getText())&&isUsernameValid(usernameEditText.getText()) ){
                        passwordTextInput.setError(null); // Clear the error
                        usernameTextInput.setError(null);
                        passwordEditText.getText().clear();
                        usernameEditText.getText().clear();
                        //((NavigationHost) getActivity()).navigateTo(new ContactosFragment(), false); // Navigate to the next Fragment - Not in use
                        goToMainPage();
                    }
                }
            }
        });

        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(null); //Clear the error
                    usernameTextInput.setError(null);
                }
                return false;
            }
        });

        return view;
    }
    private boolean isPasswordValid(@Nullable Editable pass) {
        return (pass != null && pass.toString().equals("nicolas")) ;
    }
    private boolean isUsernameValid(@Nullable Editable user) {
        return (user != null && user.toString().equals("nicolas")) ;
    }
    private void goToMainPage() {
        Intent i = new Intent(getActivity(), InicioActivity.class);
        startActivity(i);

    }
}
