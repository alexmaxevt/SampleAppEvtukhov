package ru.evtukhov.android.sampleapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {

    private List<SocialNetworkItem> socialNetworkItems;
    private LayoutInflater layoutInflater;

    Adapter(Context context, List<SocialNetworkItem> list) {
        this.socialNetworkItems = list;
        layoutInflater = LayoutInflater.from(context);
    }

    private CompoundButton.OnCheckedChangeListener checkList
            = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            socialNetworkItems.get((Integer) buttonView.getTag()).setSelect(isChecked);
        }
    };

    @Override
    public int getCount() {
        return socialNetworkItems.size();
    }

    @Override
    public Object getItem(int position) {
        return socialNetworkItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public SocialNetworkItem getSocItem(int position) {
        return socialNetworkItems.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view==null) {
            view = layoutInflater.inflate(R.layout.social_item, parent, false);

        }
        SocialNetworkItem socItem = getSocItem(position);
        ImageView icon = view.findViewById(R.id.imageSoc);
        TextView name = view.findViewById(R.id.nameSoc);
        TextView link= view.findViewById(R.id.linkSoc);
        CheckBox select = view.findViewById(R.id.checkSoc);
        link.setText(socItem.getName());
        name.setText(socItem.getLink());
        icon.setImageDrawable(socItem.getIcon());
        select.setOnCheckedChangeListener(checkList);
        select.setTag(position);
        select.setChecked(socItem.isSelect());
        return view;
    }
}
