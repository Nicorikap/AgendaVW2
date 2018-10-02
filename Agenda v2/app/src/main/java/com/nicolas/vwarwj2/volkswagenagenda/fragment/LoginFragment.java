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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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

        MaterialButton nextButton = view.findViewById(R.id.next_button);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isPasswordValid(passwordEditText.getText())&&!isUsernameValid(usernameEditText.getText()) ) {

                    passwordTextInput.setError(getString(R.string.shr_error_password));
                    usernameTextInput.setError(getString(R.string.shr_error_username));
                    passwordEditText.getText().clear();
                    usernameEditText.getText().clear();

                } else {

                    passwordTextInput.setError(null); // Clear the error
                    usernameTextInput.setError(null);
                    passwordEditText.getText().clear();
                    usernameEditText.getText().clear();
                    //((NavigationHost) getActivity()).navigateTo(new ContactosFragment(), false); // Navigate to the next Fragment
                    goToMainPage();

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
        return (pass != null && pass.toString().equals("n")) ;
    }

    private boolean isUsernameValid(@Nullable Editable user) {
        return (user != null && user.toString().equals("n")) ;
    }
    private void goToMainPage() {
        Intent i = new Intent(getActivity(), InicioActivity.class);
        startActivity(i);

    }
}
