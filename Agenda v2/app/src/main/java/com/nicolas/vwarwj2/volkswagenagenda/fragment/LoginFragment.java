package com.nicolas.vwarwj2.volkswagenagenda.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicolas.vwarwj2.volkswagenagenda.interfaz.NavigationHost;
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

        MaterialButton nextButton = view.findViewById(R.id.next_button);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPasswordAndUsernameValid(passwordEditText.getText(),usernameEditText.getText()) ) {
                    passwordTextInput.setError(getString(R.string.shr_error_password));
                    usernameTextInput.setError(getString(R.string.shr_error_username));
                    passwordEditText.getText().clear();
                    usernameEditText.getText().clear();

                } else {
                    passwordTextInput.setError(null); // Clear the error
                    ((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false); // Navigate to the next Fragment
                }
            }
        });


        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordAndUsernameValid(passwordEditText.getText(),usernameEditText.getText())) {
                    passwordTextInput.setError(null); //Clear the error
                    usernameTextInput.setError(null);
                }
                return false;
            }
        });



        return view;
    }

    private boolean isPasswordAndUsernameValid(@Nullable Editable pass,@Nullable Editable user) {
        return (pass != null && pass.toString().equals("rikap") && user != null && user.toString().equals("nicolas")) ;
    }

}
