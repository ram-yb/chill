package com.mcmatt.chill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.widget.*;
import android.content.pm.*;

public class MainActivity extends Activity {
	private ListView mainListView;

	public final static String EXTRA_MESSAGE = "com.mcmatt.chill.MESSAGE";

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		PackageManager pm = getPackageManager();
		List<ApplicationInfo> installed_apps = pm.getInstalledApplications(0);
		Collections.sort(installed_apps, new ApplicationInfo.DisplayNameComparator(pm));
		ArrayList<Application> apps = new ArrayList<Application>();
		for ( int i = 0; i < installed_apps.size(); i++ ) {
			Application app = new Application(this, installed_apps.get(i));
			if ( !app.system ) apps.add(app);
		}
		Application[] apps_array = apps.toArray(new Application[apps.size()]);

		ApplicationAdapter adapter = new ApplicationAdapter(this, R.layout.list_view_row, apps_array);

		mainListView = (ListView) findViewById(R.id.mainListView);

		// View header =
		// (View)getLayoutInflater().inflate(R.layout.list_view_header, null);
		// mainListView.addHeaderView(header);
		mainListView.setAdapter(adapter);
	}

	void toggleApp(View view) {
		
	}

	/*
	@Override public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void btnFreeze(View view) {
		this.freeze("com.google.zxing.client.android");
	}

	public void btnUnfreeze(View view) {
		this.unfreeze("com.google.zxing.client.android");
	}
*/

	public boolean Run(String command) {
		try {
			Process p = Runtime.getRuntime().exec(new String[] { "su", "-c", "/system/bin/sh" });
			p.getOutputStream().write(( command + "\n" ).getBytes());
			p.getOutputStream().write("exit\n".getBytes());
//			p.waitFor();

//			if ( p.exitValue() == 1 ) { throw new Exception("This application requires root access."); }
		} catch ( Exception e ) {
			return false;
			// alert(e.getMessage());
		}

		return true;
	}

/*
	void freeze(String name) {
		this.run("pm disable " + name);
	}

	void unfreeze(String name) {
		this.run("pm enable " + name);
	}
*/
}
