package brambv.myfirstapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MyActivity extends ActionBarActivity implements View.OnClickListener{

    ArrayAdapter mArrayAdapter;
    ArrayList mNameList = new ArrayList();
    Button mainButton;
    EditText mainEditText;
    ListView mainListView;
    TextView mainTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mainButton = (Button) findViewById(R.id.main_button);
        mainButton.setOnClickListener(this);
        mainEditText = (EditText) findViewById(R.id.main_edittext);
        mainTextView = (TextView) findViewById(R.id.main_textView);
        mainTextView.setText("Set in Java!");

        // 4. Access the ListView
        mainListView = (ListView) findViewById(R.id.main_listview);

        // Create an ArrayAdapter for the ListView
        mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mNameList);

        // Set the ListView to use the ArrayAdapter
        mainListView.setAdapter(mArrayAdapter);
    }

    @Override
    public void onClick(View v) {
        // Take what was typed into the EditText
        // and use in TextView
        mainTextView.setText(mainEditText.getText().toString() + " is learning Android development!");

        // Also add that value to the list shown in the ListView
        mNameList.add(mainEditText.getText().toString());
        mArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

}
