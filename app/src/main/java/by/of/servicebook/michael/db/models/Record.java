package by.of.servicebook.michael.db.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Pavel on 09.12.2014.
 */

@Table(name = "records")
public class Record extends Model {

    @Column(name = "key")
    public int key;

    @Column(name = "car_id")
    public int car_id;

    @Column(name = "date")
    public String date;

    @Column(name = "mileage")
    public int mileage;

    @Column(name = "service_name")
    public String serviceName;

    @Column(name = "price")
    public long price;

    @Column(name = "comment")
    public String comment;

    public List<Job> jobList;

    public Record(){
        super();
    }

    public Record(int id, int car_id, String date, int mileage, String serviceName, long price, String comment) {
        this.key = id;
        this.car_id = car_id;
        this.date = date;
        this.mileage = mileage;
        this.serviceName = serviceName;
        this.price = price;
        this.comment = comment;
    }

    public Record(JSONObject jsonObject) {
        super();
        this.key = jsonObject.optInt("id");
        this.car_id = jsonObject.optInt("car_id");
        this.date = jsonObject.optString("date");
        this.mileage = jsonObject.optInt("mileage");
        this.serviceName = jsonObject.optString("service_name");
        this.price = jsonObject.optLong("price");
        this.comment = jsonObject.optString("comment");
    }
}

