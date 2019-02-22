package com.example.l18domin.validate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String lNameStr = new String();
    String fNameStr = new String();
    String dateStr = new String();
    int depSel = 0;
    String [] telBuf = new String [10];
    int ind;
    String depOtherStr = new String();
    String searchDep = new String();
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText otherCity = (EditText) findViewById(R.id.editText6);
        otherCity.setFocusable(true);
        Log.i("indValue",String.valueOf(ind));
        SharedPreferences userdetails = getSharedPreferences("userdetails", Context.MODE_PRIVATE);
        if (savedInstanceState == null) {
            lNameStr = userdetails.getString("lName", "");
            fNameStr = userdetails.getString("fName", "");
            dateStr = userdetails.getString("date", "");
            depSel = userdetails.getInt("depSel", 0);
            ind = userdetails.getInt("indVal", 32000);
            depOtherStr = userdetails.getString("depOther","");
            //ind = 32000;
            Log.i("lifecycle",String.valueOf(ind));
            for (int i = 0;i<ind-32000;i++) {
                telBuf[i] = userdetails.getString("tel" + String.valueOf(i), "");
                Log.i("lifecycle", telBuf[i]);
                addNewLayout(32000 + i);
            }

        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("lifecycle","1");
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lifecycle","2");
        // The activity has become visible (it is now "resumed").

        //Completer l'information dans les EditText
        EditText lastname = (EditText) findViewById(R.id.editText1);
        lastname.setText(lNameStr, EditText.BufferType.EDITABLE);

        EditText firstname = (EditText) findViewById(R.id.editText2);
        firstname.setText(fNameStr, EditText.BufferType.EDITABLE);

        EditText date = (EditText) findViewById(R.id.editText3);
        date.setText(dateStr, EditText.BufferType.EDITABLE);

        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        mySpinner.setSelection(depSel);

        EditText city = (EditText) findViewById(R.id.editText6);
        city.setText(depOtherStr, EditText.BufferType.EDITABLE);

        for (int i = 0;i<ind-32000;i++) {
            EditText tel = findViewById(32200+i);
            tel.setText(telBuf[i]);
        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lifecycle","3");
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lifecycle","4");
        EditText lastname = (EditText) findViewById(R.id.editText1);
        lNameStr = lastname.getText().toString();
        EditText firstname = (EditText) findViewById(R.id.editText2);
        fNameStr = firstname.getText().toString();
        EditText date = (EditText) findViewById(R.id.editText3);
        dateStr = date.getText().toString();
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        depSel = mySpinner.getSelectedItemPosition();
        Log.i("lifecycle",String.valueOf(ind));
        for (int i = 0;i<ind-32000;i++) {
            EditText tel = findViewById(32200+i);
            telBuf[i] = tel.getText().toString();
        }

        EditText city = (EditText) findViewById(R.id.editText6);
        depOtherStr = city.getText().toString();


        SharedPreferences userdetails = getSharedPreferences("userdetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = userdetails.edit();
        edit.putString("lName", lNameStr);
        edit.putString("fName", fNameStr);
        edit.putString("date", dateStr);
        edit.putInt("depSel", depSel);
        edit.putInt("indVal",ind);
        edit.putString("depOther",depOtherStr);

        for (int i = 0;i<ind-32000;i++) {
            edit.putString("tel"+String.valueOf(i), telBuf[i]);
        }
        edit.apply();

        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lifecycle","5");
        // The activity is about to be destroyed.
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        EditText lastname = (EditText) findViewById(R.id.editText1);
        lNameStr = lastname.getText().toString();
        EditText firstname = (EditText) findViewById(R.id.editText2);
        fNameStr = firstname.getText().toString();
        EditText date = (EditText) findViewById(R.id.editText3);
        dateStr = date.getText().toString();
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        depSel = mySpinner.getSelectedItemPosition();
        for (int i = 0;i<ind-32000;i++) {
            EditText tel = findViewById(32200+i);
            telBuf[i] = tel.getText().toString();
        }
        EditText city = (EditText) findViewById(R.id.editText6);
        depOtherStr = city.getText().toString();

        outState.putString("lName", lNameStr);
        outState.putString("fName", fNameStr);
        outState.putString("date", dateStr);
        outState.putInt("depSel", depSel);
        outState.putInt("indVal",ind);
        outState.putString("depOther",depOtherStr);

        for (int i = 0;i<ind-32000;i++) {
            outState.putString("tel"+String.valueOf(i), telBuf[i]);
        }
        Log.i("lifecycle","7");
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        Log.i("lifecycle","8");
        super.onRestoreInstanceState(savedInstanceState);
        lNameStr = savedInstanceState.getString("lName");
        fNameStr = savedInstanceState.getString("fName");
        dateStr = savedInstanceState.getString("date");
        depSel = savedInstanceState.getInt("depSel");
        ind = savedInstanceState.getInt("indVal");
        depOtherStr = savedInstanceState.getString("depOther");

        for (int i = 0;i<ind-32000;i++) {
            telBuf[i] = savedInstanceState.getString("tel"+String.valueOf(i), "");
            addNewLayout(32000+i);
        }

    }

    public void validateAction (View v){
        EditText lastname = (EditText) findViewById(R.id.editText1);
        String lnStr = lastname.getText().toString();
        EditText firstname = (EditText) findViewById(R.id.editText2);
        String fnStr = firstname.getText().toString();
        EditText date = (EditText) findViewById(R.id.editText3);
        String dateStr = date.getText().toString();
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        String cityStr = mySpinner.getSelectedItem().toString();
        String telStr = new String();
        telStr = "-no phone";
        String textToShow = new String ();
        int i;
        for (i = 0;i<ind-32000;i++) {
            EditText tel = findViewById(32200+i);
            telStr =  "-" + tel.getText().toString();
            telBuf[i] = telStr;
            telStr = "";
        }
        if (lnStr.isEmpty())
            textToShow = getString(R.string.plsMessage)+" "+getString(R.string.lastname);
        else if (fnStr.isEmpty())
            textToShow = getString(R.string.plsMessage)+" "+getString(R.string.firstname);
        else if (dateStr.isEmpty())
            textToShow = getString(R.string.plsMessage)+" "+getString(R.string.date);
        else if (!checkDate(dateStr))
            textToShow = getString(R.string.wrongDate);
        else if (mySpinner.getSelectedItem().toString().equals(getString(R.string.item1)))
            textToShow = getString(R.string.plsMessage)+" "+getString(R.string.ville);
        else {
            if (mySpinner.getSelectedItem().toString().equals(getString(R.string.item5))){
                EditText city = (EditText) findViewById(R.id.editText6);
                cityStr = city.getText().toString();
            }
            if (cityStr.isEmpty())
                textToShow = getString(R.string.plsMessage)+" "+getString(R.string.ville);
            else{

                for (i = 0;i<ind-32000;i++) {
                    telStr += telBuf[i];
                }

                textToShow = lnStr + "-" + fnStr + "-" + dateStr + "-" + cityStr + telStr;
            }

        }
        Snackbar.make(findViewById(R.id.myRelativeLayout),textToShow, Snackbar.LENGTH_LONG).show();

    }

    public void SetDate (View v){
        EditText date = (EditText) findViewById(R.id.editText3);
        String dateStr = date.getText().toString();
        String dateData = "";
        if (dateStr.isEmpty()){
            dateData = "01012000";
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.putExtra(Intent.EXTRA_TEXT, dateData);

            int requestCode = 2;
            startActivityForResult(intent, requestCode);// Activity is started with requestCode 2
        }
        if (checkDate(dateStr)) {
            //DateData dateDataP = new DateData(dateStr.substring(0, 2), dateStr.substring(3, 5), dateStr.substring(6, 10));
            dateData = dateStr.substring(0, 2)+ dateStr.substring(3, 5)+ dateStr.substring(6, 10);

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.putExtra(Intent.EXTRA_TEXT, dateData);

            int requestCode = 2;
            // Verify that the intent will resolve to an activity
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, requestCode);// Activity is started with requestCode 2
            }


        }else{
            Snackbar.make(findViewById(R.id.myRelativeLayout),getString(R.string.wrongDate), Snackbar.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            DateData ans = data.getParcelableExtra("ANSWER");
            dateStr = ans.getDay()+"/"+ans.getMonth()+"/"+ans.getYear();
        }
        if (requestCode == 20){

            fNameStr =  data.getStringExtra("ANSWER");

        }
    }

    public boolean webSearch (MenuItem item){
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        String cityStr = mySpinner.getSelectedItem().toString();

        if (mySpinner.getSelectedItem().toString().equals(getString(R.string.item1))){
            Snackbar.make(findViewById(R.id.myRelativeLayout),getString(R.string.plsMessage)+" "+getString(R.string.ville), Snackbar.LENGTH_LONG).show();
        }else {
            if (mySpinner.getSelectedItem().toString().equals(getString(R.string.item5))) {
                EditText city = (EditText) findViewById(R.id.editText6);
                cityStr = city.getText().toString();
                if (cityStr.isEmpty()){
                    Snackbar.make(findViewById(R.id.myRelativeLayout),getString(R.string.plsMessage)+" "+getString(R.string.ville), Snackbar.LENGTH_LONG).show();
                    return true;
                }

            }
            if (cityStr.equals("Cotes dArmor"))
                cityStr = "Cotes-d%27Armor";

            String url =getString(R.string.wikiURL)+cityStr;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        return true;
    }

    public boolean share (MenuItem item){
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        String cityStr = mySpinner.getSelectedItem().toString();

        if( mySpinner.getSelectedItem().toString().equals(getString(R.string.item1))){
            Snackbar.make(findViewById(R.id.myRelativeLayout),getString(R.string.plsMessage)+" "+getString(R.string.ville), Snackbar.LENGTH_LONG).show();
        }else {
            if (mySpinner.getSelectedItem().toString().equals(getString(R.string.item5))) {
                EditText city = (EditText) findViewById(R.id.editText6);
                cityStr = city.getText().toString();
            }
            if (cityStr.equals("Cotes dArmor"))
                cityStr = "Cotes-d%27Armor";

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, cityStr);
            sendIntent.setType("text/plain");

            String title = getString(R.string.shareText); //"Partager ce contenu avec";
            Intent chooser = Intent.createChooser(sendIntent, title);

// Verify the original intent will resolve to at least one activity
            if (sendIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooser);
            }


        }
        return true;

    }

    public boolean viewNom (MenuItem item){
        EditText nom = (EditText) findViewById(R.id.editText1);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra("nom",nom.getText().toString());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);// Activity is started with requestCode 2
        }
        return true;
    }

    public boolean editPrenom (MenuItem item){
        EditText prenom = (EditText) findViewById(R.id.editText2);
        int reqCode = 20;
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.putExtra(Intent.EXTRA_TEXT,prenom.getText().toString());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, reqCode);// Activity is started with requestCode 2
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean resetAction (MenuItem item){
        EditText lastname = (EditText) findViewById(R.id.editText1);
        lastname.getText().clear();
        EditText firstname = (EditText) findViewById(R.id.editText2);
        firstname.getText().clear();
        EditText date = (EditText) findViewById(R.id.editText3);
        date.getText().clear();
        Spinner city = (Spinner) findViewById(R.id.spinner);
        city.setSelection(0);
        EditText cityO = (EditText) findViewById(R.id.editText6);
        cityO.getText().clear();

        int j=0;
        for (int i = 0;i<ind-32000;i++) {
            EditText tel = findViewById(32200+i);
            tel.getText().clear();
            j++;
            eraseLayout(32000+i);
        }
        ind = ind-j;
        Log.i("lifecycle",String.valueOf(ind)+"rst");
        return true;
    }

    public void addNumberAction (View v) {
        addNewLayout(ind);
        ind++;

    }

    public void suprNumberAction (View v){
        if (ind != 32000) {
            ind--;
            eraseLayout(ind);
        }
    }

    private boolean checkDate (String Date){
        if (Date.length() != 10)
            return false;
        else if (!Date.regionMatches(2,"/",0,1))
            return false;
        else if (!Date.regionMatches(5,"/",0,1))
            return false;
        else if (Integer.valueOf(Date.substring(0,2))>31||
                Integer.valueOf(Date.substring(3,5))>12||
                Integer.valueOf(Date.substring(6,10))>2018||
                Integer.valueOf(Date.substring(6,10))<1900
                )
            return false;
        else
            return true;
    }
    public void addNewLayout (int ind){

        final LinearLayout lm = (LinearLayout) findViewById(R.id.LAYVERT);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        // Create LinearLayout
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setId(ind);
        ll.setLayoutParams(params);

        // Create TextView
        TextView phone = new TextView(this);
        phone.setText(getString(R.string.phoneNumber));
        phone.setLayoutParams(new LinearLayout.LayoutParams(300, LinearLayout.LayoutParams.WRAP_CONTENT));
        phone.setId(ind+100);
        ll.addView(phone);

        //Create EditText
        EditText ePhone = new EditText(this);
        ePhone.setId(ind+200);
        ePhone.setHint("0621316866");
        ePhone.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        ePhone.setEms(10);
        ll.addView(ePhone);

        // Create Button Dynamically
        final Button btnShow = new Button(this);
        btnShow.setText(getString(R.string.buttonApeler));
        btnShow.setId(ind+300);
        btnShow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tel = (EditText) findViewById(btnShow.getId()-100);
                Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:"+tel.getText().toString())); //
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
            }
        });

        // Add Button to LinearLayout
        ll.addView(btnShow);
        lm.addView(ll);
    }
    public void eraseLayout (int indVal){
        LinearLayout placeHolder = (LinearLayout) findViewById(R.id.LAYVERT);
        placeHolder.removeView(findViewById(indVal));
    }



}
