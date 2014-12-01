package mikecanco.de.kartography.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.parse.Parse;
import com.parse.ParseUser;

import mikecanco.de.kartography.R;
import mikecanco.de.kartography.fragments.CreateAccountFragment;
import mikecanco.de.kartography.fragments.LoginAccountFragment;

public class LoginHomeActivity extends Activity {

    ImageView bg;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_home);
        Parse.initialize(this, "wN6gpXkwVEF0d9eTw1YzE0ISX2WM8ACdXM0ueuiu",
                "dGycMyN2IxdihwSV6kzDiCufYAL9UBBQEpOiRmMn");
        if (ParseUser.getCurrentUser() != null) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }

    }

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.login_home, menu);
//		return true;
//	}

    public void onCreateAccount(View v) {
        FragmentManager fm = getFragmentManager();
        CreateAccountFragment createDialog = CreateAccountFragment.newInstance("New User");
        createDialog.show(fm, "fragment_new_user");
    }

    public void onLoginAccount(View v) {
        FragmentManager fm = getFragmentManager();
        LoginAccountFragment loginDialog = LoginAccountFragment.newInstance("Enter Username and Password");
        loginDialog.show(fm, "fragment_login");
    }

}


