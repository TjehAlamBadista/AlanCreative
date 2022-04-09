package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.myapplication.R
import com.example.myapplication.modul.Produk
import com.example.myapplication.viewModul.ProductViewModul
import kotlinx.android.synthetic.main.list_item_produk.view.*

class ProdukAdapter(private var produk : MutableList<Produk>, private var context: Context, private var productViewModul : ProductViewModul)
    : RecyclerView.Adapter<ProdukAdapter.ViewHolder>(){
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            fun bind(produk: Produk, context: Context, productViewModul: ProductViewModul){
                itemView.tv_nama_produk.text = produk.name
                itemView.tv_harga_produk.text = produk.price.toString()
                itemView.img_produk.load(produk.image)
                itemView.setOnClickListener {
                    val p = produk.copy()
                    p.selectedQuantity = 1
                    productViewModul.addSelectedProduct(p)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_produk, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(produk[position], context, productViewModul)

    override fun getItemCount() = produk.size

    fun updatelist(ps : List<Produk>){
        produk.clear()
        produk.addAll(ps)
        notifyDataSetChanged()
    }
}