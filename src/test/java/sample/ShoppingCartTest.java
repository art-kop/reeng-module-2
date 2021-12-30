package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @Test
    void main() {
    }

    @Test
    void addItem() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Blue Bowl", 5, 5, ShoppingCart.ItemType.NEW);
        cart.addItem("Scissors", 20.00, 4, ShoppingCart.ItemType.SECOND_FREE);
        cart.addItem("Handcuffs", 17.20, 1, ShoppingCart.ItemType.SALE);
        cart.addItem("Combat helicopter", 30.50, 500, ShoppingCart.ItemType.REGULAR);

        System.out.println( cart.formatTicket());

        String expected = "# Item               Price Quan. Discount    Total \n" +
                "-------------------------------------------------\n" +
                "1 Blue Bowl          $5.00     5        -   $25.00 \n" +
                "2 Scissors          $20.00     4      50%   $40.00 \n" +
                "3 Handcuffs         $17.20     1      70%    $5.16 \n" +
                "4 Combat helicopter $30.50   500      50% $7625.00 \n" +
                "-------------------------------------------------\n" +
                "4                                         $7695.16 ";

        assertEquals(1, 1);
    }

    @Test
    public void formatTicketCheck() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Blue Bowl", 5, 5, ShoppingCart.ItemType.NEW);
        cart.addItem("Scissors", 20.00, 4, ShoppingCart.ItemType.SECOND_FREE);
        cart.addItem("Handcuffs", 17.20, 1, ShoppingCart.ItemType.SALE);
        cart.addItem("Combat helicopter", 30.50, 500, ShoppingCart.ItemType.REGULAR);

        String expected = "# Item               Price Quan. Discount    Total \n" +
                "--------------------------------------------------\n" +
                "1 Blue Bowl          $5.00     5        -   $25.00 \n" +
                "2 Scissors          $20.00     4      50%   $40.00 \n" +
                "3 Handcuffs         $17.20     1      70%    $5.16 \n" +
                "4 Combat helicopter $30.50   500      50% $7625.00 \n" +
                "--------------------------------------------------\n" +
                "4                                         $7695.16 ";

        assertEquals(expected, cart.formatTicket());
    }

    @Test
    void appendFormattedCheck() {
        StringBuilder sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, "Word", 0, 3);
        assertEquals("Wor ", sb.toString());

        sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, "Word", 0, 10);
        assertEquals("   Word    ", sb.toString());

        sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, "Word", 0, 15);
        assertEquals("     Word       ", sb.toString());

        sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, "Word", 1, 15);
        assertEquals("           Word ", sb.toString());

        sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, "Word", -1, 15);
        assertEquals("Word            ", sb.toString());
    }

    @Test
    void calculateDiscountCheck() {
        assertEquals(0, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.REGULAR, 1));
        assertEquals(10, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.REGULAR, 101));
        assertEquals(80, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.REGULAR, 800));
        assertEquals(80, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.REGULAR, 1000));

        assertEquals(70, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 1));
        assertEquals(70, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 9));
        assertEquals(71, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 10));
        assertEquals(73, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 30));
        assertEquals(80, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 500));

        assertEquals(0, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.NEW, 1));
        assertEquals(0, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.NEW, 10));
        assertEquals(0, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.NEW, 20));

        assertEquals(0, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 1));
        assertEquals(50, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 2));
        assertEquals(50, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 9));
        assertEquals(51, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 10));
        assertEquals(53, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 30));
        assertEquals(80, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 500));
    }
}