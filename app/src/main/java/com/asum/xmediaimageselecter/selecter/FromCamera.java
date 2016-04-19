package com.asum.xmediaimageselecter.selecter;

import java.io.File;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

/**
 * 通过拍照
 * 
 * @author Asum
 * 
 */
public class FromCamera {
	private static String name;
	public static Uri uri;

	public static void takeAPicture(Activity activity) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		String rootDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/XMediaImageSelector/CameraCache/";
		File destDir = new File(rootDir);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		name = rootDir + new Date().getTime() + ".jpg";
		File file = new File(name);
		uri = Uri.fromFile(file);

		intent.putExtra("output", Uri.fromFile(file));// 输出到文件
		activity.startActivityForResult(intent, XMediaImageSelector.REQUESTCODE_CAMARE_IMAGE);
	}
}
