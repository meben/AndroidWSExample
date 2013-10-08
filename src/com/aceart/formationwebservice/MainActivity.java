package com.aceart.formationwebservice;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.aceart.formationwebservice.model.Point;
import com.aceart.formationwebservice.service.WebService;

public class MainActivity extends Activity implements OnItemClickListener {

	ListView listViewData;
	PointAdapter pointAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listViewData = (ListView) findViewById(R.id.listViewData);

		getData();
		
		listViewData.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void getData() {
		new AsyncTask<Void, Void, List<Point>>() {

			@Override
			protected List<Point> doInBackground(Void... params) {
				WebService webService = new WebService();

				List<Point> liste = webService.getPoints();

				if (liste != null) {
					return liste;
				}

				return new ArrayList<Point>();
			};

			protected void onPostExecute(java.util.List<Point> result) {

				pointAdapter = new PointAdapter(getBaseContext(), android.R.layout.simple_list_item_2, result);

				listViewData.setAdapter(pointAdapter);
			};

		}.execute();
	}

	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		if(pointAdapter != null) {
			Point point = pointAdapter.getItem(position);
			
			if(point != null) {
				
				String showText = String.format("Id : %s | Lat : %s | Long : %s", point.getID(), point.getLatitude(), point.getLongitude());
				
				Toast.makeText(getBaseContext(), showText, Toast.LENGTH_SHORT).show();
			}
			
		}
	}
}
