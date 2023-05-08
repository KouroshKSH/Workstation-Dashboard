package sample;

public class WarehouseProductsClass
{
    private String nameOfProduct, pricePerProduct,
            quantityOfProdcut, dateOfLastEntry, dateOfLastSelling,
            costPerProduct, itemsSoldPerProduct;

    public WarehouseProductsClass (String nameOfProduct, String pricePerProduct, String quantityOfProdcut,
                                   String dateOfLastEntry, String dateOfLastSelling, String costPerProduct,
                                   String itemsSoldPerProduct)
    {
        this.nameOfProduct = nameOfProduct;
        this.pricePerProduct = pricePerProduct;
        this.quantityOfProdcut = quantityOfProdcut;
        this.dateOfLastEntry = dateOfLastEntry;
        this.dateOfLastSelling = dateOfLastSelling;
        this.costPerProduct = costPerProduct;
        this.itemsSoldPerProduct = itemsSoldPerProduct;
    }

    public String getNameOfProduct ()
    {
        return nameOfProduct;
    }

    public void setNameOfProduct (String nameOfProduct)
    {
        this.nameOfProduct = nameOfProduct;
    }

    public String getPricePerProduct ()
    {
        return pricePerProduct;
    }

    public void setPricePerProduct (String pricePerProduct)
    {
        this.pricePerProduct = pricePerProduct;
    }

    public String  getQuantityOfProdcut ()
    {
        return quantityOfProdcut;
    }

    public void setQuantityOfProdcut (String quantityOfProdcut)
    {
        this.quantityOfProdcut = quantityOfProdcut;
    }

    public String getDateOfLastEntry ()
    {
        return dateOfLastEntry;
    }

    public void setDateOfLastEntry (String dateOfLastEntry)
    {
        this.dateOfLastEntry = dateOfLastEntry;
    }

    public String getDateOfLastSelling ()
    {
        return dateOfLastSelling;
    }

    public void setDateOfLastSelling (String dateOfLastSelling)
    {
        this.dateOfLastSelling = dateOfLastSelling;
    }

    public String  getCostPerProduct ()
    {
        return costPerProduct;
    }

    public void setCostPerProduct (String costPerProduct)
    {
        this.costPerProduct = costPerProduct;
    }

    public String getItemsSoldPerProduct ()
    {
        return itemsSoldPerProduct;
    }

    public void setItemsSoldPerProduct (String itemsSoldPerProduct)
    {
        this.itemsSoldPerProduct = itemsSoldPerProduct;
    }
}
