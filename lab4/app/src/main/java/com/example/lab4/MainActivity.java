package com.example.lab4;

import android.os.Bundle;

//import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;

import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class MainActivity extends AppCompatActivity {

    // My instance variables
    DatabaseReference databaseProducts;
    EditText editTextName;
    EditText editTextPrice;

    ListView listViewProducts;

    List<Product> products;

    Button buttonAddProduct, buttonUpdateProduct, buttonDeleteProduct;
    String selectedProductId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextPrice = findViewById(R.id.editTextPrice);
        listViewProducts = findViewById(R.id.listViewProducts);
        buttonAddProduct = findViewById(R.id.buttonAddProduct);
        buttonUpdateProduct = findViewById(R.id.buttonUpdateProduct);
        buttonDeleteProduct = findViewById(R.id.buttonDeleteProduct);
        products = new ArrayList<>();
        databaseProducts = FirebaseDatabase.getInstance().getReference("products");


        listViewProducts.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Product product = products.get(position);
            selectedProductId = product.getId();
            editTextName.setText(product.getName());
            editTextPrice.setText(String.valueOf(product.getPrice()));
        });

        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
            }
        });

        buttonAddProduct.setOnClickListener(v -> addProduct());

        buttonUpdateProduct.setOnClickListener(v -> {
            if (selectedProductId != null) {
                String name = editTextName.getText().toString().trim();
                double price = Double.parseDouble(editTextPrice.getText().toString().trim());
                updateProduct(selectedProductId, name, price);
            }
        });

        buttonDeleteProduct.setOnClickListener(v -> {
            if (selectedProductId != null) {
                deleteProduct(selectedProductId);
                selectedProductId = null;
                editTextName.setText("");
                editTextPrice.setText("");
            }
        });
    }


    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseProducts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                products.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting product
                    Product product = postSnapshot.getValue(Product.class);
                    //adding product to the list
                    products.add(product);
                }
                //creating adapter
                ProductList productsAdapter = new ProductList(MainActivity.this, products);
                //attaching adapter to the listview
                listViewProducts.setAdapter(productsAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void addProduct() {
        //getting the values to save
        String name = editTextName.getText().toString().trim();
        double price = Double.parseDouble(String.valueOf(editTextPrice.getText().toString()));

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Product
            String id = databaseProducts.push().getKey();

            //creating a Product Object
            Product product = new Product(id, name, price);

            //Saving the Product
            databaseProducts.child(id).setValue(product);

            //setting edittext to blank again
            editTextName.setText("");
            editTextPrice.setText("");

            //displaying a success toast
            Toast.makeText(this, "Product added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }

    private void updateProduct(String id, String name, double price) {
        //getting the specified product reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("products").child(id);
        //updating product
        Product product = new Product(id, name, price);
        dR.setValue(product);

        Toast.makeText(getApplicationContext(), "Product Updated", Toast.LENGTH_LONG).show();
    }

    private boolean deleteProduct(String id) {
        //getting the specified product reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("products").child(id);
        //removing product
        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Product Deleted", Toast.LENGTH_LONG).show();
        return true;
    }

}