package com.slipper.SpringWebApp.utils.discount;

public class DiscountFactory {


    public Discount getDiscount(DiscountTypes type) {
        Discount toReturn = null;
        switch (type) {
            case LOW:
                toReturn = new LowDiscount();
                break;
            case MIDDLE:
                toReturn = new MiddleDiscount();
                break;
            case HIGH:
                toReturn = new HighDiscount();
                break;
            default:
                throw new IllegalArgumentException("Wrong discount type:" + type);
        }
        return toReturn;
    }

}
