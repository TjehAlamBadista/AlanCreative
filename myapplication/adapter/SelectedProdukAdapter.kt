package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.myapplication.R
import com.example.myapplication.modul.Produk
import com.example.myapplication.viewModul.ProductViewModul
import kotlinx.android.synthetic.main.komponen_nomor.view.*
import kotlinx.android.synthetic.main.list_selected_produk.view.*

class SelectedProdukAdapter(private var selectedProduks : MutableList<Produk>, private var context: Context, private var productViewModel : ProductViewModul)
    : RecyclerView.Adapter<SelectedProdukAdapter.ViewHolder>(){

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            fun bind(produk: Produk, context: Context, productViewModel: ProductViewModul){
                itemView.tv_nama_produk2.text = produk.name
                itemView.tv_harga_produk2.text = produk.price.toString()
                itemView.tv_selectedQuantity.text = produk.selectedQuantity.toString()
                itemView.circle_img_produk.load(produk.image)
                itemView.cv_increment.setOnClickListener {
                    productViewModel.incrementQuantityProduk(produk)
                }
                itemView.cv_decrement.setOnClickListener {
                    productViewModel.decrementQuantityProduk(produk)
                }
                itemView.produk_more.setOnClickListener {
                    PopupMenu(context, it).apply {
                        menuInflater.inflate(R.menu.popup_menu, menu)
                        setOnMenuItemClickListener { menuItem ->
                            when(menuItem.itemId){
                                R.id.popup_delete -> {
                                    productViewModel.deleteSelectedProduk(produk)
                                    true
                                }
                                else -> true
                            }
                        }
                    }.show()
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_selected_produk, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(selectedProduks[position], context, productViewModel)

    override fun getItemCount() = selectedProduks.size

    fun updateList(ps: List<Produk>){
        selectedProduks.clear()
        selectedProduks.addAll(ps)
        notifyDataSetChanged()
    }
}