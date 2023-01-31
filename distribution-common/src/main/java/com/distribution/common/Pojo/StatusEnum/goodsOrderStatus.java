package com.distribution.common.Pojo.StatusEnum;


public enum goodsOrderStatus {
    InOrderClose(-1, "取消"),InOrderCreate(0, "创建"),
    InSupplierConfirm(1, "确认"),InSupplierSend(2, "发货"),
    InDealerReceive(3, "收货");

    private int status;

    private String description;

    goodsOrderStatus(int s, String description){
        this.status = s;
        this.description = description;
    }

    public int getStatus(){
        return status;
    }

    public String getDescription() {
        return description;
    }
}
