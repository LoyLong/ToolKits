package org.loy.testclone;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ChildClassTwo {

	String classTwoAtt;

	DataClass data;
	
	public String getClassOneAtt() {
		return classTwoAtt;
	}

	public void setClassOneAtt(String classTwoAtt) {
		this.classTwoAtt = classTwoAtt;
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
				.append("classTwoAtt", this.classTwoAtt).toString();
	}
}
