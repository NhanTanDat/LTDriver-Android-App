package com.example.ltdriver.core.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
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
    }
}
