package losbibis.xia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.ActionBar;
import android.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

import losbibis.xia.info.androidhive.actionbar.model.SpinnerNavItem;
import losbibis.xia.info.androidhive.info.actionbar.adapter.TitleNavigationAdapter;

/**
 * Created by almeric on 30/11/16.
 */

public class Menu_Principale extends Activity implements ActionBar.OnNavigationListener {

    // action bar
    private ActionBar actionBar;

    // Title navigation Spinner data
    private ArrayList<SpinnerNavItem> navSpinner;

    // Navigation adapter
    private TitleNavigationAdapter adapter;


  //  ActionBar ab;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.menu_principale);

        actionBar = getActionBar();
       // Hide the action bar title
        actionBar.setDisplayShowTitleEnabled(false);

        // Enabling Spinner dropdown navigation
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        // Spinner title navigation data
        navSpinner = new ArrayList<SpinnerNavItem>();
        navSpinner.add(new SpinnerNavItem("", R.drawable.ic_help_black_24dp));
        navSpinner.add(new SpinnerNavItem(getString(R.string.nav_shopping), R.drawable.ic_help_black_24dp));
        navSpinner.add(new SpinnerNavItem(getString(R.string.nav_do_the_shopping_list) , R.drawable.ic_place_black_24dp));
        navSpinner.add(new SpinnerNavItem(getString(R.string.nav_modify_shopping_products), R.drawable.ic_refresh_black_24dp));
        navSpinner.add(new SpinnerNavItem(getString(R.string.nav_modify_places), R.drawable.ic_search_black_24dp));

        // title drop down adapter
        adapter = new TitleNavigationAdapter(getApplicationContext(), navSpinner);

        // assigning the spinner navigation
        actionBar.setListNavigationCallbacks(adapter, this);
        getActionBar().setDisplayShowTitleEnabled(false);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }
    /**
     * On selecting action bar icons
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
     //   ...
        return false;
    }

    /**
     * Actionbar navigation item select listener
     * */
    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        // Action to be taken after selecting a spinner item

        if (itemPosition == 2)
        {
            Intent test = new Intent(Menu_Principale.this, Modification_item.class);
            Menu_Principale.this.startActivity(test);
                    }
        if (itemPosition == 1)
        {
            Intent test = new Intent(Menu_Principale.this, Shopping.class);
            Menu_Principale.this.startActivity(test);
        }


        return false;
    }
}