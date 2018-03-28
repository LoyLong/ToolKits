package org.loy.testclone;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

public class CloneParent implements Serializable, Cloneable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    private String classKey;

	public String getClassKey() {
		return classKey;
	}

	public void setClassKey(String classKey) {
		this.classKey = classKey;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("classKey", this.classKey)
				.toString();
	}
	
	public Object getClone() throws CloneNotSupportedException{
		return this.clone();
	}
}
