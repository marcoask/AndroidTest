package it.example.assini.myfirstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonTap(View v) {
        EditText edit =  (EditText) findViewById(R.id.my_name);
        String text = edit.getText().toString();

        Toast myToast = Toast.makeText(getApplicationContext(), "Ciao " + text +"!", Toast.LENGTH_LONG);
        myToast.show();


    }

}
