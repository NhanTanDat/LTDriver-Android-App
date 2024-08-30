package com.example.ltdriver

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.ltdriver.databinding.CustomTabbarHomeBinding

class CustomBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 1,
) : FrameLayout(context,attributeSet,defStyleAttr) {
    private val binding = CustomTabbarHomeBinding.inflate(LayoutInflater.from(context), this, true)
}
