// Importing Lab 4
package com.example.lab4;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductList extends ArrayAdapter<Product> {

    private Activity context;
    private List<Product> products;

    /**
     * ProductList constructor
     *
     * @param context Expects the current activity hosting the main product list being
     *                dealt with. Also, necessary to be able to use simple_list_item_1.
     * @param products Only accepts Product type objects
     */
    public ProductList(Activity context, List<Product> products) {

        // We use a default Android list layout here for simplicity
        super(context, android.R.layout.simple_list_item_1, products);
        this.context = context;
        this.products = products;
    }


    /**
     * This method is automatically called by the android system
     *
     * @param position The position of the item from where it's being drawn example: Row 00, Row 01, etc...
     * @param convertView The old view to reuse if possible, useful when we have many items.
     * @param parent The parent that this view will eventually be attached to
     * @return a Non-null list view Item (Box containing the product details)
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        // Converting the XML to an interactive and Viewable object
        // Context here refers to the Activity we reference, example "MainActivity.this"
        LayoutInflater inflater = context.getLayoutInflater();

        // Builds the UI row object from the prebuilt blueprint file of simple_list_item_1, and attaches it to the root
        // think like ladder!
        View listViewItem = inflater.inflate(android.R.layout.simple_list_item_1, null, false);

        // Get the TextView from the built-in layout
        TextView textViewName = listViewItem.findViewById(android.R.id.text1);

        // Get the current product from the list
        Product product = products.get(position);

        // Assigns the text to show the product name and price
        // This is the actual data that matters most, the name and the price of the product!
        textViewName.setText(product.getName() + " - $" + product.getPrice());

        // return the Item we created in the above steps
        return listViewItem;
    }
}