package com.example.languagedictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText foreign_txtEdit, english_tranEdit, commentEdit, language_txtEdit;
    private TextView dictionary_listText;
    private Button insertButton, updateButton, deleteButton, viewButton;
    DictionaryDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foreign_txtEdit = (EditText) findViewById(R.id.foreign_txt);
        english_tranEdit = (EditText) findViewById(R.id.english_trans);
        commentEdit = (EditText) findViewById(R.id.comment_txt);
        language_txtEdit = (EditText) findViewById(R.id.language_txt);
        dictionary_listText = (TextView)findViewById(R.id.dictionary_list);

        insertButton = (Button) findViewById(R.id.insert_button);
        updateButton = (Button) findViewById(R.id.update_button);
        deleteButton = (Button) findViewById(R.id.delete_button);
        viewButton = (Button) findViewById(R.id.view_button);
        dbHelper = new DictionaryDBHelper(this);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foreign_txt = foreign_txtEdit.getText().toString();
                String english_trans = english_tranEdit.getText().toString();
                String comment_txt = commentEdit.getText().toString();
                String lang_txt = language_txtEdit.getText().toString();

                Boolean is_insert_successful = dbHelper.insertUserData(foreign_txt, english_trans, comment_txt, lang_txt);
                if (is_insert_successful) {
                    Toast.makeText(MainActivity.this, "New Dictionary Created", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to create dictionary", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = dbHelper.getUserData();
                if (result.getCount() == 0){
                    dictionary_listText.setText("Dictionary is empty");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()){
                    buffer.append("Foreign Text:  "+result.getString(0)+"\n");
                    buffer.append("English Trans: "+result.getString(1)+"\n");
                    buffer.append("Comment Text:  "+result.getString(2)+"\n");
                    buffer.append("Language Text: "+result.getString(3)+"\n");
                    buffer.append("\n");
                    dictionary_listText.setText(buffer.toString());
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foreign_txt = foreign_txtEdit.getText().toString();
                Boolean checkdeletedata = dbHelper.deleteUserData(foreign_txt);
                if (checkdeletedata == true)
                    Toast.makeText(MainActivity.this, "Dictionary deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Failed to delete dictionary", Toast.LENGTH_SHORT).show();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foreign_txt = foreign_txtEdit.getText().toString();
                String english_trans = english_tranEdit.getText().toString();
                String comment_txt = commentEdit.getText().toString();
                String lang_txt = language_txtEdit.getText().toString();
                Boolean checkupdatedata = dbHelper.updateUserData(foreign_txt, english_trans, comment_txt, lang_txt);
                if (checkupdatedata == true)
                    Toast.makeText(MainActivity.this, "Dictionary Updated", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(MainActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}