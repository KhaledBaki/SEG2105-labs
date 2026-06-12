package com.example.lab4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Extra imported classes
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

// Google imports
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseReference databaseProducts;
        databaseProducts = FirebaseDatabase.getInstance().getReference("products");
    }

    protected void onStart() {

        super.onStart();
        // Attatching value event listener
        databaseProducts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (DataSnapshot dataSnapshot) {
                // Clearing the previous artist list
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

    protected void addProduct(){
        //getting the value to save
        String name = editTextName.getText().toString.trim();
        double price = Double.parseDouble(String.valueOf(editTextPrice.getText().toString()));

        // Checking if the value is provided
        if(!TextUtils.isEmpty(name)){

            // getting a unique id using push().getKey() method
            // it will create a unique id and we will use it as the Primary key for our product
            String id = databaseProducts.push().getKey();

            // Creating a product object
            Product product = new Product(id, name, price);

            // Saving the product
            databaseProducts.child(id).setValue(product);

            // setting the edittext to blank again
            editTextName.setText("");
            editTextPrice.setText("");

            // Displaying a success toast
            Toast.makeText(this, "Product added", Toast.LENGTH_LONG).show();
        } else {
            // if the  value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }

    private void updateProduct(String id, String name, double price){
        // getting the specified product reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products").child(id);

        // updating product
        Product product = new Product(id, name, price);
        databaseReference.setValue(product);

        Toast.makeText(getApplicationContext(), "Product Updated", Toast.LENGTH_LONG).show();
    }

    private boolean deleteProduct(String id) {
        // Getting the specified product reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products").child(id);

        // removing product
        databaseReference.removeValue();
        Toast.makeText(getApplicationContext(), "Product Deleted", Toast.LENGTH_LONG).show();
        return true;
    }
}

