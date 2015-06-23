package com.davidrivett.workouttracker;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.davidrivett.workouttracker.data.User;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.rootLayout)
    CoordinatorLayout rootLayout;

    @InjectView(R.id.fabBtn)
    FloatingActionButton fabBtn;

    @OnClick(R.id.fabBtn)
    void onClick()
    {
        //Create User


        realm.beginTransaction();
        User user = realm.createObject(User.class); // Create a new object
        user.setName("John");
        realm.commitTransaction();

        getUserQuery();

        Snackbar.make(rootLayout, "Test Snack Bar", Snackbar.LENGTH_SHORT)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                    }
                }).show();

    }

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.sampleTxt)
    TextView sampleTxt;


    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        setSupportActionBar(toolbar);

        // Obtain a Realm instance
        realm = Realm.getInstance(this);

        getUserQuery();
    }

    void getUserQuery()
    {

        RealmQuery<User> query = realm.where(User.class);

        RealmResults<User> result = query.findAll();

        String holdVal = "";

        for (int i = 0; i < result.size(); i++)
        {
            holdVal += result.get(0).getName();
            holdVal += "\n";
        }

        sampleTxt.setText(holdVal);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
