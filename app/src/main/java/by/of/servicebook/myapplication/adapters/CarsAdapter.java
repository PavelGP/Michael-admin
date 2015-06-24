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

import butterknife.ButterKnife;
import butterknife.InjectView;
import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.db.models.Car;
import by.of.servicebook.myapplication.utils.AppConst;

/**
 * Created by Pavel on 21.04.2015.
 */
public class CarsAdapter extends BaseListAdapter<Car> {

    public CarsAdapter(Context context, int layoutId, List<Car> results) {
        super(context, layoutId, results);
    }

    @Override
    protected ViewHolder<Car> createViewHolder(final View v) {
        ViewHolder viewHolder = new ViewHolder<Car>() {
            @InjectView(R.id.tvPosition)
            TextView tvPosition;
            @InjectView(R.id.tvMake)
            TextView tvMake;
            @InjectView(R.id.tvModel)
            TextView tvModel;
            @InjectView(R.id.tvYear)
            TextView tvYear;
            @InjectView(R.id.tvRegNumber)
            TextView tvRegNumber;
            @InjectView(R.id.tvVinCode)
            TextView tvVinCode;

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

        ButterKnife.inject(viewHolder, v);

        return viewHolder;
    }
}
