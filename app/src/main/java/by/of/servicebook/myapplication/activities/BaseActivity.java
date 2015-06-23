package by.of.servicebook.myapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.widget.RelativeLayout;

import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.fragments.activitycommunicator.ActivityCommunicator;

/**
 * Created by s.ankuda on 5/4/2015.
 */
public class BaseActivity extends ActionBarActivity implements ActivityCommunicator {
    public static final String EXTRA_FRAGMENT_NAME = "name.fragment.extra";
    public static final String EXTRA_FRAGMENT_ARGS = "args.fragment.extra";

    protected Bundle mainFragmentArgs;
    private Object mvcModel;
    public RelativeLayout rootLayout;

    private static boolean active = false;

    public static boolean isActive() {
        return active;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        rootLayout = (RelativeLayout) findViewById(R.id.rlRootLayout);

        Intent intent = getIntent();
        Fragment mainFragment = null;

        //TODO-IVAN delete after test
        /*
        String mainFragmentClassName = null;

        if (intent != null && null != intent.getExtras()) {
            mainFragmentClassName = intent.getStringExtra(EXTRA_FRAGMENT_NAME);
            mainFragmentArgs = intent.getBundleExtra(EXTRA_FRAGMENT_ARGS);
        }

        if (savedInstanceState == null) {
            //setDisplayHome(true);
            if (mainFragmentClassName != null) {
                mainFragment = Fragment.instantiate(this, mainFragmentClassName, mainFragmentArgs == null ? Bundle.EMPTY : mainFragmentArgs);
            } if (mainFragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, mainFragment)
                        .commit();
            }
        }*/

        if (savedInstanceState == null) {
            //setDisplayHome(true);
            if (intent != null && null != intent.getExtras()) {
                String mainFragmentClassName = intent.getStringExtra(EXTRA_FRAGMENT_NAME);
                mainFragmentArgs = intent.getBundleExtra(EXTRA_FRAGMENT_ARGS);
                mainFragment = Fragment.instantiate(this, mainFragmentClassName, mainFragmentArgs == null ? Bundle.EMPTY : mainFragmentArgs);
            } if (mainFragment != null) getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, mainFragment)
                    .commit();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }

    @Override
    public void displayActivity(Class<? extends Activity> activityClass){
        Intent intent = new Intent(BaseActivity.this, activityClass);
        startActivity(intent);
    }

    @Override
    public void startActivityWithFragment(String fragmentName, Bundle fragmentArgs, Class<? extends Activity> activityClass) {
        Intent intent = new Intent(BaseActivity.this, activityClass);
        intent.putExtra(EXTRA_FRAGMENT_NAME, fragmentName);
        intent.putExtra(EXTRA_FRAGMENT_ARGS, fragmentArgs);
        startActivity(intent);
    }
    @Override
    public void refreshFragment(Bundle args) {
        FragmentManager manager = getSupportFragmentManager();
        Fragment activeFragment = manager.findFragmentById(R.id.container);
        Bundle existingArgs = activeFragment.getArguments();
        manager.beginTransaction().remove(activeFragment).commit();
        manager.executePendingTransactions();
        if (null == existingArgs) {
            existingArgs = new Bundle();
        }
        existingArgs.putAll(args);
        activeFragment.setArguments(existingArgs);
        manager.beginTransaction().replace(R.id.container, activeFragment).commit();
        manager.executePendingTransactions();
    }

    @Override
    public Object performAction(Bundle args) {
        return null;
    }

    @Override
    public void displayActivityWithFragmentForResult(String fragmentName, Bundle fragmentArgs, int requestCode, Class<? extends Activity> activityClass) {
        Intent intent = new Intent(BaseActivity.this, activityClass);
        intent.putExtra(EXTRA_FRAGMENT_NAME, fragmentName);
        intent.putExtra(EXTRA_FRAGMENT_ARGS, fragmentArgs);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void addNewFragment(String fragmentName, Bundle fragmentArgs, String tag) {

        Fragment newFragment =
                Fragment.instantiate(this, fragmentName, fragmentArgs == null ? Bundle.EMPTY : fragmentArgs);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, newFragment, tag)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public Object getMvcModel() {
        return mvcModel;
    }

    @Override
    public void setMvcModel(Object mvcModel) {
        this.mvcModel = mvcModel;
    }
}
