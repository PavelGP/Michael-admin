package by.of.servicebook.myapplication.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.db.models.Car;
import by.of.servicebook.myapplication.utils.AppConst;

/**
 * Created by Pavel on 21.04.2015.
 */
public class CarsAdapter extends BaseListAdapter<Car> {
    LayoutInflater inflater;

    public CarsAdapter(Context context, int layoutId, List<Car> results) {
        super(context, layoutId, results);
    }

    @Override
    protected ViewHolder<Car> createViewHolder(final View v) {
        return new ViewHolder<Car>() {
            TextView tvPosition = (TextView) v.findViewById(R.id.tvPosition);
            TextView tvMake = (TextView) v.findViewById(R.id.tvMake);
            TextView tvModel = (TextView) v.findViewById(R.id.tvModel);
            TextView tvYear = (TextView) v.findViewById(R.id.tvYear);
            TextView tvRegNumber = (TextView) v.findViewById(R.id.tvRegNumber);
            TextView tvVinCode = (TextView) v.findViewById(R.id.tvVinCode);

            @Override
            protected void hold(Car item) {
                tvPosition.setText(getPosition(item) + 1);
                tvMake.setText(item.make);
                tvModel.setText(item.model);
                tvYear.setText("" + item.year);
                tvRegNumber.setText(item.regNumber);
                tvVinCode.setText(item.vinCode);
            }
        };
    }
}
