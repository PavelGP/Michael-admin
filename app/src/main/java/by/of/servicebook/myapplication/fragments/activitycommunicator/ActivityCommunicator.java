package by.of.servicebook.myapplication.fragments.activitycommunicator;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by s.ankuda on 5/7/2015.
 */
public interface ActivityCommunicator {
    public void startActivityWithFragment(String fragmentName, Bundle fragmentArgs, Class<? extends Activity> activityClass);
    public void refreshFragment(Bundle args);
    public Object performAction(Bundle args);
    public void displayActivityWithFragmentForResult(String fragmentName, Bundle fragmentArgs, int requestCode, Class<? extends Activity> activityClass);
    public void addNewFragment(String fragmentName, Bundle fragmentArgs, String TAG);
    public void displayActivity(Class<? extends Activity> activityClass);
    public Object getMvcModel();
    public void setMvcModel(Object model);
}
