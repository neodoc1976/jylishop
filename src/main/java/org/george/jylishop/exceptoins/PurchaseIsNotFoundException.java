package org.george.jylishop.exceptoins;

/**
 * Created by Yulya on 28.01.2017.
 */
public class PurchaseIsNotFoundException extends JuliShopException {

    private int purchaseTransactionId;

    public int getPurchaseTransactionId() {
        return purchaseTransactionId;
    }
    public PurchaseIsNotFoundException (int id){
        purchaseTransactionId=id;
    }
}
