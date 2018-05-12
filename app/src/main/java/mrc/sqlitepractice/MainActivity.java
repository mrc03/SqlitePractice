package mrc.sqlitepractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    MyHelper myHelper;
    Button addButton;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        myHelper = new MyHelper(this, null, null, 1);
        addButton = (Button) findViewById(R.id.button1);
        deleteButton = (Button) findViewById(R.id.button2);
        setListeners();
        //printDatabase();
    }

    public void setListeners() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contacts contacts = new Contacts(editText.getText().toString());
                myHelper.addRow(contacts);
                printDatabase();

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contacts contacts = new Contacts(editText.getText().toString());
                myHelper.delRow(contacts);
                printDatabase();

            }
        });
    }


    public void printDatabase() {
        ArrayList<Contacts> list = (ArrayList<Contacts>) myHelper.getAllContacts();
        int i;
        String text = "";
        for (i = 0; i < list.size(); i++) {
            text += list.get(i).get_name().toString();
        }
        textView.setText(text);
        editText.setText("");
    }
}
