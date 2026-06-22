package com.example.lab5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView idView;
    EditText productBox;
    EditText skuBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        idView = (TextView) findViewById(R.id.productID);
        productBox = (EditText) findViewById(R.id.productName);
        skuBox = (EditText) findViewById(R.id.productSku);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void newProduct(View view) {
        String name = productBox.getText().toString().trim();
        String skuText = skuBox.getText().toString().trim();

        if (name.isEmpty()) {
            idView.setText("Enter a product name");
            return;
        }

        if (skuText.isEmpty()) {
            idView.setText("Enter a SKU");
            return;
        }

        int sku;
        try {
            sku = Integer.parseInt(skuText);
        } catch (NumberFormatException e) {
            idView.setText("SKU must be a number");
            return;
        }

        Product product = new Product(name, sku);

        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.addProduct(product);

        productBox.setText("");
        skuBox.setText("");
        idView.setText("Product Added");
    }


    public void lookupProduct (View view) {

        MyDBHandler dbHandler = new MyDBHandler(this);
        Product product = dbHandler.findProduct(productBox.getText().toString());



        if (product != null) {
            idView.setText(String.valueOf(product.getProductID()));
            skuBox.setText(String.valueOf(product.getSKU()));
        } else {
            idView.setText("No Match Found");
        }
    }


    public void removeProduct (View view) {

        MyDBHandler dbHandler = new MyDBHandler(this);
        Product product = dbHandler.findProduct(productBox.getText().toString());
        boolean result = false;

        if (result) {
            idView.setText("Record Deleted");
            productBox.setText("");
            skuBox.setText("");
        }
        else
            idView.setText("No Match Found");
    }


    public void about(View view) {
        Intent aboutIntent = new Intent(this, About.class);
        startActivity(aboutIntent);
    }
}