package com.example.shankarpentyala.labassignment4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(View v)
    {
        EditText UserName = (EditText) findViewById(R.id.text_user);
        EditText Password1 =(EditText) findViewById(R.id.text_pwd);
        TextView Errortext = (TextView) findViewById(R.id.text_Error);
        String S_username = UserName.getText().toString();
        String S_pwd =Password1.getText().toString();
        if(S_username.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Enter User Name", Toast.LENGTH_SHORT).show();
        }
        else if (S_pwd.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
        }
        else if (!S_username.equals("LAB4"))
        {
            Toast.makeText(getApplicationContext(),"Invalid UserName",Toast.LENGTH_SHORT).show();
        }
        else if(!S_pwd.equals("LAB4"))
        {
            Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent redirect=new Intent(Login.this,Webservice.class);
            startActivity(redirect);
        }

    }

    public void signup(View V)
    {
        Intent Redirect = new Intent (Login.this,Register.class);
        startActivity (Redirect);
    }


}
