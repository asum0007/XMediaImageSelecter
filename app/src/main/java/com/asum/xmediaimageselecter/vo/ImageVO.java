package com.asum.xmediaimageselecter.vo;

public class ImageVO {
	public String path;
	public String name;
	public long time;
	public boolean isSelect;

	public ImageVO() {

	}

	public ImageVO(String path, String name, long time) {
		this.path = path;
		this.name = name;
		this.time = time;
	}

	@Override
	public boolean equals(Object o) {
		try {
			ImageVO other = (ImageVO) o;
			return this.path.equalsIgnoreCase(other.path);
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return super.equals(o);
	}

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public long getTime() {
		return time;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}
}
