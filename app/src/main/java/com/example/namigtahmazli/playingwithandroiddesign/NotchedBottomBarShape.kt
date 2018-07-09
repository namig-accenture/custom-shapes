package com.example.namigtahmazli.playingwithandroiddesign

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.shapes.Shape

class NotchedBottomBarShape(private val notchedCircleRadius: Float) : Shape() {
    private var rect = RectF()
    override fun draw(canvas: Canvas?, paint: Paint?) {
        canvas?.drawPath(drawOutline(), paint)
    }

    override fun clone(): Shape {
        return (super.clone() as NotchedBottomBarShape).apply {
            rect = RectF()
        }
    }

    override fun onResize(width: Float, height: Float) {
        super.onResize(width, height)
        rect = RectF(0f, 0f, width, height)
    }

    private fun drawOutline(): Path {
        val notchedArea = RectF(
                rect.centerX() - notchedCircleRadius,
                rect.top - notchedCircleRadius,
                rect.centerX() + notchedCircleRadius,
                rect.top + notchedCircleRadius
        )
        return Path().apply {
            moveTo(rect.left, rect.top)
            lineTo(rect.centerX() - notchedCircleRadius, rect.top)
            addArc(notchedArea, 180f, -180f)
            lineTo(rect.right, rect.top)
            lineTo(rect.right, rect.bottom)
            lineTo(rect.left, rect.bottom)
            lineTo(rect.left, rect.top)
        }
    }

}