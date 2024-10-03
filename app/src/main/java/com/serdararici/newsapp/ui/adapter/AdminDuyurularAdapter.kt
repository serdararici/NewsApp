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
import com.serdararici.newsapp.databinding.CardAnnouncementAdminBinding
import com.serdararici.newsapp.ui.fragment.AdminDuyuruFragmentDirections
import com.serdararici.newsapp.ui.viewmodel.AdminDuyuruViewModel

class AdminDuyurularAdapter (var mContext: Context,
                             var duyurularListesi:List<Etkinlik>,
                             var viewModel: AdminDuyuruViewModel)
    : RecyclerView.Adapter<AdminDuyurularAdapter.CardAnnouncementAdminHolder>(){
    inner class CardAnnouncementAdminHolder(binding : CardAnnouncementAdminBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: CardAnnouncementAdminBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAnnouncementAdminHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = CardAnnouncementAdminBinding.inflate(layoutInflater,parent,false)
        return CardAnnouncementAdminHolder(binding)
    }

    override fun getItemCount(): Int {
        return duyurularListesi.size
    }

    override fun onBindViewHolder(holder: CardAnnouncementAdminHolder, position: Int) {
        val duyuru = duyurularListesi.get(position)
        holder.binding.tvTitleCardAnnouncementAdmin.text = duyuru.konu
        holder.binding.ivCardAnnouncementAdmin.setImageResource(R.drawable.baseline_notifications_24_yellow)

        holder.binding.cvAnnouncementAdmin.setOnClickListener{
            val action = AdminDuyuruFragmentDirections.actionAdminDuyuruFragmentToAdminDuyuruDetayFragment(duyuru)
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.ivAnnouncementSettingsAdmin.setOnClickListener{
            val popup = PopupMenu(mContext, holder.binding.ivAnnouncementSettingsAdmin)
            popup.menuInflater.inflate(R.menu.announcement_popup_menu_admin, popup.menu)
            popup.show()
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.updateAnnouncement -> {
                        val action = AdminDuyuruFragmentDirections.actionAdminDuyuruFragmentToAdminDuyuruGuncelleFragment(duyuru)
                        Navigation.findNavController(it).navigate(action)
                        true
                    }
                    R.id.deleteAnnouncement -> {
                        // Silme işlemi, Snackbar ile onay alınıyor
                        Snackbar.make(it, "${duyuru.konu} ${mContext.getString(R.string.isDelete)}", Snackbar.LENGTH_LONG)
                            .setAction(R.string.yes) {
                                viewModel.deleteAnnouncement(duyuru.id)
                            }.show()
                        true
                    }
                    else -> false
                }
            }
        }
    }


}