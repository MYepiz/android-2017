package com.example.marty.pocketbolt;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.HashMap;

import android.widget.TextView;

public class expandiblelist_adapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<String> expandableListTitle;
    private HashMap<String, ArrayList<String>> expandableListDetail;
    DBaccess databaseAccess;

    public expandiblelist_adapter(Context context, ArrayList<String> expandableListTitle,
                                       HashMap<String, ArrayList<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_shop_layout, null);
        }

        TextView itemName = (TextView) convertView.findViewById(R.id.itemName);
        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);

        itemName.setText((String)getGroup(listPosition));
        itemName.setTextColor(Color.BLACK);
        itemName.setPadding(100,35,4,0);
        itemName.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        icon.setImageDrawable(null);

        if (listPosition %2 == 0) {
            convertView.setBackgroundColor(convertView.getResources().getColor(R.color.colorPrimaryDark));
        }else{
            convertView.setBackgroundColor(convertView.getResources().getColor(R.color.colorPrimary));
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String expandedListText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_shop_layout, null);
        }

        TextView itemName = (TextView) convertView.findViewById(R.id.itemName);
        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);

        itemName.setText(expandedListText);

        databaseAccess = DBaccess.getInstance(convertView.getContext());
        databaseAccess.open();
        Boolean adquired =  databaseAccess.getIsItemAdquired(expandedListText);
        if (adquired){
            icon.setImageResource(R.mipmap.ic_check_circle_black_36dp);
        }else{
            icon.setImageResource(R.mipmap.ic_highlight_off_black_36dp);
        }
        databaseAccess.close();
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}


