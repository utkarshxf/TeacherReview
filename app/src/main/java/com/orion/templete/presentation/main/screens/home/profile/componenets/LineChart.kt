package com.orion.templete.presentation.main.screens.home.profile.componenets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.ceil
import kotlin.math.max


@Composable
fun LineChart(
    values: List<Float>,
    gradient: Brush,
    baseColor: Color,
    minimum: Float = 0f,
    showMarkers: Boolean = true,
    modifier: Modifier
) {
    val lineChartFont = android.graphics.Paint().apply {
        textSize = 30f
        color = android.graphics.Color.WHITE
    }
    val isDataUnique = values.distinct().count() == 1
    val isDataAllZero = isDataUnique && values.distinct().first() == 0f
    val maxOfValues =
        (if (isDataAllZero) 1f else if (isDataUnique) 2 * values[0] else values.reduce(::max) * 1.1f)
    val values = if (isDataAllZero) values.map { it + 0.01f } else values
    val fractions = values.map { 1 - (it - minimum) / (maxOfValues - minimum) }

    val indices = values.indices.toList()
    val maxOfIndices = indices.reduce(::max)
    val parts = indices.map { it * 1f / maxOfIndices }
    val spacerY = 40f
    val spacerX = 100f

    var delta = 16f
    var levels = getLevelSize(minimum, maxOfValues, delta)
    while (levels < 5 && delta >= 0.01) {
        delta /= 2f
        levels = getLevelSize(minimum, maxOfValues, delta)
    }
    if (levels > 5) {
        levels = 5
        delta = (maxOfValues - minimum) / levels
    }


    Canvas(modifier = modifier.size(300.dp)) {
        val outline = Path()
        val fill = Path()
        val effectiveHeight = size.height - spacerY
        val effectiveWidth = size.width - spacerX

        fill.moveTo(spacerX, effectiveHeight)

        var ox = spacerX
        var oy = fractions[0] * effectiveHeight
        outline.moveTo(ox, oy)
        fill.lineTo(ox, oy)

        (1 until values.size).forEach {
            val x = parts[it] * effectiveWidth + spacerX
            val y = fractions[it] * effectiveHeight

            outline.cubicTo(
                x1 = lerp(ox, x, 0.33f), y1 = oy,
                x2 = lerp(ox, x, 0.66f), y2 = y,
                x3 = x, y3 = y
            )
            fill.cubicTo(
                x1 = lerp(ox, x, 0.33f), y1 = oy,
                x2 = lerp(ox, x, 0.66f), y2 = y,
                x3 = x, y3 = y
            )

            ox = x
            oy = y
        }
        fill.lineTo(effectiveWidth + spacerX, effectiveHeight)
        drawPath(outline, color = baseColor, style = Stroke(width = 10f))
        drawPath(fill, brush = gradient)

        if (showMarkers)
            indices.forEach {
                drawCircle(
                    color = Color.White, 10f,
                    Offset(parts[it] * effectiveWidth + spacerX, fractions[it] * effectiveHeight)
                )
                drawCircle(
                    color = baseColor, 10f,
                    Offset(parts[it] * effectiveWidth + spacerX, fractions[it] * effectiveHeight),
                    style = Stroke(width = 10f)
                )
            }

        drawLine(
            Color(0xFFBDBDBD),
            Offset(spacerX - 20f, size.height - spacerY),
            Offset(effectiveWidth + spacerX + 20f, size.height - spacerY),
            strokeWidth = 5f
        )

        (0..levels).forEach {
            drawContext.canvas.nativeCanvas.drawText(
                String.format("%.2f", (minimum + delta * (levels - it))),
                0f, effectiveHeight * it / levels, lineChartFont
            )
        }
    }
}

fun getLevelSize(minimum: Float, maximum: Float, delta: Float): Int {
    return ceil((maximum - minimum) / delta).toInt()
}

fun lerp(a: Float, b: Float, f: Float): Float {
    return a + (b - a) * f
}

@Composable
@Preview(showBackground = true)
fun CustomComponentPreview() {
    val dummyData = listOf(1f, 2f, 3f, 4f, 1f, 2f, 4f, 3f)
//    LineChart(values = dummyData)
}
