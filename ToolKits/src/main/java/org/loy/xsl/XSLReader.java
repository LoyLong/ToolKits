
package org.loy.xsl;


/**
 * <p>
 * <b> Insert description of the class's responsibility/role. </b>
 * </p>
 */
public abstract class XSLReader {
	
	public boolean combinedAcctType = false;
	public int tab = 0;
    public int startRow = 2;
    public short acctTypeCodeCellIndex = 4;
    public short acctProductTypeCodeCellIndex = 5;
    public short acctDescIndex = 2;
    public short groupMemberCellIndex = 3;
    public String filePatch;
    
    abstract public void readXSL();
    
}
