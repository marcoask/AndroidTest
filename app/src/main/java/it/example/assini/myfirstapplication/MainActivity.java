package it.example.assini.myfirstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonTap(View v) {
        EditText editName =  (EditText) findViewById(R.id.Name);
        String name = editName.getText().toString();

        EditText editYear =  (EditText) findViewById(R.id.Year);
        int year = Integer.parseInt(editYear.getText().toString());
        String anni = getAge(year,1,1);

        if ("Elena".equalsIgnoreCase(name.trim())){
            name = "Amorino";
            anni = "giovane";
        }

        Toast myToast = Toast.makeText(getApplicationContext(), "Ciao " + name +"! (anni: " +anni+")" , Toast.LENGTH_LONG);
        myToast.show();

    }

    public void onDateTap(View v) {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        Toast myToast = Toast.makeText(getApplicationContext(), "Oggi è il " + formattedDate, Toast.LENGTH_LONG);
        myToast.show();


    }




    /**
     * Method to extract the user's age from the entered Date of Birth.
     * @param DoB String The user's date of birth.
     * @return ageS String The user's age in years based on the supplied DoB.
     */
    private String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }

}
