package com.example.ltdriver.core.tabbar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.ltdriver.R

class CustomBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 1,
) : FrameLayout(context, attributeSet, defStyleAttr) {

    private lateinit var mapLayout: LinearLayout
    private lateinit var historyLayout: LinearLayout
    private lateinit var statsLayout: LinearLayout
    private lateinit var moreLayout: LinearLayout

    init {
        try {
            // Inflate the custom layout for the bottom navigation
            val binding = LayoutInflater.from(context).inflate(R.layout.custom_tabbar_home, this, true)

            // Initialize the variables after inflation
            mapLayout = binding.findViewById(R.id.mapLayout)
            historyLayout = binding.findViewById(R.id.historyLayout)
            statsLayout = binding.findViewById(R.id.statsLayout)
            moreLayout = binding.findViewById(R.id.moreLayout)

            // Set default selected button
            setSelectedButton(mapLayout)

            // Set click listeners for each tab
            mapLayout.setOnClickListener { onNavigationItemSelected(mapLayout) }
            historyLayout.setOnClickListener { onNavigationItemSelected(historyLayout) }
            statsLayout.setOnClickListener { onNavigationItemSelected(statsLayout) }
            moreLayout.setOnClickListener { onNavigationItemSelected(moreLayout) }
        } catch (e: Exception) {
            Log.e("CustomBottomNav", "Error initializing CustomBottomNavigationView", e)
        }
    }

    private fun onNavigationItemSelected(selectedButton: LinearLayout) {
        Log.d("CustomBottomNav", "Navigation item selected: ${selectedButton.id}")
        setSelectedButton(selectedButton)
    }

    private fun setSelectedButton(selectedButton: LinearLayout) {
        Log.d("CustomBottomNav", "Setting selected button with ID: ${selectedButton.id}")
        // Reset all buttons to default state
        resetButtons()

        // Set selected state for the clicked button
        selectedButton.isSelected = true
        updateButtonAppearance(selectedButton, true)
    }

    private fun resetButtons() {
        Log.d("CustomBottomNav", "Resetting all buttons")
        updateButtonAppearance(mapLayout, false)
        updateButtonAppearance(historyLayout, false)
        updateButtonAppearance(statsLayout, false)
        updateButtonAppearance(moreLayout, false)
    }

    private fun updateButtonAppearance(buttonLayout: LinearLayout, isSelected: Boolean) {
        Log.d("CustomBottomNav", "Updating appearance for buttonLayout ID: ${buttonLayout.id}, isSelected: $isSelected")

        // Tìm ImageView và TextView tương ứng trong mỗi buttonLayout
        val icon = when (buttonLayout.id) {
            R.id.mapLayout -> buttonLayout.findViewById<ImageView>(R.id.mapIcon)
            R.id.historyLayout -> buttonLayout.findViewById<ImageView>(R.id.historyIcon)
            R.id.statsLayout -> buttonLayout.findViewById<ImageView>(R.id.statsIcon)
            R.id.moreLayout -> buttonLayout.findViewById<ImageView>(R.id.moreIcon)
            else -> null
        }

        val text = when (buttonLayout.id) {
            R.id.mapLayout -> buttonLayout.findViewById<TextView>(R.id.mapText)
            R.id.historyLayout -> buttonLayout.findViewById<TextView>(R.id.historyText)
            R.id.statsLayout -> buttonLayout.findViewById<TextView>(R.id.statsText)
            R.id.moreLayout -> buttonLayout.findViewById<TextView>(R.id.moreText)
            else -> null
        }

        // Log thông tin để kiểm tra
        Log.d("CustomBottomNav", "Icon found: ${icon != null}, Text found: ${text != null}")

        // Kiểm tra nếu icon và text không phải null, sau đó thay đổi màu sắc
        icon?.let {
            try {
                if (isSelected) {
                    Log.d("CustomBottomNav", "Setting selected color for icon")
                    it.setColorFilter(ContextCompat.getColor(context, R.color.colorSelected))
                } else {
                    Log.d("CustomBottomNav", "Setting default color for icon")
                    it.setColorFilter(ContextCompat.getColor(context, R.color.colorDefault))
                }
            } catch (e: Exception) {
                Log.e("CustomBottomNav", "Error setting color filter for icon", e)
            }
        }

        text?.let {
            try {
                if (isSelected) {
                    Log.d("CustomBottomNav", "Setting selected color for text")
                    it.setTextColor(ContextCompat.getColor(context, R.color.colorSelected))
                } else {
                    Log.d("CustomBottomNav", "Setting default color for text")
                    it.setTextColor(ContextCompat.getColor(context, R.color.colorDefault))
                }
            } catch (e: Exception) {
                Log.e("CustomBottomNav", "Error setting text color", e)
            }
        }
    }
}
