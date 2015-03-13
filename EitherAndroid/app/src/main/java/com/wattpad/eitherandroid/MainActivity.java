package com.wattpad.eitherandroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.wattpad.api.*;
import com.wattpad.util.Either;

public class MainActivity extends ActionBarActivity {

    static {
        System.loadLibrary("EitherModule");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserService service = UserService.getService();
        Either<com.wattpad.api.Error, User> either = service.getUser(1234L);
        if (either.left() != null) {
            com.wattpad.api.Error error = either.left();
            // Handle the error
        } else {
            User user = either.right();
            // Do something with the user
        }
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
