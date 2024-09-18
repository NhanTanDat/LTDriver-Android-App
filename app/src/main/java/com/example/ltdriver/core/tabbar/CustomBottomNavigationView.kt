package com.example.ltdriver.core.tabbar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.example.ltdriver.R
import com.example.ltdriver.databinding.CustomTabbarHomeBinding

class CustomBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

    private val binding = CustomTabbarHomeBinding.inflate(LayoutInflater.from(context), this, true)
    private var onTabClickListener: ((Int) -> Unit)? = null

    init {
        setupListeners()
    }

    private fun setupListeners() {
        val defaultBackgroundColor = ContextCompat.getColor(context, R.color.defaultBackground)
        val defaultTextColor = ContextCompat.getColor(context, R.color.defaultText)
        val pressedBackgroundColor = ContextCompat.getColor(context, R.color.colorPressed)
        val pressedTextColor = ContextCompat.getColor(context, R.color.colorIconPressed)

        fun resetTabStates() {
            binding.mapLayout.setBackgroundColor(defaultBackgroundColor)
            binding.historyLayout.setBackgroundColor(defaultBackgroundColor)
            binding.statisticsLayout.setBackgroundColor(defaultBackgroundColor)
            binding.moreLayout.setBackgroundColor(defaultBackgroundColor)

            binding.mapTextView.setTextColor(defaultTextColor)
            binding.historyTextView.setTextColor(defaultTextColor)
            binding.statisticsTextView.setTextColor(defaultTextColor)
            binding.moreTextView.setTextColor(defaultTextColor)

            binding.mapImageView.setColorFilter(defaultTextColor)
            binding.historyImageView.setColorFilter(defaultTextColor)
            binding.statisticsImageView.setColorFilter(defaultTextColor)
            binding.moreImageView.setColorFilter(defaultTextColor)

        }

        binding.mapLayout.setOnClickListener { view ->
            resetTabStates()
            view.setBackgroundColor(pressedBackgroundColor)
            binding.mapTextView.setTextColor(pressedTextColor)
            binding.mapImageView.setColorFilter(pressedTextColor)
            onTabClickListener?.invoke(R.id.mapLayout)
            Log.d("LayoutClick", "Map Layout clicked")
        }

        binding.historyLayout.setOnClickListener { view ->
            resetTabStates()
            view.setBackgroundColor(pressedBackgroundColor)
            binding.historyTextView.setTextColor(pressedTextColor)
            binding.historyImageView.setColorFilter(pressedTextColor)
            onTabClickListener?.invoke(R.id.historyLayout)
            Log.d("LayoutClick", "History Layout clicked")
        }

        binding.statisticsLayout.setOnClickListener { view ->
            resetTabStates()
            view.setBackgroundColor(pressedBackgroundColor)
            binding.statisticsTextView.setTextColor(pressedTextColor)
            binding.statisticsImageView.setColorFilter(pressedTextColor)
            onTabClickListener?.invoke(R.id.statisticsLayout)
            Log.d("LayoutClick", "Statistics Layout clicked")
        }

        binding.moreLayout.setOnClickListener { view ->
            resetTabStates()
            view.setBackgroundColor(pressedBackgroundColor)
            binding.moreTextView.setTextColor(pressedTextColor)
            binding.moreImageView.setColorFilter(pressedTextColor)
            onTabClickListener?.invoke(R.id.moreLayout)
            Log.d("LayoutClick", "More Layout clicked")
        }
    }

    fun setOnTabClickListener(listener: (Int) -> Unit) {
        onTabClickListener = listener
    }
}
