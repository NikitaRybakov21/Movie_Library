package com.example.movielibrary.ui.detailsFragment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.movielibrary.R

class FragmentRating : Fragment() {

    companion object{
        fun newInstance(ratingPercent: Int) : FragmentRating {
            val fragmentRating = FragmentRating()
            val bundle = Bundle()
            bundle.putInt("KEY",ratingPercent)
            fragmentRating.arguments = bundle
            return fragmentRating
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val percent = arguments?.getInt("KEY")

        val view = inflater.inflate(R.layout.rating_fragment,container,false)
        val linearLayout = view.findViewById<LinearLayout>(R.id.linearLayout)
        linearLayout.addView(DrawView(requireContext(),percent))

        return linearLayout
    }

    inner class DrawView(ctx: Context, private val percent: Int?) : View(ctx) {
        private val paint = Paint()

        private var count = 0f
        private var speed = 0f
        private var startSpeed = 6f

        @SuppressLint("DrawAllocation", "ResourceAsColor")
        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)

            val temp = count / 100

            val myRectGreen = Rect()
            val myRectRed = Rect()

            myRectGreen.set(0, 0, (width * temp).toInt(), height)
            myRectRed.set((width * temp).toInt(), 0, width, height)

            paint.color = Color.rgb(69, 204, 90)
            canvas?.drawRect(myRectGreen, paint)

            paint.color = Color.RED
            canvas?.drawRect(myRectRed, paint)

            if(percent != null) {
                if(count <= percent) {
                    invalidate()
                    count += speed
                    speed = startSpeed - startSpeed * (count / percent)
                }
            }
        }

    }
}


