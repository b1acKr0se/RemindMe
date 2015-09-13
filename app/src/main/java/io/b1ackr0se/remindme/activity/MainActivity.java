package io.b1ackr0se.remindme.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.b1ackr0se.remindme.view.MainView;
import io.b1ackr0se.remindme.R;

public class MainActivity extends AppCompatActivity implements MainView {

    @Bind(R.id.drawerLayout)DrawerLayout drawerLayout;
    @Bind(R.id.fab)FloatingActionButton fab;
    @Bind(R.id.progressBar)ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showButton() {
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideButton() {
        fab.setVisibility(View.GONE);
    }
}
