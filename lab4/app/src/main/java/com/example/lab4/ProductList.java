package com.example.lab4; // Make sure this matches your project!

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ProductList extends ArrayAdapter<Product> {

    private Activity context;
    private List<Product> products;

    public ProductList(Activity context, List<Product> products) {
        // We use a default Android list layout here for simplicity
        super(context, android.R.layout.simple_list_item_1, products);
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        // This inflates a basic built-in Android layout that just has a TextView
        View listViewItem = inflater.inflate(android.R.layout.simple_list_item_1, null, true);

        // Get the TextView from the built-in layout
        TextView textViewName = listViewItem.findViewById(android.R.id.text1);

        // Get the current product from the list
        Product product = products.get(position);

        // Set the text to show the product name and price
        textViewName.setText(product.getName() + " - $" + product.getPrice());

        return listViewItem;
    }
}