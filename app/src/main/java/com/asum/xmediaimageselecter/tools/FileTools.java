package com.asum.xmediaimageselecter.tools;

import java.io.File;
import java.util.ArrayList;

import com.asum.xmediaimageselecter.activity.XSelectPictureActivity;
import com.asum.xmediaimageselecter.vo.FolderVO;
import com.asum.xmediaimageselecter.vo.ImageVO;

import android.database.Cursor;
import android.support.v4.content.Loader;
import android.text.TextUtils;

/**
 * 文件操作类
 * 
 * @author Asum
 *
 */
public class FileTools {
	private static ArrayList<ImageVO> allImageVOs;
	private static ArrayList<FolderVO> allFolderVOs;

	public static void judgeCursor(Loader<Cursor> loader, Cursor data) {
		if (data != null) {
			if (data.getCount() > 0) {
				allImageVOs=new ArrayList<ImageVO>();
				allFolderVOs=new ArrayList<FolderVO>();
				
				ArrayList<ImageVO> images = new ArrayList<ImageVO>();
				data.moveToFirst();
				do {
					String path = data.getString(data.getColumnIndexOrThrow(XSelectPictureActivity.IMAGE_PROJECTION[0]));
					String name = data.getString(data.getColumnIndexOrThrow(XSelectPictureActivity.IMAGE_PROJECTION[1]));
					long dateTime = data.getLong(data.getColumnIndexOrThrow(XSelectPictureActivity.IMAGE_PROJECTION[2]));

					ImageVO imageVO = null;
					if (fileExist(path)) {
						imageVO = new ImageVO(path, name, dateTime);
						images.add(imageVO);

						allImageVOs.add(imageVO);
					}

					// 获取文件夹名称
					File folderFile = new File(path).getParentFile();
					if (folderFile != null && folderFile.exists()) {
						String fp = folderFile.getAbsolutePath();
						FolderVO existFolderVO = getFolderByPath(fp);
						if (existFolderVO == null) {
							FolderVO newFolderVO = new FolderVO();
							newFolderVO.name = folderFile.getName();
							newFolderVO.path = fp;
							newFolderVO.cover = imageVO;
							newFolderVO.images.add(imageVO);
							allFolderVOs.add(newFolderVO);
						} else {
							existFolderVO.images.add(imageVO);
						}
					}

				} while (data.moveToNext());
			}
		}
	}

	public static ArrayList<ImageVO> getAllImageVOs() {
		return allImageVOs;
	}

	public static ArrayList<FolderVO> getAllFolderVOs() {
		return allFolderVOs;
	}

	private static FolderVO getFolderByPath(String path) {
		if (allFolderVOs != null) {
			for (FolderVO folderVO : allFolderVOs) {
				if (TextUtils.equals(folderVO.path, path)) {
					return folderVO;
				}
			}
		} else {
			allFolderVOs = new ArrayList<FolderVO>();
		}
		return null;
	}

	public static boolean fileExist(String path) {
		if (!TextUtils.isEmpty(path)) {
			return new File(path).exists();
		}
		return false;
	}
}
