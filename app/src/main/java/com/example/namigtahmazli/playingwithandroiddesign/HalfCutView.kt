package com.example.namigtahmazli.playingwithandroiddesign

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.shapes.Shape

class HalfCutView(val cut: Float) : Shape() {
    private var rect: RectF = RectF()
    override fun draw(canvas: Canvas?, paint: Paint?) {
        canvas?.drawPath(drawOutline(), paint)
    }

    override fun clone(): Shape {
        return (super.clone() as HalfCutView).apply {
            rect = RectF()
        }
    }

    override fun onResize(width: Float, height: Float) {
        super.onResize(width, height)
        rect = RectF(0f, 0f, width, height)
    }

    private fun drawOutline(): Path {
        return Path().apply {
            moveTo(rect.left, rect.top)
            lineTo(rect.right, rect.top)
            lineTo(rect.right, rect.bottom - cut)
            lineTo(rect.left, rect.bottom)
            lineTo(rect.left, rect.top)
        }
    }

}