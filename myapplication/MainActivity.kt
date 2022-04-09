package com.example.myapplication

import android.app.Dialog
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.ProdukAdapter
import com.example.myapplication.adapter.SelectedProdukAdapter
import com.example.myapplication.viewModul.ProductViewModul
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottomsheet_cart.*

class MainActivity : AppCompatActivity() {

    private lateinit var productViewModul: ProductViewModul
    private lateinit var bottomSheet : BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
    }

    private fun setupUI(){

        productViewModul = ViewModelProvider(this).get(ProductViewModul::class.java)

        rv_produk.apply {
            layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                GridLayoutManager(this@MainActivity, 2)
            }
            else{
                GridLayoutManager(this@MainActivity, 4)
            }
            adapter =  ProdukAdapter(mutableListOf(), this@MainActivity, productViewModul)
        }

        rv_selected_produk.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = SelectedProdukAdapter(mutableListOf(), this@MainActivity, productViewModul)
        }

        bottomSheet = BottomSheetBehavior.from(bottomsheet_detail_order)
        bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN

        btn_detail.setOnClickListener {
            if(bottomSheet.state == BottomSheetBehavior.STATE_COLLAPSED || bottomSheet.state == BottomSheetBehavior.STATE_HIDDEN){
                bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
            }
            else{
                bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
            }
        }

        btn_checkout.setOnClickListener {
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setContentView(R.layout.custom_dialog)

            dialog.show()
        }

        productViewModul.fetchDummyProduct()
        productViewModul.listenToProduk().observe(this, Observer {
            rv_produk.adapter?.let { a ->
                if (a is ProdukAdapter){
                    a.updatelist(it)
                }
            }
        })
        productViewModul.listenSelectedProduk().observe(this, Observer {
            rv_selected_produk.adapter?.let { a ->
                if (a is SelectedProdukAdapter){
                    a.updateList(it)
                }
            }
            val totalHarga =
                if (it.isEmpty()){ 0 }
                else{ it.sumBy {  p ->
                    p.price!! * p.selectedQuantity!!
                }
                }
            tv_total_harga.text = totalHarga.toString()
        })
    }
}