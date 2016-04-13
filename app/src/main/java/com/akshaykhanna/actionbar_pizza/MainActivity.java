package com.akshaykhanna.actionbar_pizza;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ShareActionProvider;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {


    private String[] titles;
    private ListView drawerList;
    private DrawerLayout dl;
    private ActionBarDrawerToggle drawerToggle;
    private ShareActionProvider shareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dl=(DrawerLayout)findViewById(R.id.drawer_layout);
        titles=getResources().getStringArray(R.array.titles);
        drawerList=(ListView)findViewById(R.id.lv_drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles));
        drawerList.setOnItemClickListener(this);

        ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(this,dl,R.string.open_drawer,R.string.close_drawer)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        dl.setDrawerListener(drawerToggle);

       //display drawer button:
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        if(savedInstanceState==null)
        {
            selectFragment(0);
        }
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
// If the drawer is open, hide action items related to the content view
        boolean drawerOpen = dl.isDrawerOpen(drawerList);
        menu.findItem(R.id.action_share).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        selectFragment(position);
    }

    private void selectFragment(int position)
    {

        setActionBar(position);
        Fragment fragment;
        switch (position)
        {
            case 1:
                fragment=new PizzasFragment();
                break;
            case 2:
                fragment=new PastaFragment();
                break;
            case 3:
                fragment=new StoresFragment();
                break;
            case 4:
                fragment=new NumberAndObjectsFragment();
                break;
            default:
                fragment=new TopFragment();
        }
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content_frame, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        //close drawer
        dl.closeDrawer(drawerList);
    }

    private void setActionBar(int position) {
        String title;
        if(position==0)
        {
            title=getResources().getString(R.string.app_name);
        }
        else
        {
            title=titles[position];
        }
        getActionBar().setTitle(title);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
        setIntent("This is example text");
        return super.onCreateOptionsMenu(menu);
    }
    private void setIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

      /*  if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }*/
        switch(item.getItemId())
        {
            case R.id.action_create_order:
                Intent intent=new Intent(MainActivity.this,CreateOrder.class);
                startActivity(intent);
                return true;

            case R.id.action_settings:
                return true;
 
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
