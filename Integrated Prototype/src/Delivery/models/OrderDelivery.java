/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Delivery.models;

/**
 *
 * @author Manushi-PC
 */
public class OrderDelivery {
    
    
    private static double unitprice = 50.00;
    
    private String deliveryID;
    private String orderID;
    private String address;
    private String delDate;
    private String vehicleNo;
    private String driver;
    private double distance;
    //private double priceDistance;
    private boolean busyh;
    private boolean roadCon;
    private boolean fragile;
    //private double otherCharges;
    private String status;
    private double total;

    public OrderDelivery() {
    }
    
    

    public OrderDelivery(String deliveryID, String orderID, String delDate, String vehicleNo, String driver, double distance, double priceDistance, boolean busyh, boolean roadCon, boolean fragile, double otherCharges, String status, double total) {
        this.deliveryID = deliveryID;
        this.orderID = orderID;
        this.delDate = delDate;
        this.vehicleNo = vehicleNo;
        this.driver = driver;
        this.distance = distance;
//        this.priceDistance = priceDistance;
        this.busyh = busyh;
        this.roadCon = roadCon;
        this.fragile = fragile;
//        this.otherCharges = otherCharges;
        this.status = status;
        this.total = total;
    }

    /**
     * @return the deliveryID
     */
    public String getDeliveryID() {
        return deliveryID;
    }

    /**
     * @param deliveryID the deliveryID to set
     */
    public void setDeliveryID(String deliveryID) {
        this.deliveryID = deliveryID;
    }

    /**
     * @return the orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the delDate
     */
    public String getDelDate() {
        return delDate;
    }

    /**
     * @param delDate the delDate to set
     */
    public void setDelDate(String delDate) {
        this.delDate = delDate;
    }

    /**
     * @return the vehicleNo
     */
    public String getVehicleNo() {
        return vehicleNo;
    }

    /**
     * @param vehicleNo the vehicleNo to set
     */
    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @return the priceDistance
     */
      /**
     * @return the busyh
     */
    public boolean isBusyh() {
        return busyh;
    }

    /**
     * @param busyh the busyh to set
     */
    public void setBusyh(boolean busyh) {
        this.busyh = busyh;
    }

    /**
     * @return the roadCon
     */
    public boolean isRoadCon() {
        return roadCon;
    }

    /**
     * @param roadCon the roadCon to set
     */
    public void setRoadCon(boolean roadCon) {
        this.roadCon = roadCon;
    }

    /**
     * @return the fragile
     */
    public boolean isFragile() {
        return fragile;
    }

    /**
     * @param fragile the fragile to set
     */
    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }

    

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the unitprice
     */
    public double getUnitprice() {
        return unitprice;
    }

    /**
     * @param unitprice the unitprice to set
     */
    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
