package com.only.yc.only_onez;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.EditText;

public class words extends AppCompatActivity {
    private Toolbar mToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        Words_TooBlar();
        EditText_Settings();
    }

    public void Words_TooBlar() {
        mToolBar = (Toolbar) findViewById(R.id.Words_Toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void EditText_Settings() {
        EditText editText = new EditText(this);
        editText.setInputType(InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE);
        editText.setGravity(Gravity.TOP);
        editText.setSingleLine(false);
        editText.setHorizontallyScrolling(false);
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
