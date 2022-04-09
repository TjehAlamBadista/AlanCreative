package com.example.myapplication.viewModul

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.modul.Produk

class ProductViewModul : ViewModel() {
    private var produk = MutableLiveData<List<Produk>>()
    private var selectedProducts = MutableLiveData<List<Produk>>()

    init {
        produk.postValue(mutableListOf())
        selectedProducts.postValue(mutableListOf())
    }

    fun fetchDummyProduct(){
        val dummies = mutableListOf<Produk>().apply {
            add(Produk(1, "Makanan A", 1000, "https://awsimages.detik.net.id/community/media/visual/2021/04/22/5-makanan-enak-dari-indonesia-dan-malaysia-yang-terkenal-enak-5.jpeg?w=700&q=90"))
            add(Produk(2, "Makanan B", 2000, "https://img.inews.co.id/media/822/files/inews_new/2021/05/18/mencicipi_makanan_khas_indonesia.jpg"))
            add(Produk(3, "Makanan C", 3000, "https://media.suara.com/pictures/970x544/2022/01/05/49087-makanan-khas-indonesia.jpg"))
            add(Produk(4, "Makanan D", 4000, "https://statik.tempo.co/data/2018/06/03/id_709908/709908_720.jpg"))
            add(Produk(5, "Makanan E", 5000, "https://ad-cms-daihatsu.imgix.net/uploads/tipsandtrick/25-makanan-khas-palembang-1611581901437.jpeg?w=720&h=auto&q=65&fm=jpg&auto=format&fit=max&crop=center"))
            add(Produk(6, "Makanan F", 6000, "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2021/06/makanan.jpg"))
            add(Produk(7, "Makanan G", 7000, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTEG_DX_NNMEIJfbil4uIvR7hPtyZa-sPgbw&usqp=CAU"))
            add(Produk(8, "Makanan H", 8000, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStJTyRgWOrwTysqr-9WIe1bkGLBtDPs82KzQ&usqp=CAU"))
        }

        produk.postValue(dummies)
    }

    fun addSelectedProduct(produk: Produk){
        val tempSelectedProduk : MutableList<Produk>
        = if (selectedProducts.value == null)
            mutableListOf()
        else{
            selectedProducts.value as MutableList<Produk>
        }

        val sameProduk : Produk? = tempSelectedProduk.find { p ->
            p.id == produk.id
        }
        sameProduk?.let {
            it.selectedQuantity = it.selectedQuantity?.plus(1)
        }
        selectedProducts.postValue(tempSelectedProduk)
    }

    fun decrementQuantityProduk(produk: Produk){
        val tempSelectedProduk : MutableList<Produk>
                = if (selectedProducts.value == null)
            mutableListOf()
        else{
            selectedProducts.value as MutableList<Produk>
        }

        val sameProduk : Produk? = tempSelectedProduk.find { p ->
            p.id == produk.id
        }
        sameProduk?.let {
            if (it.selectedQuantity?.minus(1) == 0){
                tempSelectedProduk.remove(it)
            }
            else{
                it.selectedQuantity = it.selectedQuantity!!.minus(1)
            }
        }
        selectedProducts.postValue(tempSelectedProduk)
    }

    fun incrementQuantityProduk(produk: Produk){
        val tempSelectedProduk : MutableList<Produk>
                = if (selectedProducts.value == null)
            mutableListOf()
        else{
            selectedProducts.value as MutableList<Produk>
        }

        val sameProduk : Produk? = tempSelectedProduk.find { p ->
            p.id == produk.id
        }
        sameProduk?.let {
            it.selectedQuantity = it.selectedQuantity!!.plus(1)
        }
        selectedProducts.postValue(tempSelectedProduk)
    }

    fun deleteSelectedProduk(produk: Produk){
        val tempSelectedProduk : MutableList<Produk>
                = if (selectedProducts.value == null)
            mutableListOf()
        else{
            selectedProducts.value as MutableList<Produk>
        }

        val sameProduk : Produk? = tempSelectedProduk.find { p ->
            p.id == produk.id
        }
        sameProduk?.let {
            tempSelectedProduk.remove(it)
        }
        selectedProducts.postValue(tempSelectedProduk)
    }

    fun listenToProduk() = produk
    fun listenSelectedProduk() = selectedProducts
}