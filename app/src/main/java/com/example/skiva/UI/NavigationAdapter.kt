package com.example.skiva.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageButton
import com.example.skiva.R
import com.example.skiva.UI.*

class NavigationController(private val context: Context, view: View) {

    private val homeButton: ImageButton = view.findViewById(R.id.homeButton)
    private val reminderButton: ImageButton = view.findViewById(R.id.reminderButton)
    private val reportButton: ImageButton = view.findViewById(R.id.reportButton)
    private val profileButton: ImageButton = view.findViewById(R.id.profileButton)
    private val screeningButton: ImageButton = view.findViewById(R.id.screeningButton)

    init {
        setupListeners()
    }

    private fun setupListeners() {
        homeButton.setOnClickListener {
            context.startActivity(Intent(context, home_page::class.java))
        }

        reminderButton.setOnClickListener {
            context.startActivity(Intent(context, pengingat_obat::class.java))
        }

        reportButton.setOnClickListener {
            context.startActivity(Intent(context, laporan::class.java))
        }

        profileButton.setOnClickListener {
            context.startActivity(Intent(context, profile::class.java))
        }

        screeningButton.setOnClickListener {
            context.startActivity(Intent(context, screening::class.java))
        }
    }
}
