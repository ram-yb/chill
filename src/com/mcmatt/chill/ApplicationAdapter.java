package com.mcmatt.chill;

import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;

public class ApplicationAdapter extends ArrayAdapter<Application> {
	Context context;
	int layoutResourceID;
	Application data[];

	public ApplicationAdapter(Context context, int layoutResourceID, Application[] data) {
		super(context, layoutResourceID, data);
		this.context = context;
		this.layoutResourceID = layoutResourceID;
		this.data = data;
	}

	@Override public View getView(int pos, View convert, ViewGroup parent) {
		View row = convert;
		ApplicationHolder holder = null;
		if ( row == null ) {
			LayoutInflater inflater = ( (Activity) context ).getLayoutInflater();
			row = inflater.inflate(layoutResourceID, parent, false);

			holder = new ApplicationHolder();
			LinearLayout texts = (LinearLayout) row.findViewById(R.id.texts);
			holder.icon = (ImageView) row.findViewById(R.id.icon);
			holder.title = (TextView) texts.findViewById(R.id.title);
			holder._package = (TextView) texts.findViewById(R.id.pkg);
			holder.button = (Button) row.findViewById(R.id.btn);

			row.setTag(holder);
		} else {
			holder = (ApplicationHolder) row.getTag();
		}

		Application app = data[pos];
		holder.icon.setImageDrawable(app.icon);
		holder.title.setText(app.title);
		holder._package.setText(app._package);
		holder.button.setText(app.frozen ? "Unfreeze" : "Freeze");
		holder.button.setTag(app);
		holder.button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Application app = (Application) v.getTag();
				if ( app.frozen ) {
					app.Unfreeze();
					( (Button) v ).setText("Freeze");
				} else {
					app.Freeze();
					( (Button) v ).setText("Unfreeze");
				}
			}
		});

		return row;
	}

	static class ApplicationHolder {
		Application app;
		ImageView icon;
		TextView title, _package;
		Button button;
	}
}
