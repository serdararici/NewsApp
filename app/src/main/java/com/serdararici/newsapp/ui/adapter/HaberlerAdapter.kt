package com.serdararici.newsapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serdararici.newsapp.R
import com.serdararici.newsapp.data.entity.Etkinlik
import com.serdararici.newsapp.databinding.CardNewsBinding

class HaberlerAdapter(var mContext: Context, var haberlerListesi:List<Etkinlik>) : RecyclerView.Adapter<HaberlerAdapter.CardNewsHolder>() {
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
        holder.binding.ivCardNews.setImageResource(R.drawable.sport_news)
    }
}