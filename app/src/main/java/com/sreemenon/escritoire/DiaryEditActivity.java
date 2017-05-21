package com.sreemenon.escritoire;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.sreemenon.db.DiaryModel;

import java.util.Date;
import java.util.Locale;

public class DiaryEditActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etDate;
    private EditText etEntry;

    private Date entry_date;
    DiaryModel entry = null;

    Calendar entryDateCalender = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            setEntryDateCalender(year, monthOfYear, dayOfMonth);
            updateDateLabel();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_diary_editor);
        setSupportActionBar(toolbar);

        etTitle = (EditText) findViewById(R.id.frag_diary_edit_et_title);
        etDate = (EditText) findViewById(R.id.frag_diary_edit_et_date);
        etEntry = (EditText) findViewById(R.id.frag_diary_edit_et_entry);

        etDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(DiaryEditActivity.this, date, entryDateCalender
                        .get(Calendar.YEAR), entryDateCalender.get(Calendar.MONTH),
                        entryDateCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        initializeData();

        updateDateLabel();
    }

    private void initializeData(){

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            if (bundle.containsKey(Constants.BUNDLE_DIARY_EDIT_ACTIVITY_ENTRY_ID)) {
                entry = DiaryModel.findById(DiaryModel.class, bundle.getLong(Constants.BUNDLE_DIARY_EDIT_ACTIVITY_ENTRY_ID));
                loadDataFromDiaryModel(entry);
            }
        }
    }

    private void loadDataFromDiaryModel(DiaryModel entry){
        etTitle.setText(entry.title);
        entryDateCalender.setTime(entry.date);
        updateDateLabel();
        etDate.setEnabled(false);
        etEntry.setText(entry.entry);
    }

    private void setEntryDateCalender(int year, int monthOfYear,
                                      int dayOfMonth){
        entryDateCalender.set(Calendar.YEAR, year);
        entryDateCalender.set(Calendar.MONTH, monthOfYear);
        entryDateCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    }

    private void updateDateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        entry_date = entryDateCalender.getTime();
        etDate.setText(sdf.format(entry_date));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.diary_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save_diary_entry) {
            saveDiaryEntry();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveDiaryEntry(){
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        if (entry == null){
            entry = new DiaryModel(etTitle.getText().toString(), entry_date, etEntry.getText().toString(), now);
        } else {
            entry.title = etTitle.getText().toString();
            entry.entry = etEntry.getText().toString();
        }
        entry.save();
        finish();
    }
}
