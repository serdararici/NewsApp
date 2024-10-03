package com.serdararici.newsapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.serdararici.newsapp.R
import com.serdararici.newsapp.data.entity.Etkinlik
import com.serdararici.newsapp.databinding.CardAnnouncementBinding
import com.serdararici.newsapp.ui.fragment.DuyuruFragmentDirections
import com.serdararici.newsapp.ui.viewmodel.DuyuruViewModel

class DuyurularAdapter (var mContext: Context,
                        var duyurularListesi:List<Etkinlik>,
                        var viewModel: DuyuruViewModel)
    :RecyclerView.Adapter<DuyurularAdapter.CardAnnouncementHolder>(){
    inner class CardAnnouncementHolder(binding : CardAnnouncementBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: CardAnnouncementBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAnnouncementHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = CardAnnouncementBinding.inflate(layoutInflater,parent,false)
        return CardAnnouncementHolder(binding)
    }

    override fun getItemCount(): Int {
        return duyurularListesi.size
    }

    override fun onBindViewHolder(holder: CardAnnouncementHolder, position: Int) {
        val duyuru = duyurularListesi.get(position)
        holder.binding.tvTitleCardAnnouncement.text = duyuru.konu
        holder.binding.ivCardAnnouncement.setImageResource(R.drawable.baseline_notifications_24_yellow)

        holder.binding.cvAnnouncementMain.setOnClickListener{
            val action = DuyuruFragmentDirections.actionDuyuruFragmentToDuyuruDetayFragment(duyuru)
            Navigation.findNavController(it).navigate(action)
        }
    }
}