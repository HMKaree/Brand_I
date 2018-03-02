package com.example.android.brand_i;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class WelcomePage extends AppCompatActivity implements View.OnClickListener {


    EditText EmailEditor;
    EditText PasswordEditor;
    TextView RegisterLink;
    Button LoginBtn;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);



        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        TextView RegisterLink = (TextView) findViewById(R.id.RegisterLink);
        Button LoginBtn = (Button) findViewById(R.id.LoginBtn);

        RegisterLink.setOnClickListener(this);
        LoginBtn.setOnClickListener(this);


    }

    private void UserLogin()
    {
        String email= EmailEditor.getText().toString().trim();
        String password= PasswordEditor.getText().toString().trim();

        if (email.isEmpty()){
            EmailEditor.setError("Email is required");
            EmailEditor.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EmailEditor.setError("Enter valid email address");
            EmailEditor.requestFocus();
            return;
        }

        if(password.isEmpty()){
            PasswordEditor.setError("Password is required");
            PasswordEditor.requestFocus();
            return;
        }

        if(password.length()<6){
            PasswordEditor.setError("Minimum length of password is 6");
            PasswordEditor.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(WelcomePage.this, BottomNavigationPage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.RegisterLink:
                startActivity(new Intent(this, RegisterPage.class));
                break;

            case R.id.LoginBtn:
                UserLogin();
                startActivity(new Intent(this, BottomNavigationPage.class));
                break;

        }


    }
}


