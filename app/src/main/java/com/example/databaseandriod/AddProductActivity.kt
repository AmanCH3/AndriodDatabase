package com.example.databaseandriod

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.databaseandriod.databinding.ActivityAddProductBinding
import com.example.databaseandriod.model.ProductModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddProductActivity : AppCompatActivity() {

    lateinit var addProductBinding: ActivityAddProductBinding
    //connection establish
    var firebaseDatabase : FirebaseDatabase = FirebaseDatabase.getInstance()
    var ref : DatabaseReference = firebaseDatabase.reference.child("product") // name as the table in JSON format


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        addProductBinding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(addProductBinding.root)


        addProductBinding.button.setOnClickListener{
            var productName : String = addProductBinding.editName.text.toString()
            var productPrice : Int = addProductBinding.editPrice.text.toString().toInt()
            var productDesc: String = addProductBinding.editDesc.text.toString()

         var id = ref.push().key.toString()// random key generated for primary key

            var data  = ProductModel(productName,productPrice,productDesc)
          ref.child(id).setValue(data) .addOnCompleteListener{
              task ->
              if(task.isSuccessful){
                  Toast.makeText(applicationContext,"Data saved",Toast.LENGTH_LONG).show()
              }
              else{
                  Toast.makeText(applicationContext,task.exception?.message,Toast.LENGTH_LONG).show()
              }
          }

        }
    }
}