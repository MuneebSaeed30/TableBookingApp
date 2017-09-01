package model;

/**
 * Created by MuneebPC on 8/23/2017.
 */
public class CartItems {

     public String ItemName, ItemQuantity, ItemPrice;
    // public ArrayList<CartItems> itemOnCart= new ArrayList<>();

    public CartItems() {
    }

    public CartItems(String itemName, String itemQuantity, String itemPrice) {
        ItemName = itemName;
        ItemQuantity = itemQuantity;
        ItemPrice = itemPrice;
    }

    public String getItemName() {return ItemName;}

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemQuantity() {
        return ItemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        ItemQuantity = itemQuantity;
    }

    public String getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        ItemPrice = itemPrice;
    }
}
