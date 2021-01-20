package app.com.roomdatabase

import android.content.Context
import android.inputmethodservice.Keyboard
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import java.util.*


class MainActivity : AppCompatActivity() {

    // val background : ExecutorService = Executors.newFixedThreadPool(2)
    //val shopViewModel : ShopViewModel by viewModels()

    lateinit var a : List<Shopping>
    /////// MANIFEST FILE ACTIVITY TAG HAS BEEN ADDED WITH android:windowSoftInputMode="adjustPan" IN ORDER TO AVOID KEYBOARD MOVING THE LAYOUT UPWARDS YUP
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shopViewModel = ViewModelProvider(this).get(ShopViewModel::class.java)
///

        val inputTex = findViewById<TextInputEditText>(R.id.textInputEditText)
        val tex      = findViewById<TextView>(R.id.textView3)
        val imagePlus = findViewById<ImageView>(R.id.imageView)
        val imageMinus = findViewById<ImageView>(R.id.imageView2)
        val add = findViewById<Button>(R.id.button)
        val remove = findViewById<Button>(R.id.button2)

        var counter: Int = 0

        val shop = Shopping()

        imagePlus.setOnClickListener{
            counter += 1
            tex.text = counter.toString()
        }
        imageMinus.setOnClickListener {
            if (counter != 0 )
                counter -= 1
            tex.text = counter.toString()
        }

        add.setOnClickListener {

            val a = inputTex.text.toString()
            if (a == "")
            {
                Toast.makeText(this, "Please enter your food name!", Toast.LENGTH_SHORT).show()
                counter = 0
                tex.text = counter.toString()
            }
              else
              {
                shop.name = a
                shop.amount = counter
                if (counter > 5)
                    shop.IsBigSale = true
                shopViewModel.addShopping(shop)
                tex.text = "0"
                counter = 0
                inputTex.setText("") //YUP THIS IS THE ONLY WAY HOW YOU CAN SET TEXT INTO TEXTINPUTEDITTEXT


                 ////////// //Hiding keyboard as you click this button////////////////
                  val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                  imm.hideSoftInputFromWindow(it.windowToken, 0)
              }
        }

         remove.setOnClickListener {
             shopViewModel.deleteAllShoppings()
         }


        // Recycler view yup
        val recycl = findViewById<RecyclerView>(R.id.recyclerview1)
        val adapter = ShopAdapter()
        val linearLayoutManager = LinearLayoutManager(this)
        recycl.layoutManager = linearLayoutManager
        recycl.adapter = adapter


     shopViewModel.allData.observe(this, Observer {
             adapter.setSHopping(it) //passing list yup
         a = it
     })

        ///////// How to add lines between recyclerview rows////////////
        recycl.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))



        //////////Swipe to delete///////////////////////
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               shopViewModel.deleteShopping(a[viewHolder.adapterPosition])
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recycl)


    }
}