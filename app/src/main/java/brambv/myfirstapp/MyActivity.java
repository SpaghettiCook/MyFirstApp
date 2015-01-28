package brambv.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;

import java.util.ArrayList;

// some extra comments, so
public class MyActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    ArrayAdapter mArrayAdapter;
    ArrayList mNameList = new ArrayList();
    Button mMainButton;
    EditText mMainEditText;
    ListView mMainListView;
    ShareActionProvider mShareActionProvider;
    TextView mMainTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mMainButton = (Button) findViewById(R.id.main_button);
        mMainButton.setOnClickListener(this);
        mMainEditText = (EditText) findViewById(R.id.main_edittext);
        mMainTextView = (TextView) findViewById(R.id.main_textView);
        mMainTextView.setText("Set in Java!");

        // 4. Access the ListView
        mMainListView = (ListView) findViewById(R.id.main_listview);
        mMainListView.setOnItemClickListener(this);

        // Create an ArrayAdapter for the ListView
        mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mNameList);

        // Set the ListView to use the ArrayAdapter
        mMainListView.setAdapter(mArrayAdapter);
    }

    @Override
    public void onClick(View v) {
        // Take what was typed into the EditText
        // and use in TextView
        mMainTextView.setText(mMainEditText.getText().toString() + " is learning Android development!");

        // Also add that value to the list shown in the ListView
        mNameList.add(mMainEditText.getText().toString());
        mArrayAdapter.notifyDataSetChanged();

        setShareIntent();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("omg android", position + ": " + mNameList.get(position));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu.
        // Adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);

        // Access the Share Item defined in menu XML
        MenuItem shareItem = menu.findItem(R.id.menu_item_share);

        // Access the object responsible for
        // putting together the sharing submenu
        if (shareItem != null) {
            mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        }

        // Create an Intent to share your content
        setShareIntent();

        return true;
    }

    private void setShareIntent() {

        if (mShareActionProvider != null) {

            // create an Intent with the contents of the TextView
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Android Development");
            shareIntent.putExtra(Intent.EXTRA_TEXT, mMainTextView.getText());

            // Make sure the provider knows
            // it should work with that Intent
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}
