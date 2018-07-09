package com.example.namigtahmazli.playingwithandroiddesign

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.shapes.Shape

class CircleShape : Shape() {
    private var mRect = RectF()
    override fun draw(canvas: Canvas?, paint: Paint?) {
        canvas?.drawCircle(mRect.centerX(), mRect.centerY(), mRect.width() / 2, paint)
    }

    override fun onResize(width: Float, height: Float) {
        super.onResize(width, height)
        mRect = RectF(0f, 0f, width, height)
    }

    override fun clone(): Shape {
        return (super.clone() as CutShape).apply {
            mRect = RectF()
        }
    }
}