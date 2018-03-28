package org.loy.testclone;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ChildClassOne extends CloneParent {
	
	private static final long serialVersionUID = 1L;

	String classOneAtt;

	DataClass data;
	
	public String getClassOneAtt() {
		return classOneAtt;
	}

	public void setClassOneAtt(String classOneAtt) {
		this.classOneAtt = classOneAtt;
	}

	public DataClass getData() {
		return data;
	}

	public void setData(DataClass data) {
		this.data = data;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("data", this.data)
				.append("classOneAtt", this.classOneAtt)
				.append("classKey", this.getClassKey()).toString();
	}
	
}
