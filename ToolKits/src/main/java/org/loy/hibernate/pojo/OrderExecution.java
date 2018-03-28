package org.loy.hibernate.pojo;

import java.sql.Timestamp;

public class OrderExecution {

    private int ordNum;

    private Timestamp ordDate;

    private int execNum;

    private Timestamp execDate;

    private String pic;

    private int execQty;

    private double price;

    private String status;

    private Timestamp updateTime;

    private String trans;

    private String imReleased;

    private String excludeInPos;

    private double ledgerBalInUSD;

    private double marginValueInUSD;

    private double preReleaseMargin;

    private int relExecNum;

    private Timestamp relExecDate;

    public int getOrdNum() {
        return ordNum;
    }

    public void setOrdNum(int ordNum) {
        this.ordNum = ordNum;
    }

    public Timestamp getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(Timestamp ordDate) {
        this.ordDate = ordDate;
    }

    public int getExecNum() {
        return execNum;
    }

    public void setExecNum(int execNum) {
        this.execNum = execNum;
    }

    public Timestamp getExecDate() {
        return execDate;
    }

    public void setExecDate(Timestamp execDate) {
        this.execDate = execDate;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getExecQty() {
        return execQty;
    }

    public void setExecQty(int execQty) {
        this.execQty = execQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public String getImReleased() {
        return imReleased;
    }

    public void setImReleased(String imReleased) {
        this.imReleased = imReleased;
    }

    public String getExcludeInPos() {
        return excludeInPos;
    }

    public void setExcludeInPos(String excludeInPos) {
        this.excludeInPos = excludeInPos;
    }

    public double getLedgerBalInUSD() {
        return ledgerBalInUSD;
    }

    public void setLedgerBalInUSD(double ledgerBalInUSD) {
        this.ledgerBalInUSD = ledgerBalInUSD;
    }

    public double getMarginValueInUSD() {
        return marginValueInUSD;
    }

    public void setMarginValueInUSD(double marginValueInUSD) {
        this.marginValueInUSD = marginValueInUSD;
    }

    public double getPreReleaseMargin() {
        return preReleaseMargin;
    }

    public void setPreReleaseMargin(double preReleaseMargin) {
        this.preReleaseMargin = preReleaseMargin;
    }

    public int getRelExecNum() {
        return relExecNum;
    }

    public void setRelExecNum(int relExecNum) {
        this.relExecNum = relExecNum;
    }

    public Timestamp getRelExecDate() {
        return relExecDate;
    }

    public void setRelExecDate(Timestamp relExecDate) {
        this.relExecDate = relExecDate;
    }

    @Override
    public String toString() {
        return "OrderExecution [ordNum=" + ordNum + ", ordDate=" + ordDate + ", execNum=" + execNum + ", execDate=" + execDate
            + ", pic=" + pic + ", execQty=" + execQty + ", price=" + price + ", status=" + status + ", updateTime=" + updateTime
            + ", trans=" + trans + ", imReleased=" + imReleased + ", excludeInPos=" + excludeInPos + ", ledgerBalInUSD="
            + ledgerBalInUSD + ", marginValueInUSD=" + marginValueInUSD + ", preReleaseMargin=" + preReleaseMargin
            + ", relExecNum=" + relExecNum + ", relExecDate=" + relExecDate + "]";
    }

}
