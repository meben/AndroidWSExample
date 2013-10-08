package com.aceart.formationwebservice;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aceart.formationwebservice.model.Point;

public class PointAdapter extends ArrayAdapter<Point> {

	public PointAdapter(Context context,int textViewResourceId, List<Point> objects) {
		super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;

		Point point = getItem(position);

		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, null);

			viewHolder.address = (TextView) convertView.findViewById(android.R.id.text1);
			viewHolder.subAdress = (TextView) convertView.findViewById(android.R.id.text2);

			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.address.setText(point.getAddress());
		viewHolder.subAdress.setText(String.format("%s %s", point.getPostalCode(), point.getCity()));

		return convertView;
	}

	private class ViewHolder {
		TextView address;
		TextView subAdress;
	}

}
