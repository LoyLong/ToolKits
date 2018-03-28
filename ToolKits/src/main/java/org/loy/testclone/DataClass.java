package org.loy.testclone;

import org.apache.commons.lang.builder.ToStringBuilder;

public class DataClass {
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("data", this.data).toString();
	}
}
