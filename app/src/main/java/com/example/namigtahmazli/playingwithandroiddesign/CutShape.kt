package com.example.namigtahmazli.playingwithandroiddesign

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.shapes.Shape

class CutShape(val cut: Float) : Shape() {
    private var mRect = RectF()
    override fun draw(canvas: Canvas?, paint: Paint?) {
        canvas?.drawPath(drawOutlines(), paint)
    }

    private fun drawOutlines(): Path {
        return Path().apply {
            moveTo(mRect.left + cut, mRect.top)
            lineTo(mRect.right - cut, mRect.top)
            lineTo(mRect.right, mRect.top + cut)
            lineTo(mRect.right, mRect.bottom - cut)
            lineTo(mRect.right - cut, mRect.bottom)
            lineTo(mRect.left + cut, mRect.bottom)
            lineTo(mRect.left, mRect.bottom - cut)
            lineTo(mRect.left, mRect.top + cut)
            lineTo(mRect.left + cut, mRect.top)
        }
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