package by.of.servicebook.myapplication.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseUser;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import by.of.servicebook.myapplication.R;

/**
 * Created by p.gulevich on 24.06.2015.
 */
public class UsersAdapter extends BaseListAdapter<ParseUser> {
    public UsersAdapter(Context context, int layoutId, List<ParseUser> results) {
        super(context, layoutId, results);
    }

    @Override
    protected ViewHolder<ParseUser> createViewHolder(final View v) {

        ViewHolder<ParseUser> viewHolder = new ViewHolder<ParseUser>() {
            TextView tvEmail =(TextView) v.findViewById(R.id.tvEmail);

            @Override
            protected void hold(ParseUser item) {
                tvEmail.setText(item.getUsername());
            }
        };

        ButterKnife.inject(viewHolder, v);

        return viewHolder;
    }
}
