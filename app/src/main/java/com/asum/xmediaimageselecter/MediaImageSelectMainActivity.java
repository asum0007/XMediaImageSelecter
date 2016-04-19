package com.asum.xmediaimageselecter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.asum.xmediaimageselecter.activity.XSelectPictureActivity;
import com.asum.xmediaimageselecter.selecter.XMediaImageSelector;
import com.asum.xmediaimageselecter.selecter.XMediaImageSelectorCallBack;

import java.io.File;

public class MediaImageSelectMainActivity extends AppCompatActivity {
    private ImageView imageView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_image_select_main);
        imageView = (ImageView) findViewById(R.id.activity_main_imageview);

        findViewById(R.id.activity_main_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                XMediaImageSelector.fromPicture(MediaImageSelectMainActivity.this, null, true, 1, 1, 200, 200, new XMediaImageSelectorCallBack() {
                    public void complete(boolean isSuccess) {
                        if (isSuccess) {
                            imageView.setImageBitmap(XMediaImageSelector.getBitmap());
                            File file = XMediaImageSelector.getFile();
                            Log.i("XJW", file.getPath() + "：" + file.length());
                        }
                    }
                });
            }
        });

        findViewById(R.id.activity_main_button2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // XMediaImageSelector.fromCamera(MainActivity.this, null, true, 1, 1, 200, 200, new XMediaImageSelectorCallBack() {
                // public void complete(boolean isSuccess) {
                // if (isSuccess) {
                // imageView.setImageBitmap(XMediaImageSelector.getBitmap());
                // File file = XMediaImageSelector.getFile();
                // Log.i("XJW", file.getPath() + "：" + file.length());
                // }
                // }
                // });

                Intent intent = new Intent(MediaImageSelectMainActivity.this, XSelectPictureActivity.class);
                MediaImageSelectMainActivity.this.startActivity(intent);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        XMediaImageSelector.disposeResult(this, requestCode, resultCode, data);
    }
}
