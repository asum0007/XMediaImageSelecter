package com.asum.xmediaimageselecter.vo;

import java.util.ArrayList;
import java.util.List;

public class FolderVO {
	public String name;
	public String path;
	public ImageVO cover;
	public List<ImageVO> images = new ArrayList<ImageVO>();
}
