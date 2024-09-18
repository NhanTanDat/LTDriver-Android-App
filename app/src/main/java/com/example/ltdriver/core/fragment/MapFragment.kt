package com.example.ltdriver.core.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ltdriver.R

class MapFragment : Fragment() {

    private var isShutdown = true // Trạng thái để thay đổi văn bản và icon

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Lấy ImageButton và TextView từ layout
        val imageButton: ImageButton = view.findViewById(R.id.imageButton)
        val popupTextView: TextView = view.findViewById(R.id.popupTextView)

        // Lấy avatar ImageView từ layout
        val avatarImageView: ImageView = view.findViewById(R.id.avatarImageView)

        // Xử lý sự kiện nhấn nút
        imageButton.setOnClickListener {
            if (isShutdown) {
                imageButton.setImageResource(R.drawable.ic_shutdown) // Đổi sang icon ic_start
                popupTextView.text = "Đang Hoạt Động" // Thay đổi văn bản
            } else {
                imageButton.setImageResource(R.drawable.ic_shutdow_dark) // Đổi lại icon ic_shutdown
                popupTextView.text = "Không Hoạt Động" // Thay đổi văn bản
            }
            isShutdown = !isShutdown // Đảo ngược trạng thái
        }

        // Xử lý sự kiện nhấn vào avatar để hiện popup
        avatarImageView.setOnClickListener {
            showPopup(view)
        }
    }

    // Hàm hiển thị popup
    private fun showPopup(anchorView: View) {
        // Inflate the popup_layout
        val inflater = LayoutInflater.from(requireContext())
        val popupView = inflater.inflate(R.layout.popup_layout, null)

        // Tạo PopupWindow
        val popupWindow = PopupWindow(
            popupView,
            ConstraintLayout.LayoutParams.MATCH_PARENT,  // Chiếm toàn bộ chiều rộng màn hình
            ConstraintLayout.LayoutParams.MATCH_PARENT   // Chiếm toàn bộ chiều cao màn hình
        )

        // Lấy Button từ popup_layout và xử lý sự kiện nhấn
        val closeButton = popupView.findViewById<Button>(R.id.acceptPopupButton)
        closeButton.setOnClickListener {
            popupWindow.dismiss() // Đóng popup khi nhấn nút
        }

        // Hiển thị popup ở giữa màn hình
        popupWindow.isFocusable = true
        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0)
    }

}
