package com.serdararici.newsapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.serdararici.newsapp.R
import com.serdararici.newsapp.data.entity.Haber
import com.serdararici.newsapp.databinding.CardNewsAdminBinding
import com.serdararici.newsapp.ui.fragment.AdminHaberFragmentDirections

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

        holder.binding.ivNewsSettingsAdmin.setOnClickListener{
            val popup = PopupMenu(mContext, holder.binding.ivNewsSettingsAdmin)
            popup.menuInflater.inflate(R.menu.news_popup_menu_admin, popup.menu)
            popup.show()
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.updateNews -> {
                        val action = AdminHaberFragmentDirections.actionAdminHaberFragmentToAdminHaberGuncelleFragment(haber)
                        Navigation.findNavController(it).navigate(action)
                        true
                    }
                    R.id.deleteNews -> {
                        // Silme işlemi, Snackbar ile onay alınıyor
                        Snackbar.make(it, "${haber.konu} ${mContext.getString(R.string.isDelete)}", Snackbar.LENGTH_LONG)
                            .setAction(R.string.yes) {
                                Log.e("sil", haber.id.toString())
                                //viewModel.deleteNewsSettings(newsSettings.id!!)
                            }.show()
                        true
                    }
                    else -> false
                }
            }
        }
    }
}