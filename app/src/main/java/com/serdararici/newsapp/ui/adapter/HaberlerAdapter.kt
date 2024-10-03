package com.serdararici.newsapp.ui.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.serdararici.newsapp.R
import com.serdararici.newsapp.data.entity.Etkinlik
import com.serdararici.newsapp.databinding.CardNewsBinding
import com.serdararici.newsapp.ui.fragment.HaberFragmentDirections
import com.serdararici.newsapp.ui.viewmodel.HaberViewModel
import java.io.File

class HaberlerAdapter(var mContext: Context,
                      var haberlerListesi:List<Etkinlik>,
                      var viewModel: HaberViewModel)
    : RecyclerView.Adapter<HaberlerAdapter.CardNewsHolder>() {
    inner class CardNewsHolder(binding : CardNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding:CardNewsBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardNewsHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = CardNewsBinding.inflate(layoutInflater,parent,false)
        return CardNewsHolder(binding)
    }

    override fun getItemCount(): Int {
        return haberlerListesi.size
    }

    override fun onBindViewHolder(holder: CardNewsHolder, position: Int) {
        val haber = haberlerListesi.get(position)
        holder.binding.tvTitleCardNews.text = haber.konu
        holder.binding.tvContentCardNews.text = haber.icerik
        holder.binding.tvUserNameCardNews.text = haber.kullaniciAdi
        holder.binding.tvDateCardNews.text = haber.gecerlilikTarihi
        //holder.binding.ivCardNews.setImageResource(R.drawable.image_not_found)

        val imagePath = haber.resim
        val file = java.io.File(imagePath)

        if (file.exists()) {
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            holder.binding.ivCardNews.setImageBitmap(bitmap)  // Resmi bir ImageView'de göster
        } else {
            holder.binding.ivCardNews.setImageResource(R.drawable.image_not_found)
        }

        holder.binding.cvNewsMain.setOnClickListener{
            val action = HaberFragmentDirections.actionHaberFragmentToHaberDetayFragment(haber)
            Navigation.findNavController(it).navigate(action)
        }
    }
}