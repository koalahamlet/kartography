package mikecanco.de.kartography;

import android.app.Application;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;

public class KartographyApplication extends Application{

	private static Picasso singletonPicasso;

	public static Picasso getSingletonPicasso() {
		return singletonPicasso;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		Cache cache = new Cache(new File(this.getFilesDir(), "okhttp"), 75 * 1024 * 1024);
		singletonPicasso = new Picasso.Builder(this)
				.downloader(new OkHttpDownloader(new OkHttpClient().setCache(cache))).build();
		singletonPicasso.setIndicatorsEnabled(false);

	}
}
