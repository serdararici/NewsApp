package com.serdararici.newsapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serdararici.newsapp.R
import com.serdararici.newsapp.data.entity.Haber
import com.serdararici.newsapp.databinding.CardNewsAdminBinding

class AdminHaberlerAdapter (var mContext: Context, var haberlerListesi:List<Haber>) : RecyclerView.Adapter<AdminHaberlerAdapter.CardNewsAdminHolder>() {
    inner class CardNewsAdminHolder(binding : CardNewsAdminBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: CardNewsAdminBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardNewsAdminHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = CardNewsAdminBinding.inflate(layoutInflater,parent,false)
        return CardNewsAdminHolder(binding)
    }

    override fun getItemCount(): Int {
        return haberlerListesi.size
    }

    override fun onBindViewHolder(holder: CardNewsAdminHolder, position: Int) {
        val haber = haberlerListesi.get(position)
        holder.binding.tvTitleCardNewsAdmin.text = haber.konu
        holder.binding.tvContentCardNewsAdmin.text = haber.icerik
        holder.binding.tvUserNameCardNewsAdmin.text = haber.kullaniciAdi
        holder.binding.tvDateCardNewsAdmin.text = haber.gecerlilikTarihi
        holder.binding.ivCardNewsAdmin.setImageResource(R.drawable.sport_news)
    }
}