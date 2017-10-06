package models;

/**
 * เป็นคลาสที่เอาไว้เริ่มการขายใหม่ และจบการขาน จะจัดการ productCatalog และ Sale
 */
public class Cashier {
    private ProductCatalog productCatalog;
    private Sale sale;

    public Cashier(){
        productCatalog = ProductCatalog.getInstacnce();

    }

    /**
     * ไว้เริ่มการขายใหม่
     */
    public void startSale(){
        sale = new Sale();

    }

    /**
     * @param prd สินค้าที่ลูกค้าจะซื้อ
     * @param qty จำนวนสินค้า
     */
    public void addItem(Product prd,int qty){
        sale.makeNewLine(prd,qty);
    }

    /**
     * ไว้จบการขาย
     */
    public void endSale(){
        sale = null;

    }

    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    public Sale getSale() {
        return sale;
    }
}
