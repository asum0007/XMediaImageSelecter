package com.asum.xmediaimageselecter.selecter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

public class CropPicture {
	private static String name;

	public static void cropImageFromUri(Activity activity, String cachePath, Uri uri, double scaleW, double scaleH, double outputW, double outputH) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("scale", true);

		if (cachePath != null) {
			name = cachePath + new Date().getTime() + ".jpg";
			File file = new File(name);
			intent.putExtra("output", Uri.fromFile(file));
		} else {
			String rootDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/XMediaImageSelector/Cache/";
			File destDir = new File(rootDir);
			if (!destDir.exists()) {
				destDir.mkdirs();
			}
			name = rootDir + new Date().getTime() + ".jpg";
			File file = new File(name);
			intent.putExtra("output", Uri.fromFile(file));
		}

		if (scaleW != -1 && scaleH != -1) {
			intent.putExtra("aspectX", (int) scaleW);
			intent.putExtra("aspectY", (int) scaleH);
		}

		intent.putExtra("outputX", (int) outputW);
		intent.putExtra("outputY", (int) outputH);
		intent.putExtra("outputFormat", "PNG");
		intent.putExtra("noFaceDetection", true);
		intent.putExtra("return-data", false);

		activity.startActivityForResult(intent, XMediaImageSelector.REQUESTCODE_CROP_IMAGE);
	}

	public static File getFile() {
		return new File(name);
	}

	public static Bitmap getBitmap() {
		try {
			FileInputStream fis = new FileInputStream(name);
			Bitmap bitmap = BitmapFactory.decodeStream(fis);
			return bitmap;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getPath() {
		return name;
	}
}
