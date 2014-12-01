package mikecanco.de.kartography.fragments;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import mikecanco.de.kartography.R;
import mikecanco.de.kartography.activities.MainActivity;

public class LoginAccountDialogFragment extends DialogFragment {

    public static LoginAccountDialogFragment newInstance(String title) {
        LoginAccountDialogFragment ca = new LoginAccountDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        ca.setArguments(args);
        return ca;
    }

    private EditText username;
    private EditText password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_account, container);
        username = (EditText) view.findViewById(R.id.etCreateUsername);
        password = (EditText) view.findViewById(R.id.etEnterPassword);

        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        Button confirm = (Button) view.findViewById(R.id.btnLoginAccount);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(username.getText().toString(),
                        password.getText().toString(), new LogInCallback() {
                            public void done(ParseUser user, ParseException e) {
                                if (user != null) {
                                    // Hooray! The user is logged in.
                                    Intent i = new Intent(getActivity(),
                                            MainActivity.class);
                                    startActivity(i);
                                    getActivity().finish();
                                } else {
                                    // Signup failed. Look at the ParseException
                                    // to see
                                    // what happened.
                                    Toast.makeText(getActivity(),
                                            "Invalid username or password",
                                            Toast.LENGTH_LONG).show();
                                    e.printStackTrace();
                                    e.getLocalizedMessage();
                                }
                            }
                        }
                );
            }
        });
        Button cancel = (Button) view.findViewById(R.id.btnLoginCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginAccountDialogFragment.this.dismiss();
            }
        });

        return view;
    }
}
