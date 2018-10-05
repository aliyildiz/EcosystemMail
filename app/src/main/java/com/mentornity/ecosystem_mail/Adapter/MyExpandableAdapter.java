package com.mentornity.ecosystem_mail.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.mentornity.ecosystem_mail.CommentNodes.ChildObject;
import com.mentornity.ecosystem_mail.CommentNodes.ParentObject;
import com.mentornity.ecosystem_mail.R;

import java.util.List;

public class MyExpandableAdapter extends BaseExpandableListAdapter {

    Context context;
    List<ParentObject> parentObjects;

    public MyExpandableAdapter(Context context, List<ParentObject> parentObjects) {
        this.context = context;
        this.parentObjects = parentObjects;
    }

    @Override
    public int getGroupCount() {
        return parentObjects.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return parentObjects.get(groupPosition).childObjects.size();
    }

    @Override
    public ParentObject getGroup(int groupPosition) {
        return parentObjects.get(groupPosition);
    }

    @Override
    public ChildObject getChild(int groupPosition, int childPosition) {
        return parentObjects.get(groupPosition).childObjects.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ParentObject currentParent = parentObjects.get(groupPosition);
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.parent_row,null);
        }
        TextView txtParentName = convertView.findViewById(R.id.person_name);
        TextView txtParentMessage = convertView.findViewById(R.id.messageBody);
        txtParentName.setText(currentParent.parentName);
        txtParentMessage.setText(currentParent.parentMessage);

        ExpandableListView listView = (ExpandableListView)parent;
        listView.expandGroup(groupPosition);


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ParentObject currentParent = getGroup(groupPosition);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ChildObject currentChild = getChild(groupPosition,childPosition);
        convertView = inflater.inflate(R.layout.child_row,null);
        TextView txtChildName = convertView.findViewById(R.id.Cperson_name);
        TextView txtChildMessage = convertView.findViewById(R.id.CmessageBody);
        txtChildName.setText(currentChild.childName);
        txtChildMessage.setText(currentChild.childMessage);



        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
