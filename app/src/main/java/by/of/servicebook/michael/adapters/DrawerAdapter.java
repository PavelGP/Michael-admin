package by.of.servicebook.michael.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import by.of.servicebook.michael.R;

/**
 * Created by Pavel on 09.12.2014.
 */
public class DrawerAdapter extends ArrayAdapter<String> {

    private Activity context;
    private final String[] itemTitle;
    private final int[] imgid;

    public DrawerAdapter(Activity context, String[] itemTitle, int[] imgid) {
        super(context, R.layout.drawer_item, itemTitle);
        this.itemTitle=itemTitle;
        this.imgid=imgid;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.drawer_item, null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text.setText(itemTitle[position]);
        viewHolder.image.setImageResource(imgid[position]);

        return convertView;
    };

    class ViewHolder{
        ImageView image;
        TextView text;
    }
}
