package com.example.databaseandriod

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databaseandriod.adapter.ProductAdapter
import com.example.databaseandriod.databinding.ActivityMainBinding
import com.example.databaseandriod.model.ProductModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding

    // connection establish for adapter
    var firebaseDatabase : FirebaseDatabase = FirebaseDatabase.getInstance()
    var  ref : DatabaseReference = firebaseDatabase.reference.child("product") // this product name is case sensitive


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        // to show data in screem
        var productList = ArrayList<ProductModel>()

        // reference value show
        //object unkown class

        ref.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {


                // clear the data every time savedata button is click show that again data from list is not shown
                productList.clear()
                for(eachData in snapshot.children){
//                    var product = eachData.getValue() // for raw data
                var product = eachData.getValue(ProductModel::class.java)
                    if(product!=null){
                        Log.d("My data ",product.productName)
                        Log.d("My data ",product.productPrice.toString())
                        Log.d("My data ",product.productDesc)


                        productList.add(product)
                    }

                    // assign adapter
                    var adapter = ProductAdapter(productList)
                    mainBinding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    mainBinding.recyclerView.adapter = adapter

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


       mainBinding.floatingActionButton.setOnClickListener {
           var intent = Intent(this@MainActivity, AddProductActivity::class.java)
           startActivity(intent)
       }




    }
    }