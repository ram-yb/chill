package com.mcmatt.chill;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

public class Application {
	private ApplicationInfo _info;
	private MainActivity _activity;

	public Drawable icon;
	public String _package, title;
	public boolean frozen, system;

	public Application() {
		super();
	}

	public Application(MainActivity activity, ApplicationInfo app) {
		super();

		this._activity = activity;
		this._info = app;

		CharSequence title = activity.getPackageManager().getApplicationLabel(app);
		this.icon = activity.getPackageManager().getApplicationIcon(app);
		this.title = title.toString();
		this._package = app.packageName;

		this.frozen = !app.enabled;
		this.system = ( ( app.flags & ApplicationInfo.FLAG_SYSTEM ) == 1 );
	}

	private boolean run(String command) {
		return _activity.Run(command);
	}

	public boolean Freeze() {
		if ( frozen ) return true;
		boolean success = run("pm disable " + this._package);
		frozen = true;
		return success;
	}

	public boolean Unfreeze() {
		if ( !frozen ) return true;
		boolean success = run("pm enable " + this._package);
		frozen = false;
		return success;
	}
}
