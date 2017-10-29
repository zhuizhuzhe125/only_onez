package com.only.yc.only_onez;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class UserBirthday extends AppCompatActivity {

    private Toolbar Mtoolbar;
    int T_Day = 31;
    String Year[] = new String[100];
    String[] Month = new String[12];
    String[] Day;
    String F_Year;
    String F_Month1;
    String F_Day;
    private Spinner BYear;
    private Spinner BMonth;
    private Spinner BDay;
    private TextView TextShow;
    private Button Btn_Ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_birthday);
        UserBirthday_Toobar();

        initFor();
        init();
        initYear();
        initClick();
        Click_OK();
    }

    public void UserBirthday_Toobar() {
        Mtoolbar = (Toolbar) findViewById(R.id.UserBirthday_Toolbar);
        setSupportActionBar(Mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void initFor() {
        Day = new String[T_Day];
        for(int i= 2017, j = 0; i >1917; i--, j++) {
            String s = String.valueOf(i);
            Year[j] = s;
        }

        for(int i = 1, j =0; i < 13; i++, j++) {
            String s = String.valueOf(i);
            Month[j] = s;
        }

        for(int i = 1, j = 0; i <= T_Day; i++, j++) {
            String s = String.valueOf(i);
            Day[j] = s;
        }

        /*for (int i = 0; i < Day.length; i++) {
            System.out.println(Day[i]);
        }*/
    }
    public void initForDay() {
        Day = new String[T_Day];
        for(int i = 1, j = 0; i <= T_Day; i++, j++) {
            String s = String.valueOf(i);
            Day[j] = s;
        }
        initDay();

    }
    public void init() {
        BYear = (Spinner) findViewById(R.id.UserBirthday_Year);
        BMonth = (Spinner) findViewById(R.id.UserBirthday_Month);
        BDay = (Spinner) findViewById(R.id.UserBirthday_Day);
        TextShow = (TextView) findViewById(R.id.UserBirthday_Show);
        Btn_Ok = (Button) findViewById(R.id.UserBirthday_Button_Ok);
    }

    public void initYear() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Year);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Month);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Day);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BYear.setAdapter(adapter);
        BMonth.setAdapter(adapter1);
        BDay.setAdapter(adapter2);
    }
    public void initDay() {
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Day);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BDay.setAdapter(adapter3);
    }
    public void initClick() {
        BYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                F_Year = Year[position];
                TextShow.setText(F_Year+" - "+F_Month1+" - "+F_Day);
                if(F_Month1 != null) {
                    IF_Year();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //月；
        BMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                F_Month1 = Month[position];
                TextShow.setText(F_Year+" - "+F_Month1+" - "+F_Day);
                IF_Year();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        BDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                F_Day = Day[position];
                TextShow.setText(F_Year+" - "+F_Month1+" - "+F_Day);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void IF_Year() {

        int a_Year = Integer.parseInt(F_Year);
        int a_Month = Integer.parseInt(F_Month1);
        int T_tMonth = 0;
        if((a_Year % 4 ==0 && a_Year % 100 == 0) || a_Year % 400 ==0) {
            T_tMonth = 1;
        }
        if(T_tMonth == 1 && a_Month == 2) {
            T_Day = 29;
            initForDay();
        }
        if(T_tMonth == 0 && a_Month == 2) {
            T_Day = 28;
            initForDay();
        }
        if(a_Month == 4 || a_Month == 6 || a_Month == 9 || a_Month == 11) {
            T_Day = 30;
            initForDay();

        }
        if(a_Month == 1 || a_Month == 3 || a_Month == 5 || a_Month == 7 || a_Month == 8 || a_Month == 10 || a_Month == 12) {
            T_Day = 31;
            initForDay();

        }

    }

    public void Click_OK() {
        Btn_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Birthday = TextShow.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("Birth_Day",Birthday);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        String Birthday = TextShow.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("Birth_Day",Birthday);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
