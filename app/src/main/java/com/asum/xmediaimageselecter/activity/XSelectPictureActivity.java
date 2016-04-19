package com.asum.xmediaimageselecter.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.asum.xmediaimageselecter.tools.FileTools;
import com.asum.xmediaimageselecter.vo.FolderVO;
import com.asum.xmediaimageselecter.vo.ImageVO;

import java.util.ArrayList;

/**
 * 选择图片的Activity
 * 
 * @author Asum
 *
 */
public abstract class XSelectPictureActivity extends FragmentActivity implements XSelectPictureActivityCallBack {
	public static final String[] IMAGE_PROJECTION = { MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.Media.MIME_TYPE,
			MediaStore.Images.Media.SIZE, MediaStore.Images.Media._ID };

	/**
	 * 扫描图片的类型，0代表全部，1代表指定路径，如果是1，保证path不为空，注：修改类型需在super之前修改
	 */
	protected static int LOADER_TYPE = 0;

	/**
	 * 所有的图片文件
	 */
	protected ArrayList<ImageVO> allImageVOs;

	/**
	 * 所有包含图片的文件夹
	 */
	protected ArrayList<FolderVO> allFolderVOs;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializeDatas();
	}

	private void initializeDatas() {
		this.getSupportLoaderManager().initLoader(LOADER_TYPE, null, new LoaderCallbacks<Cursor>() {
			public Loader<Cursor> onCreateLoader(int id, Bundle args) {
				if (id == 0) {
					CursorLoader cursorLoader = new CursorLoader(XSelectPictureActivity.this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION,
							IMAGE_PROJECTION[4] + ">0 AND " + IMAGE_PROJECTION[3] + "=? OR " + IMAGE_PROJECTION[3] + "=? ", new String[] { "image/jpeg", "image/png" }, IMAGE_PROJECTION[2] + " DESC");
					return cursorLoader;
				} else if (id == 1) {
					CursorLoader cursorLoader = new CursorLoader(XSelectPictureActivity.this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION,
							IMAGE_PROJECTION[4] + ">0 AND " + IMAGE_PROJECTION[0] + " like '%" + args.getString("path") + "%'", null, IMAGE_PROJECTION[2] + " DESC");
					return cursorLoader;
				}

				return null;
			}

			public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
				FileTools.judgeCursor(loader, data);

				allImageVOs = FileTools.getAllImageVOs();
				allFolderVOs = FileTools.getAllFolderVOs();
				
				judgeFinish();
			}

			public void onLoaderReset(Loader<Cursor> arg0) {
			}
		});
	}
}
