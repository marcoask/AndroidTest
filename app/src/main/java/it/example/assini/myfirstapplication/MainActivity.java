package it.example.assini.myfirstapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

//public class MyActivity extends AppCompatActivity {
public class MainActivity extends Activity {

    private DatePicker pickerDate;
    private TextView info;
    public final static String EXTRA_MESSAGE = "it.example.assini.myfirstapplication.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button btn = (Button) findViewById(R.id.button);
//        if(!"".equals(name) && !"".equals(year)){
//            btn.setClickable(true);
//        }

        info = (TextView) findViewById(R.id.info);
        pickerDate = (DatePicker) findViewById(R.id.pickerdate);

        Calendar today = Calendar.getInstance();

        pickerDate.init(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH),
                new OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        //Toast.makeText(getApplicationContext(), "onDateChanged", Toast.LENGTH_SHORT).show();

                        info.setText(
                                "\nData scelte:\nYear: " + year + "\n" +
                                        "Month of Year: " + monthOfYear+1  + "\n" +
                                        "Day of Month: " + dayOfMonth);

                    }
                }
        );

    }

    public void onButtonTap(View v) {

        EditText editName =  (EditText) findViewById(R.id.Name);
        String name = editName.getText().toString();

        int year = pickerDate.getYear();
        int month = pickerDate.getMonth();
        int day = pickerDate.getDayOfMonth();

        String anni = getAge(year, month, day);

/**
        int year = 0;
        EditText editYear =  (EditText) findViewById(R.id.Year);
        if (!TextUtils.isEmpty(editYear.getText().toString())){
            year = Integer.parseInt(editYear.getText().toString());
            anni = getAge(year, 1, 1);
        } else {
            anni = "?";
        }
 **/

        if ("Elena".equalsIgnoreCase(name.trim()) && 1981==year){
            name = "Amorino";
            anni = "giovane";
        }

        Toast myToast = Toast.makeText(getApplicationContext(), "Ciao " + name + "! (anni: " + anni + ")", Toast.LENGTH_LONG);
        myToast.show();

    }

    public void onDateTap(View v) {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df;
        df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ITALIAN);
        String formattedDate = df.format(c.getTime());

        Toast myToast = Toast.makeText(getApplicationContext(), "Oggi è il " + formattedDate, Toast.LENGTH_LONG);
        myToast.show();

    }

    public void onButtonNewActivityTap(View v) {

        //http://developer.android.com/training/basics/firstapp/starting-activity.html
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.Name);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);


    }

    public void onShareTap(View v) {

        sendEmail();

    }

    /**
     * Method to extract the user's age from the entered Date of Birth.
     */
    private String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = age;

        return ageInt.toString();
    }

    private void sendEmail() {
        Log.i("Send email", "Send email");

        String[] TO = {"marco.assini@gmail.com"};
        //String[] CC = {"xyz@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "MyFirstApplication");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "bla bla bla");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Send email", "Finished sending email...");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


}
