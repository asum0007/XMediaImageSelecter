package com.asum.xmediaimageselecter.selecter;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

public class FromPicture {
	public static void selectPicture(Activity activity) {
		Intent intent = new Intent(Intent.ACTION_PICK, null);
		intent.setType("image/*");
		intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		activity.startActivityForResult(intent, XMediaImageSelector.REQUESTCODE_GALLERY_IMAGE);
	}
}
