package dev.ploeger.sweets.shop;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import dev.ploeger.sweets.shop.adapter.WarehouseAdapter;
import dev.ploeger.sweets.shop.model.Sweet;

import java.util.List;

@Route("")
public class ShoppingView extends VerticalLayout {

    private final WarehouseAdapter warehouseAdapter;


    public ShoppingView(WarehouseAdapter warehouseAdapter) {
        this.warehouseAdapter = warehouseAdapter;
        List<Sweet> inventory = warehouseAdapter.getInventory();
        add(new H1("I take you to the candy shop"),
                new Text(inventory.getFirst().name()));


//        TextField textField = new TextField("Candy");
//        Button button = new Button("Add to Cart");
//        VerticalLayout cart = new VerticalLayout();
//
//        button.addClickListener(clickEvent -> {
//            cart.add(new Text(textField.getLabel()));
//        });
//
//        add(
//                new H1("I take you to the candy shop"),
//                new HorizontalLayout(textField, button),
//                new H1("Cart"),
//                cart
//
//        );
    }
}
