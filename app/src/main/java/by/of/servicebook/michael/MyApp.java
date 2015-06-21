package by.of.servicebook.michael;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

import by.of.servicebook.michael.parse.models.ParseCar;
import by.of.servicebook.michael.parse.models.ParseDetail;
import by.of.servicebook.michael.parse.models.ParseJob;
import by.of.servicebook.michael.parse.models.ParseRecord;
import by.of.servicebook.michael.parse.models.ParseStatistic;

/**
 * Created by Pavel on 10.12.2014.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        //register parse.com subclasses
        ParseObject.registerSubclass(ParseCar.class);
        ParseObject.registerSubclass(ParseRecord.class);
        ParseObject.registerSubclass(ParseJob.class);
        ParseObject.registerSubclass(ParseDetail.class);
        ParseObject.registerSubclass(ParseStatistic.class);
        Parse.initialize(this, "ZjTI65G77ck4IneaorWJTVdZnUz6YBUTkC8GOpxh", "h9gPunO5xfdcEF4yVQXoRGhjkRaUzEc550GCZvi1");
    }
}