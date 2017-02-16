package com.example.shankarpentyala.labassignment4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById (R.id.fab);
        fab.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Snackbar.make (view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction ("Action", null).show ( );
            }
        });
    }
    public void Register(View V)
    {
        EditText FullName = (EditText) findViewById (R.id.FullName);
        EditText Email = (EditText) findViewById (R.id.Email);
        EditText Password = (EditText) findViewById (R.id.editText9);
        CheckBox Check1 =(CheckBox) findViewById (R.id.checkBox);

        String SfullName = FullName.getText ().toString ();
        String SEmail = Email.getText ().toString ();
        String Spassword = Password.getText ().toString ();

        if (SfullName.isEmpty () || SEmail.isEmpty () || Spassword.isEmpty ())
        {
            Toast.makeText (getApplicationContext (),"Please fill the details",Toast.LENGTH_SHORT).show ();
        }
        else if (!Check1.isChecked ())
        {
            Toast.makeText (getApplicationContext (),"Please accept the terms and conditions",Toast.LENGTH_SHORT).show ();
        }
        else
        {
            Intent Redirect = new Intent(Register.this,Login.class);
            startActivity (Redirect);
        }

    }

}
