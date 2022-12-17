package com.example.kotlin_eva.components

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlin_eva.R

// the fragment initialization parameters,
private const val IMAGE = "param1"
private const val HEADER = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SlideFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SlideFragment : Fragment() {
    private var image: Int? = null
    private var header: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            image = it.getInt(IMAGE)
            header = it.getString(HEADER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_slide, container, false)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val textView = view.findViewById<TextView>(R.id.sliderText)
        imageView.setImageResource(image!!)
        textView.text = header
        return view
    }

    companion object {
        /**
         * This factory method creates a new instance of
         * this fragment using the provided parameters.
         *
         * @param image Image of slide.
         * @param header Header of slide
         * @return A new instance of fragment SlideFragment.
         */
        @JvmStatic
        fun newInstance(image: Int, header: String) =
            SlideFragment().apply {
                arguments = Bundle().apply {
                    putInt(IMAGE, image)
                    putString(HEADER, header)
                }
            }
    }
}